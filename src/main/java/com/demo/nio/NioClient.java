package com.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
    // 通道管理器.
    private Selector selector;
    // 启动客户端.
    public static void main(String[] args) throws IOException {
        NioClient nioClient = new NioClient();
        nioClient.initClient("127.0.0.1", 9000);
        nioClient.connect();
    }

    /**
     * 获取一个Socket通道,并对该通道做一些初始化的工作.
     * @param ip 连接服务器的ip.
     * @param port 连接服务器的端口.
     * @throws IOException
     */
    public void initClient(String ip, int port) throws IOException {
        // 创建一个socket通道.
        SocketChannel channel = SocketChannel.open();
        // 设置通道为非阻塞.
        channel.configureBlocking(false);
        // 获得一个通道管理器.
        this.selector = Selector.open();

        // 客户端连接服务器.
        channel.connect(new InetSocketAddress(ip, port));
        // 将通道管理器和该通道绑定,并为该通道注册SelectionKey.OP_CONNECT事件.
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 采用轮询的方法监听selector上是否有需要处理的事件,如果有,则进行处理.
     * @throws IOException
     */
    public void connect() throws IOException {
        // 轮询访问通道管理器.
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 删除已选择的key,避免重复.
                iterator.remove();
                // 连接事件发生.
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 如果正在连接,则完成连接.
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    // 设置成非阻塞.
                    channel.configureBlocking(false);
                    // 给服务端发送消息.
                    ByteBuffer buffer = ByteBuffer.wrap("HelloServer".getBytes());
                    channel.write(buffer);
                    // 在和服务端连接成功之后,为了可以接收到服务端的信息,需要给通道设置读权限.
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    /**
     * 处理服务器端发来的消息的事件.
     *
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {
        // 服务器端可读取消息:得到事件发生的Socket通道.
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区.
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        if (read != -1) {
            System.out.println("客户端收到消息: " + new String(buffer.array(), 0, read));
        }
    }
}

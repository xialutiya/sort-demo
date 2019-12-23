package com.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
    public static void main(String[] args) throws IOException {
        // 创建一个在本地端口进行监听的服务socket通道,并设置为非阻塞模式.
        ServerSocketChannel socket = ServerSocketChannel.open();
        // 必须设置为非阻塞才能注册到selector上,否则会报错,selector模式本身就是非阻塞模式.
        socket.configureBlocking(false);
        socket.socket().bind(new InetSocketAddress(9000));
        // 创建一个选择器.
        Selector selector = Selector.open();
        // 把ServerSocketChannel注册到selector上,并且selector对client的accept操作感兴趣.
        socket.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("等待事件发生...");
            // 轮询监听channel里面的key,select是阻塞的,accept也是阻塞的.
            int select = selector.select();
            System.out.println("有事件发生了...");
            // 客户端请求被轮询监听到.
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 删除本次key,防止下次selector重复处理.
                iterator.remove();
                handler(key);
            }
        }
    }

    public static void handler(SelectionKey key) throws IOException {
        // 如果是连接事件.
        if (key.isAcceptable()) {
            System.out.println("有客户端连接事件发生了.");
            ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
            // nio非阻塞的体现:此处accept方法是阻塞的,但是这里是发生了connection事件,
            // 所以这个方法会马上执行完,不会阻塞.
            // 处理完连接请求不会继续等待客户端的数据发送.
            SocketChannel channel = socketChannel.accept();
            channel.configureBlocking(false);
            // 通过selector监听channel时,对read事件感兴趣.
            channel.register(key.selector(), SelectionKey.OP_READ);
            // 如果发生了read事件.
        } else if (key.isReadable()) {
            System.out.println("有客户端read事件发生...");
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // nio非阻塞的体现:首先read方法不会阻塞,其次这种事件响应模型,
            // 当调用到read方法时,肯定是发生了客户端发送数据的事件.
            int read = channel.read(buffer);
            if (read != -1) {
                System.out.println("读取到客户端发送的数据: " + new String(buffer.array(), 0, read));
            }
            ByteBuffer wrap = ByteBuffer.wrap("HelloClient".getBytes());
            channel.write(wrap);
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            System.out.println("write事件...");
            key.interestOps(SelectionKey.OP_READ);
//            channel.close();
        }
    }
}

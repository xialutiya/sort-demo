package com.demo.linked;

public class Linked {

    private Node head;

    public Node getHead() {
        return head;
    }


    //   public class Node {
//        int data;
//        Node next;
//
//        Node(int data) {
//            this.data = data;
//        }
//    }

    public void insertHead(int data) {
        Node node = new Node(data);
        node.setNext(head);
        head = node;
    }

    public Node reverse(Node head, int K) {
       Node cur = null, next = null;
        int i;
        Node kth;
        for (i = 0, kth = head; kth != null && (i < K); i++, kth = kth.getNext()) {
            next.setNext(kth.getNext());
            kth.setNext(cur);;
            cur = kth;
            kth = next;
        }
        return cur;
    }

    public void printLinked(Node head) {
        while (head != null) {
            System.out.print(head.getData() + " ");
            head = head.getNext();
        }
        System.out.println();
    }

}


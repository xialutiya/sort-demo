package com.demo.linked;

public class LinkedReverse {

    public static void main(String[] args) {
        Linked linked = new Linked();
        for (int i = 9; i >= 0; i--) {
            linked.insertHead(i);
        }
        linked.printLinked(linked.getHead());
        Node node = reverseGroup(linked.getHead(), 3);
        linked.printLinked(node);
    }

    public static Node GetKPlusOneThNode(int K, Node head) {
        Node Kth;
        int i;
        if (head == null) {
            return head;
        }
        for (i = 0, Kth = head; Kth != null && (i < K); i++, Kth = Kth.getNext()) {
            if (i == K && Kth != null) {
                return Kth;
            }
        }
        return head.getNext();
    }

    public static boolean HasKnodes(Node head, int K) {
        int i;
        for (i = 0; head != null && (i < K); i++, head = head.getNext()) {
            if (i == K) {
                return true;
            }
        }
        return false;
    }

    public static Node ReverseBlockOfK(Node head, int K) {
        Node temp, next, cur = head, newHead;
        if (K == 0 || K == 1) {
            return head;
        }
        if (HasKnodes(cur, K - 1)) {
            newHead = GetKPlusOneThNode(K - 1, cur);
        } else {
            newHead = head;
        }
        while (cur != null && HasKnodes(cur, K)) {
            temp = GetKPlusOneThNode(K, cur);
            int i = 0;
            while (i < K) {
                next = cur.getNext();
                cur.setNext(temp);
                temp = cur;
                cur = next;
                i++;
            }
        }
        return newHead;
    }

    /**
     * 逆置链表.
     *
     * @param head 头节点.
     * @return 头节点.
     */
    public static Node reverse(Node head) {
        Node pre = null, next;
        while (head != null) {
            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * K个一组逆置链表.
     *
     * @param head 头节点.
     * @param K    一组的数量.
     * @return 头节点.
     */
    public static Node reverseGroup(Node head, int K) {
        Node dummy = new Node(0);
        dummy.setNext(head);
        Node pre = dummy;
        Node end = dummy;
        while (end.getNext() != null) {
            for (int i = 0; (i < K) && end != null; i++) {
                end = end.getNext();
            }
            if (end == null) {
                break;
            }
            Node start = pre.getNext();
            Node next = end.getNext();
            end.setNext(null);
            pre.setNext(reverse(start));
            start.setNext(next);
            pre = start;
            end = pre;
        }
        return dummy.getNext();
    }
}

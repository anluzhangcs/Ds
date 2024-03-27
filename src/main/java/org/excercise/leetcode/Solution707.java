package org.excercise.leetcode;


/**
 * @author Manny
 * @create 2024-03-23-20:57
 */
public class Solution707 {
    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(4);
        obj.display();
        System.out.println(obj.get(1));
    }
}

class MyLinkedList {

    LinkedNode head;

    public MyLinkedList() {
        head = new LinkedNode();
    }

    public int get(int index) {
        if (index < 0) {
            return -1;
        }
        LinkedNode cur = head.next;
        int res = -1;
        while (cur != null && index > 0) {
            cur = cur.next;
            index--;
        }
        if (cur != null) {
            res = cur.val;
        }
        return res;
    }

    public void addAtHead(int val) {
        LinkedNode node = new LinkedNode(val);
        node.next = head.next;
        head.next = node;
    }

    public void addAtTail(int val) {
        LinkedNode node = new LinkedNode(val);
        LinkedNode tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
    }

    public void addAtIndex(int index, int val) {
        LinkedNode node = new LinkedNode(val);
        LinkedNode tmp = head;
        while (tmp.next != null && index > 0) {
            tmp = tmp.next;
            index--;
        }
        if (tmp.next == null && index > 0) {
            //越界
            return;
        }
        node.next = tmp.next;
        tmp.next = node;

    }

    public void deleteAtIndex(int index) {
        if (index < 0) {
            return;
        }

        LinkedNode pre = head;
        LinkedNode cur = head.next;
        while (cur != null && index > 0) {
            pre = pre.next;
            cur = cur.next;
            index--;
        }
        if (cur == null) {
            //越界
            return;
        }
        pre.next = cur.next;
        cur.next = null;
    }

    //show
    public void display() {
        LinkedNode tmp = head.next;
        System.out.println("此时链表为：");
        while (tmp != null) {
            System.out.printf("%d\t", tmp.val);
            tmp = tmp.next;

        }
        System.out.println();
    }
}

class LinkedNode {
    int val;
    LinkedNode next;

    public LinkedNode() {
    }

    ;

    public LinkedNode(int val) {
        this.val = val;
    }

    public LinkedNode(int val, LinkedNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
package org.excercise.linkedlist;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Manny
 * @create 2024-03-04-14:42
 * 链表
 */
public class LinkedListDemo {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        Scanner scanner = new Scanner(System.in);
        String menu;
        char data;
        boolean flag = true;
        int index;

        while (flag) {
            System.out.println("init:初始化链表");
            System.out.println("add:添加节点");
            System.out.println("del：删除节点");
            System.out.println("show:展示节点");
            System.out.println("find:找到节点");
            System.out.println("update:更新节点");
            System.out.println("addAt:指定位置添加节点");
            //等待输入命令
            menu = scanner.next();
            if (menu.equals("findAt")) {
                index = scanner.nextInt();
                Node node = linkedList.find(index);
                if (node != null) {

                    System.out.println("该节点值为" + node.getData());
                }
            } else if (menu.equals("find")) {
                data = scanner.next().charAt(0);
                int count = linkedList.find(data);
                System.out.println("该节点位置为：" + count);
            } else if (menu.equals("add")) {
                data = scanner.next().charAt(0);
                linkedList.insert(data);

            } else if (menu.equals("addAt")) {
                System.out.printf("请输入要插入的节点位置：");
                index = scanner.nextInt();
                System.out.printf("请输入节点值：");
                data = scanner.next().charAt(0);
                linkedList.insertAtIndex(index, data);

            } else if (menu.equals("show")) {
                linkedList.display();

            } else if (menu.equals("exit")) {
                System.out.println("程序退出");
                flag = false;

            } else if (menu.equals("del")) {
                index = scanner.nextInt();
                linkedList.delete(index);

            } else if (menu.equals("init")) {
                linkedList.init();

            } else if (menu.equals("update")) {
                System.out.printf("请输入要更新的节点位置：");
                index = scanner.nextInt();
                System.out.printf("请输入要更新的节点值：");
                data = scanner.next().charAt(0);
                linkedList.update(index, data);

            } else if (menu.equals("findk")) {
                index = scanner.nextInt();
                Node k = linkedList.findK(index);
                if (k != null) {
                    System.out.println("倒数第" + index + "的值为" + k.getData());

                }

            } else if (menu.equals("count")) {

                System.out.println("该链表结点个数为：" + linkedList.count());

            } else if (menu.equals("reverse")) {
                System.out.println("反转之后的链表");
                linkedList.reverse();
            } else if (menu.equals("displayr")) {
                System.out.println("逆序打印");
                linkedList.displayFromTail();

            } else if (menu.equals("merge")) {
                LinkedList linkedList1 = new LinkedList();
                linkedList1.init();
                linkedList1.insert('a');
                linkedList1.insert('c');
                linkedList1.insert('e');
                LinkedList linkedList2 = new LinkedList();
                linkedList2.init();
                linkedList2.insert('b');
                linkedList2.insert('d');
                linkedList2.insert('f');
                System.out.println("合并单链表");
                LinkedList merge = linkedList.merge(linkedList1.getHead(), linkedList2.getHead());
                merge.display();

            }
        }
    }
}


//节点类
class Node {
    private char data;
    private Node next;

    public Node(char data) {
        this.data = data;
    }

    public Node() {
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}

class LinkedList {
    private Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    //初始化
    public void init() {
        head = new Node();
        head.setNext(null);
    }

    //尾插法
    public void insert(char data) {
        //临时指针
        Node tmp = head;
        //找到链表末尾
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        Node node = new Node(data);
        node.setNext(null);
        tmp.setNext(node);
    }

    //头插法
    public void insertToHead(char data) {
        Node node = new Node(data);
        node.setNext(head.getNext());
        head.setNext(node);
    }

    //添加节点到指定位置
    public void insertAtIndex(int index, char data) {
        Node node = new Node(data);
        Node pre = find(index - 1);
        if (pre != null) {//没有越界
            if (pre.getNext() == null) { //要添加的在最后
                pre.setNext(node);
                node.setNext(null);

            } else { //要添加的在中间
                Node next = find(index);
                pre.setNext(node);
                node.setNext(next);

            }
        }

    }

    //查找链表第index元素
    public Node find(int index) {
        Node tmp = head;
        while (index > 0 && tmp.getNext() != null) {
            index--;
            tmp = tmp.getNext();
        }
        if (index > 0) {
            System.out.println("链表越界，请重新输入");
            return null;
        } else {
            return tmp;
        }
    }

    //查找指定元素
    public int find(char data) {
        Node tmp = head;
        int count = 0;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
            count++;
            if (tmp.getData() == data) {
                return count;
            }
        }
        System.out.println("该元素不存在，请重新输入");
        return -1;
    }

    //更新链表第index个元素
    public void update(int index, char data) {
        Node node = find(index);
        if (node != null) {
            node.setData(data);
        }
    }

    //删除链表第index个元素
    public void delete(int index) {
        Node node = find(index);
        if (node != null) {
            //找到前一个元素
            Node pre = find(index - 1);
            pre.setNext(node.getNext());
            System.out.println("删除成功，值为" + node.getData());
        }
    }


    //show
    public void display() {
        Node tmp = head.getNext();
        System.out.println("此时链表为：");
        while (tmp != null) {
            System.out.printf("%c\t", tmp.getData());
            tmp = tmp.getNext();

        }
        System.out.println();
    }


    //查找单链表的倒数第k个节点

    /**
     * 使用双指针
     *
     * @param k
     * @return
     */
    public Node findK(int k) {
        Node pre = head;
        Node next = head;
        while (k > 0 && next.getNext() != null) { //让一个指针先走k步

            next = next.getNext();
            k--;
        }
        if (k > 0) { //说明越界
            System.out.println("已越界");
            return null;
        }
        while (next != null) {
            next = next.getNext();
            pre = pre.getNext();
        }
        return pre;
    }

    /**
     * 将单链表逆转
     * 双指针
     * 遍历链表，使用头插法 插入 链表的每个节点
     */
    public void reverse() {
        //创建新的头节点
        Node newHead = new Node();
        //指向当前节点
        Node cur = head.getNext();
        Node next = null;

        while (cur != null) { //遍历到尾部
            //暂存cur下一个节点
            next = cur.getNext();

            //进行头插法
            cur.setNext(newHead.getNext());
            newHead.setNext(cur);

            //将cur向前移
            cur = next;
        }

        //将地址赋值给head
        head.setNext(newHead.getNext());

    }

    /**
     * 从尾到头打印单链表
     * 我的思路：
     * - 逆转一下在打印（会破坏原链表结构，不建议）
     * - 从头遍历，将要打印的信息储存起来，逆向输出
     * 最佳 利用栈
     */
    public void displayFromTail() {
        Node tmp = head.getNext();
        //栈结构
        Stack<Node> stack = new Stack<>();
        while (tmp != null) {
            //依次压入栈中
            stack.push(tmp);
            tmp = tmp.getNext();
        }
        //出栈操作
        while (stack.size() > 0) {
            System.out.println(stack.pop().getData());

        }

    }

    /**
     * 从小到大
     * 合并两个单链表，合并后任然有序
     * 思路：和逆转单链表类似，创建一个新节点，依次比较两个链表，小的使用尾插法
     * 重点：注意保存下一个节点指针
     *
     * @param head1
     * @param head2
     * @return
     */
    public LinkedList merge(Node head1, Node head2) {
        LinkedList linkedList = new LinkedList();
        linkedList.init();

        Node newHead = linkedList.getHead();
        Node tmp = newHead;

        Node cur1 = head1.getNext();
        Node cur2 = head2.getNext();
        Node next1 = null;
        Node next2 = null;

        while (cur1 != null && cur2 != null) {
            //链表1比较小
            if (cur1.getData() < cur2.getData()) {
                //暂存下一个节点指针，避免丢失
                next1 = cur1.getNext();

                //尾插法
                tmp.setNext(cur1);
                cur1.setNext(null);
                tmp = tmp.getNext();

                //移动指针
                cur1 = next1;

            } else {
                //暂存下一个节点指针，避免丢失
                next2 = cur2.getNext();

                //尾插法
                tmp.setNext(cur2);
                cur2.setNext(null);
                tmp = tmp.getNext();

                //移动指针
                cur2 = next2;

            }
        }

        //将单链表剩余的加进来
        while (cur1 != null) {
            //暂存下一个节点指针，避免丢失
            next1 = cur1.getNext();

            //尾插法
            tmp.setNext(cur1);
            cur1.setNext(null);
            tmp = tmp.getNext();

            //移动指针
            cur1 = next1;
        }

        while (cur2 != null) {
            next2 = cur2.getNext();

            //尾插法
            tmp.setNext(cur2);
            cur2.setNext(null);
            tmp = tmp.getNext();

            //移动指针
            cur2 = next2;
        }
        return linkedList;
    }

    //获取节点个数
    public int count() {
        Node tmp = head;
        int count = 0;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
            count++;
        }
        return count;
    }
}



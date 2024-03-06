package org.excercise.linkedlist;

import java.util.Scanner;

/**
 * @author Manny
 * @create 2024-03-06-15:02
 * 双向链表:
 * 删除不需要辅助接点，可以自我删除
 * 可以双向遍历
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
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
                DoubleNode node = linkedList.findx(index);
                if (node != null) {

                    System.out.println("该节点值为" + node.data);
                }
            } else if (menu.equals("find")) {
                data = scanner.next().charAt(0);
                int count = linkedList.find(data);
                System.out.println("该节点位置为：" + count);
            } else if (menu.equals("add")) {
                data = scanner.next().charAt(0);
                linkedList.addAtLast(data);

            } else if (menu.equals("show")) {
                linkedList.display();

            } else if (menu.equals("exit")) {
                System.out.println("程序退出");
                flag = false;

            } else if (menu.equals("del")) {
                index = scanner.nextInt();
                linkedList.del(index);

            } else if (menu.equals("init")) {
                linkedList.init();

            } else if (menu.equals("update")) {
                System.out.printf("请输入要更新的节点位置：");
                index = scanner.nextInt();
                System.out.printf("请输入要更新的节点值：");
                data = scanner.next().charAt(0);
                linkedList.update(index, data);

            } else if (menu.equals("showPre")) {
                index = scanner.nextInt();
                linkedList.showPre(index);
            } else if (menu.equals("showNext")) {
                index = scanner.nextInt();
                linkedList.showNext(index);
            }
        }
    }

}

class DoubleNode {
    DoubleNode pre;
    char data;
    DoubleNode next;

}

class DoubleLinkedList {

    private DoubleNode head;

    public void init() {
        head = new DoubleNode();
        head.pre = null;
        head.next = null;

    }

    /**
     * 找到元素位置
     *
     * @param data
     */
    public int find(char data) {
        DoubleNode tmp = head.next;
        int index = 0;
        boolean flag = false;

        while (tmp != null) {
            index++;
            if (tmp.data == data) {
                flag = true;
                break; //找到了退出此次循环
            }
            tmp = tmp.next;
        }
        return index >= count() && !flag ? -1 : index;

    }

    /**
     * 找到指定位置的元素
     *
     * @param index
     * @return
     */
    public DoubleNode findx(int index) {
        DoubleNode tmp = head;

        while (tmp.next != null && index > 0) {
            index--;
            tmp = tmp.next;
        }

        if (index > 0) {
            System.out.println("越界");
            return null;
        }

        return tmp;
    }

    /**
     * 尾插法
     *
     * @param data
     */
    public void addAtLast(char data) {
        DoubleNode node = new DoubleNode();
        node.data = data;

        DoubleNode tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }

        //此时tmp指向最后一个元素
        tmp.next = node;
        node.pre = tmp;
        node.next = null;
    }

    /**
     * 头插法
     *
     * @param data
     */
    public void addAtHead(char data) {
        DoubleNode node = new DoubleNode();
        node.data = data;

        node.next = head.next;
        node.pre = head;
        head.next = node;
    }

    /**
     * 更新指定位置的值
     *
     * @param index
     */
    public void update(int index, char data) {
        DoubleNode node = findx(index);
        if (node != null) {

            node.data = data;
        }
    }

    /**
     * 删除指定位置的值
     * 自我删除
     *
     * @param index
     */
    public void del(int index) {
        DoubleNode node = findx(index);
        if (node != null) {
            //自我删除
            node.pre.next = node.next;
            //判断是否是最后一个节点
            if (node.next != null) {

                node.next.pre = node.pre;
            }
            node.pre = null;
            node.next = null;
        }
    }


    /**
     * 展示
     */
    public void display() {

        DoubleNode tmp = head.next;

        System.out.println("链表为：");
        while (tmp != null) {
            System.out.printf("%c\t", tmp.data);
            tmp = tmp.next;
        }

    }

    /**
     * 统计元素个数
     */

    public int count() {
        DoubleNode tmp = head.next;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        return count;
    }

    //输出他的前一个结点
    public void showPre(int index) {
        DoubleNode node = findx(index);
        if (node != null) {
            System.out.println("前一个节点值为：" + node.pre.data);

        }
    }

    //输出后一个节点
    public void showNext(int index) {
        DoubleNode node = findx(index);
        if (node != null && node.next != null) {
            System.out.println("后一个结点值为：" + node.next.data);
        }
    }
}

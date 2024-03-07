package org.excercise.linkedlist;

import java.util.Scanner;

/**
 * @author Manny
 * @create 2024-03-06-21:27
 * 循环单链表
 * 约瑟夫环问题：n个人围成一圈，从编号为k（1<=k<=n）开始报数，报数也从1开始，直到m，然后出列
 * 继续操作，直至完全出列
 */
public class CircleLinkedListDemo {

    public static void main(String[] args) {
        CircleLinkedList linkedList = new CircleLinkedList();
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

            } else if (menu.equals("add")) {
                data = scanner.next().charAt(0);
                linkedList.add(data);

            } else if (menu.equals("show")) {
                linkedList.show();

            } else if (menu.equals("exit")) {
                System.out.println("程序退出");
                flag = false;

            } else if (menu.equals("init")) {
                linkedList.init();

            } else if (menu.equals("josfu")) {
                System.out.println("请输入开始位置：");
                int k = scanner.nextInt();
                System.out.println("请输入叫数次数");
                int m = scanner.nextInt();
                linkedList.joshfu(k, m);

            }
        }
    }

}

class CircleNode {
    int data;
    CircleNode next;

}

/**
 * 不带头结点
 */
class CircleLinkedList {

    //环形链表的第一个元素
    CircleNode first;

    public void init() {
        first = null;
    }

    public void add(char data) {
        //临时指针
        CircleNode tmp = first;

        if (tmp == null) { //一开始没有元素
            CircleNode node = new CircleNode();
            node.next = node;
            node.data = data;
            first = node;
            return;
        }

        while (tmp.next != first) { //判断是否到了最后一个元素
            tmp = tmp.next;
        }
        //找到了最后一个元素
        CircleNode node = new CircleNode();
        node.data = data;
        node.next = first; //将指针指向第一个节点
        tmp.next = node;

    }

    public void show() {
        //临时指针
        CircleNode tmp = first;

        System.out.println("此时链表为：");
        if (tmp.next == first) { //只有一个元素
            System.out.printf("%c\t", tmp.data);
        } else {
            while (tmp.next != first) { //判断是否到了最后一个元素
                System.out.printf("%c\t", tmp.data);
                tmp = tmp.next;
            }
            //输出最后一个元素
            System.out.printf("%c\t", tmp.data);
        }

        System.out.println();
    }

    /**
     * 演示约瑟夫问题
     * 思路：双指针，设置一个helper指针指向first前一个结点，即最后一个节点
     *
     * @param k 从第k个位置开始报m次书
     * @param m
     */
    public void joshfu(int k, int m) {

        //1.判断k的合理性
        if (k < 1 || k > count()) {
            System.out.println("开始位置有误，请重新输入");
            return;
        }

        //2.判断链表合法性
        if (first == null) {
            System.out.println("单链表为空");
            return;
        }

        //3.设置helper指针
        CircleNode tmp = first;
        CircleNode helper = null;
        while (tmp.next != first) { //找到最后一个节点
            tmp = tmp.next;
            helper = tmp;
        }

        //4.判断helper是否为空 空表示链表只有一个元素
        if (helper == null) {
            System.out.printf("出对序列为：%c\n", first.data);
            return;
        }

        //5.first 和 helper 同时向前移动k-1步
        //循环后first指向开始报数的元素
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        //6.开始报数
        int count = count();
        int j = m;
        while (count > 1) { //当链表中元素多于2个
            j = m;
            //向前走m-1步，因此自己也报了数
            for (int i = 0; i < m - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            //报数
            System.out.printf("%c被叫道\n", first.data);
            //删除该元素
            helper.next = first.next;
            first = first.next;
            count = count();
        }

        //7.报数最后一个元素 first指向
        System.out.printf("%c被叫道\n", first.data);

    }

    public int count() {
        int count = 0;
        if (first == null) {
            return 0;
        }
        CircleNode tmp = first;
        while (tmp.next != first) {
            count++;
            tmp = tmp.next;
        }
        count++;
        return count;
    }

}

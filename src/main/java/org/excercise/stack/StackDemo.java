package org.excercise.stack;

import java.util.Scanner;

/**
 * @author Manny
 * @create 2024-03-07-20:39
 * 栈：先进后出，只有一端进行操作。
 * 应用场景：
 * - 子程序的调用：在跳往子程序前，会将当前当前程序下个指令的地址存到堆栈中，子程序执行完成后将地址取出，继续原程序执行
 * - 递归调用：与子程序调用类似，但还存了参数、区域变量等
 * - 中缀表达式 转 后缀表达式
 * - 二叉树遍历
 * - 图的深度遍历
 */
public class StackDemo {

    public static void main(String[] args) {
        //ArrayStack stack = new ArrayStack();
        LinkedStack<Integer> stack = new LinkedStack();
        //LinkedStack<Character> stack = new LinkedStack();
        Scanner scanner = new Scanner(System.in);
        String menu;
        int maxSize;
        char data;
        int value;
        while (true) {
            System.out.println("请输入选项：");
            System.out.println("init:");
            System.out.println("show:");
            System.out.println("pop:");
            System.out.println("push:");
            menu = scanner.next();
            if (menu.equals("show")) {
                stack.display();
            } else if (menu.equals("init")) {
                //System.out.println("请输入栈的最大容量");
                //maxSize = scanner.nextInt();
                //stack.init(maxSize);
                stack.init();

            } else if (menu.equals("push")) {
                System.out.println("请输入要入栈的值:");
                data = scanner.next().charAt(0);
                stack.push((int) data);

            } else if (menu.equals("pop")) {
                //char pop = stack.pop();
                //System.out.printf("此时出栈的为:%c\n", pop);
                stack.pop();
            } else if (menu.equals("exit")) {
                break;
            }
        }

        scanner.close();
    }


}

/**
 * 使用数组模拟栈结构
 */
class ArrayStack {
    int top;
    char[] arr;
    int maxSize;

    /**
     * 初始化栈
     *
     * @param maxSize
     */
    public void init(int maxSize) {
        this.maxSize = maxSize;
        arr = new char[maxSize];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 入栈
     *
     * @param data
     */
    public void push(char data) {
        if (isFull()) {
            System.out.println("栈已满，不能入栈");
            return;
        }
        top++;
        arr[top] = data;
    }

    /**
     * 出栈
     *
     * @return
     */
    public char pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        char data = arr[top];
        top--;
        return data;
    }

    public void display() {
        System.out.println("栈从上到下以此为：");
        //注意使用临时变量代替top,防止破坏栈的结构
        int tmp = top;
        while (tmp >= 0) {
            System.out.printf("%c\t", arr[tmp]);
            tmp--;
        }
        System.out.println();
    }
}

//使用带头结点单链表模拟栈结构
class LinkedStack<T> {

    Node<T> top;

    public LinkedStack() {
        init();
    }

    public void init() {
        top = new Node();
        top.next = null;
    }

    public boolean isEmpty() {
        return top.next == null ? true : false;
    }

    public T peek() {
        return top.next.data;
    }

    /**
     * 入栈
     *
     * @param data
     */
    public void push(T data) {
        Node<T> node = new Node(data);
        //直接使用头插法
        node.next = top.next;
        top.next = node;
    }

    //出栈
    public T pop() {
        if (isEmpty()) {
            System.out.println("栈空");
            return null;
        }
        //System.out.printf("此时出栈元素为:%c\n", top.next.data);
        //tmp指向栈顶元素,需要删除
        Node<T> tmp = top.next;
        top.next = tmp.next;
        tmp.next = null;
        return tmp.data;
    }

    public void display() {
        Node cur = top.next;
        System.out.println("此时栈顶元素为:");
        while (cur != null) {
            //System.out.printf("%c\t", cur.data);
            cur = cur.next;
        }
        System.out.println();
    }

}

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public Node() {

    }
}

package org.excercise.queue;

import java.util.Scanner;

/**
 * @author Manny
 * @create 2024-03-03-13:30
 * 使用数组模拟队列
 * 队列：队头进，队尾出
 */
public class ArrayQueueDemo {


    public static void main(String[] args) {
        CircleQueue3 queue = new CircleQueue3(3);
        Scanner scanner = new Scanner(System.in);
        String menu;
        int c;
        boolean flag = true;
        while (flag) {
            System.out.println("show:show queue");
            System.out.println("add:in queue");
            System.out.println("out:out queue");
            System.out.println("get:get front");
            System.out.println("exit:exit");
            System.out.println("请输入选项：");
            menu = scanner.next();
            switch (menu) {
                case "show": {
                    queue.print();
                    break;
                }
                case "add": {
                    c = scanner.nextInt();
                    queue.inQueue(c);
                    break;
                }
                case "out": {
                    queue.outQueue();
                    break;
                }
                case "get": {
                    queue.getHead();
                    break;
                }
                case "exit": {
                    System.out.println("退出");
                    flag = false;
                    break;
                }

            }
        }
    }

}


class ArrayQueue {

    int maxSize; //队列最大容量

    int front; //队头指针

    int rear; //队尾指针

    int[] arr; //储存数据数组


    /**
     * 构造器并初始化空队列
     *
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1; //front指向队头的前一个位置
        this.rear = -1;  //rear指向队尾
    }

    /**
     * 判满
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 入队
     *
     * @param x
     */
    public void inQueue(int x) {
        if (!isFull()) {
            rear++;
            arr[rear] = x;
        } else {
            System.out.println("队列已满");
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public void outQueue() {
        if (!isEmpty()) {
            System.out.println(arr[++front] + "正在出队");

        } else {
            throw new RuntimeException("队列为空");
        }
    }

    /**
     * 获取队头数据
     *
     * @return
     */
    public int getHead() {
        if (!isEmpty()) {
            return arr[++front];
        } else {
            throw new RuntimeException("队列为空");
        }
    }

    public void print() {
        System.out.println("此时队列为：");
        for (int i = (front + 1); i <= rear; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println("front = " + front + ";rear = " + rear);

    }
}


/**
 * 循环队列：会产生队空和队满 判定相同 的问题。
 * 约定front指向队头前一个位置，rear指向队尾
 * 解决方法：浪费一个存储单元
 *          增加count计数器
 *          增加tag标记
 * 有效数据个数：(rear+maxSize-front)%maxSize
 */
//循环队列 - 浪费一个存储单元
class CircleQueue1 {

    int maxSize; //队列最大容量

    int front; //队头指针

    int rear; //队尾指针

    int[] arr; //储存数据数组


    /**
     * 构造器并初始化空队列
     *
     * @param maxSize
     */
    public CircleQueue1(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 判满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 入队
     *
     * @param x
     */
    public void inQueue(int x) {
        if (!isFull()) {
            rear = (rear + 1) % maxSize;
            arr[rear] = x;
        } else {
            System.out.println("队列已满");
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public void outQueue() {
        if (!isEmpty()) {
            front = (front + 1) % maxSize;
            System.out.println(arr[front] + "正在出队");
            arr[front] = 0;

        } else {
            System.out.println("队列为空");
        }
    }

    /**
     * 获取队头数据
     *
     * @return
     */
    public void getHead() {
        if (!isEmpty()) {
            System.out.println("队头元素为：" + arr[(front + 1) % maxSize]);
        } else {
            System.out.println("队列为空");
        }

    }

    public void print() {
        System.out.println("此时队列为：");
        for (int item : arr) {
            System.out.printf("%d\t", item);
        }
        System.out.println("front = " + front + ";rear = " + rear);

    }
}

//循环队列 - 使用标志flag
class CircleQueue2 {

    int maxSize; //队列最大容量

    int front; //队头指针

    int rear; //队尾指针

    int[] arr; //储存数据数组

    //队满标志
    boolean flag;


    /**
     * 构造器并初始化空队列
     *
     * @param maxSize
     */
    public CircleQueue2(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
        this.flag = false;
    }

    /**
     * 判满
     *
     * @return
     */
    public boolean isFull() {
        return front == rear && flag;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear && !flag;
    }

    /**
     * 入队
     *
     * @param x
     */
    public void inQueue(int x) {
        if (!isFull()) {
            rear = (rear + 1) % maxSize;
            arr[rear] = x;
        } else {
            System.out.println("队列已满");
        }
        //置标
        if (front == rear) {
            flag = true;
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public void outQueue() {
        if (!isEmpty()) {
            front = (front + 1) % maxSize;
            System.out.println(arr[front] + "正在出队");
            arr[front] = 0;

        } else {
            System.out.println("队列为空");
        }
        if (front == rear) {
            flag = false;
        }
    }

    /**
     * 获取队头数据
     *
     * @return
     */
    public void getHead() {
        if (!isEmpty()) {
            System.out.println("队头元素为：" + arr[(front + 1) % maxSize]);
        } else {
            System.out.println("队列为空");
        }

    }

    public void print() {
        System.out.println("此时队列为：");
        for (int item : arr) {
            System.out.printf("%d\t", item);
        }
        System.out.println("front = " + front + ";rear = " + rear);

    }
}


//循环队列 - 使用count计数
class CircleQueue3 {

    int maxSize; //队列最大容量

    int front; //队头指针

    int rear; //队尾指针

    int[] arr; //储存数据数组

    int count; //计数器


    /**
     * 构造器并初始化空队列
     *
     * @param maxSize
     */
    public CircleQueue3(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    /**
     * 判满
     *
     * @return
     */
    public boolean isFull() {
        return count == maxSize;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 入队
     *
     * @param x
     */
    public void inQueue(int x) {
        if (!isFull()) {
            rear = (rear + 1) % maxSize;
            arr[rear] = x;
            count++;
        } else {
            System.out.println("队列已满");
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public void outQueue() {
        if (!isEmpty()) {
            front = (front + 1) % maxSize;
            System.out.println(arr[front] + "正在出队");
            arr[front] = 0;
            count--;
        } else {
            System.out.println("队列为空");
        }
    }

    /**
     * 获取队头数据
     *
     * @return
     */
    public void getHead() {
        if (!isEmpty()) {
            System.out.println("队头元素为：" + arr[(front + 1) % maxSize]);
        } else {
            System.out.println("队列为空");
        }

    }

    public void print() {
        System.out.println("此时队列为：");
        for (int item : arr) {
            System.out.printf("%d\t", item);
        }
        System.out.println("front = " + front + ";rear = " + rear);

    }
}



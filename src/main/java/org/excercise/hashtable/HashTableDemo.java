package org.excercise.hashtable;

import java.util.Scanner;

/**
 * @author Manny
 * @create 2024-03-14-17:00
 * 哈希表:数组+链表/二叉树 + 散列函数
 * 通过散列函数将要查找元素关键码值映射到表中的一个位置来访问记录，以加快查找的速度。
 */
public class HashTableDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTable hashTable = new HashTable();
        int menu;
        int id;
        String name;
        while (true) {
            System.out.println("***********菜单选项如下：*************");
            System.out.println("1:创建并初始化哈希表");
            System.out.println("2:添加元素");
            System.out.println("3:查找元素");
            System.out.println("4:删除元素");
            System.out.println("5:更新元素");
            System.out.println("6:打印元素");
            System.out.println("退出");
            menu = scanner.nextInt();
            if (menu == 1) {
                hashTable.init(20);
                System.out.println("哈希表初始化完成");
            } else if (menu == 2) {
                System.out.println("请输入id：");
                id = scanner.nextInt();
                System.out.println("请输入姓名：");
                name = scanner.next().trim();
                Employee employee = new Employee(id, name);
                hashTable.add(employee);
            } else if (menu == 3) {
                System.out.println("请输入要查找的id：");
                id = scanner.nextInt();
                Employee employee = hashTable.query(id);
                System.out.println(employee);
            } else if (menu == 6) {
                hashTable.print();
            } else if (menu == 5) {
                System.out.println("请输入id：");
                id = scanner.nextInt();
                System.out.println("请输入姓名：");
                name = scanner.next().trim();
                hashTable.update(id, name);
            } else if (menu == 4) {
                System.out.println("请输入id：");
                id = scanner.nextInt();
                hashTable.delete(id);

            } else {
                break;
            }
        }
    }
}

class HashTable {
    //散列数组
    Node[] heads;

    /**
     * 初始化哈希表
     *
     * @param maxSize
     */
    public void init(int maxSize) {
        heads = new Node[maxSize];
        for (int i = 0; i < maxSize; i++) {
            Node head = new Node();
            heads[i] = head;
        }
    }

    /**
     * 散列函数，返回要查找元素所在链表位置
     *
     * @param id
     * @return
     */
    public int locate(int id) {
        return id % heads.length;
    }

    /**
     * 添加一个元素到哈希表中
     *
     * @param employee
     */
    public void add(Employee employee) {
        //定位需要存放的位置
        int pos = locate(employee.id);

        Node head = heads[pos];
        //头插法
        Node newEm = new Node(employee);
        newEm.next = head.next;
        head.next = newEm;
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    public Employee query(int id) {
        //找到了根据散列函数元素应该在的位置
        int pos = locate(id);
        //head指向链表头节点
        Node head = heads[pos];
        //进行顺序查找
        //没到最后一个元素且元素不是要查找的元素，向前移
        Node tmp = head.next;
        while (tmp != null && id != tmp.employee.id) {
            tmp = tmp.next;
        }
        if (tmp == null) { //没有找到
            return null;
        } else { //找到了
            return tmp.employee;
        }
    }

    /**
     * 更新元素的值
     *
     * @param id
     * @param name
     */
    public void update(int id, String name) {

        Employee em = query(id);
        if (em != null) {
            em.name = name;
        } else {
            System.out.println("没有该元素");
        }
    }

    /**
     * 删除元素
     *
     * @param id
     */
    public void delete(int id) {
        //找到了根据散列函数元素应该在的位置
        int pos = locate(id);
        //head指向链表头节点
        Node head = heads[pos];
        //进行顺序查找
        //没到最后一个元素且元素不是要查找的元素，向前移
        Node cur = head.next;
        Node pre = head;
        while (cur != null && id != cur.employee.id) {
            pre = pre.next;
            cur = cur.next;
        }
        if (cur == null) {
            System.out.println("没有该元素");
        } else {
            pre.next = cur.next;
            cur.next = null;
        }
    }

    //展示哈希表
    public void print() {
        for (Node head : heads) {
            if (head.next != null) {
                System.out.println();
            }
            while (head.next != null) {
                System.out.printf("%d ->", head.next.employee.id);
                head = head.next;
            }
        }
    }
}

class Node {
    Employee employee;
    Node next;

    public Node(Employee employee) {
        this.employee = employee;
    }

    public Node() {

    }
}


class Employee {
    int id;
    String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID为" + id + "的雇员姓名为：" + name;
    }

}

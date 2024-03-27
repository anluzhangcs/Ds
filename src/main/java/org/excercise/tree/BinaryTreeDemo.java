package org.excercise.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Manny
 * @create 2024-03-14-21:51
 * 前言：数组和链表的缺点
 * 数组：需要扩容、可能需要大量移动
 * 链表：查找麻烦
 * 二叉树
 * 存储结构：完全二叉树或者满二叉树可以采用数组存储
 * 第n个节点 左孩子 2n+1
 * 右孩子 2n+2
 * 父节点 (n-1)/2
 * 可以以链表的方式储存，每个节点一个数据域，两个指针域
 * a
 * b        c
 * d    e
 * a b d # # e # # c # #
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree<String> bt = new BinaryTree<>();
        Scanner scanner = new Scanner(System.in);
        String menu;
        String str;
        while (true) {
            System.out.println("***********菜单选项如下：*************");
            System.out.println("create:创建二叉树");
            menu = scanner.next().trim();
            System.out.println(menu);
            if (menu.equals("create")) {
                System.out.println("请输入二叉树序列：空格作为分隔符；#作为结束符");
                //读上一行的换行
                scanner.nextLine();
                str = scanner.nextLine();
                //str = scanner.next();
                System.out.println(str);
                String[] items = str.split(" ");
                System.out.println(Arrays.toString(items));
                bt.init(items);
            } else if (menu.equals("pre")) {
                System.out.println("前序遍历：");
                bt.preTraverse();
            } else if (menu.equals("in")) {
                System.out.println("中序遍历：");
                bt.inTraverse();
            } else if (menu.equals("post")) {
                System.out.println("后序遍历：");
                bt.postTraverse();
            } else if (menu.equals("level")) {
                System.out.println("层序遍历：");
                bt.levelTraverse();
            } else if (menu.equals("high")) {
                System.out.println("获取高度");
                System.out.println(bt.depth());
            } else if (menu.equals("leaf")) {
                System.out.println("叶子数目：");
                System.out.println(bt.count());
            } else if (menu.equals("show")) {
                System.out.println("顺序存储：");
                int count = bt.getCount();
                bt.setItems(new Node[count]);
                bt.toArray();
                for (Node<String> item : bt.getItems()) {
                    System.out.println(item.getData());
                }
            } else if (menu.equals("count")) {
                System.out.println("节点数目：");
                System.out.println(bt.getCount());
            } else if (menu.equals("full")) {
                System.out.println("是否是满二叉树：");
                System.out.println(bt.isFull());
            } else if (menu.equals("copy")) {
                System.out.println("复制二叉树：");
                Node root = bt.copy();
                BinaryTree<String> newBt = new BinaryTree<>();
                newBt.setRoot(root);
                newBt.preTraverse();

            } else if (menu.equals("exit")) {
                break;
            }
        }

    }

}

/**
 * 节点类
 *
 * @param <T>
 */
class Node<T> {
    //数据域
    private T data;
    //左子树
    private Node<T> left;

    private int leftType;
    //右子树
    private Node<T> right;
    private int rightType;

    public Node(T data) {
        this.data = data;
    }

    public Node() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}

/**
 * 二叉树类
 *
 * @param <T>
 */
class BinaryTree<T> {
    //根节点
    private Node<T> root;

    //辅助创建
    static int index = 0;

    //节点数目
    static int count = 0;

    //
    private Node<T>[] items;

    public Node<T>[] getItems() {
        return items;
    }

    public void setItems(Node<T>[] items) {
        this.items = items;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public BinaryTree() {
    }

    public void init(T[] items) {
        root = createBT(items);
    }


    public int depth() {
        return getDepth(root);
    }

    public int count() {
        return getLeafCount(root);
    }

    /**
     * 根据输入序列创建二叉树结构
     * 注意
     *
     * @param items
     */
    public Node createBT(T[] items) {
        //递归退出条件
        if (items[index].equals("#")) {
            index++;
            return null;
        }
        Node node = new Node(items[index]);
        index++;
        //递归创建左子树
        node.setLeft(createBT(items));

        //递归创建左子树
        node.setRight(createBT(items));

        return node;
    }


    public void preTraverse() {
        preTraverse(root);
    }

    public void inTraverse() {
        inTraverse(root);
    }

    public void postTraverse() {
        postTraverse(root);
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public void preTraverse(Node root) {

        //递归退出条件，回溯
        if (root == null) {
            return;
        }
        //先遍历根节点
        visit(root);
        //递归遍历左子树
        preTraverse(root.getLeft());
        //递归遍历右子树
        preTraverse(root.getRight());

    }

    /**
     * 遍历时对结点的操作
     *
     * @param root
     */
    private void visit(Node root) {
        System.out.printf("%s\t", root.getData());
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inTraverse(Node root) {
        //递归退出条件，回溯
        if (root == null) {
            return;
        }
        //先递归遍历左子树
        inTraverse(root.getLeft());
        //遍历根节点
        visit(root);
        //递归遍历右子树
        inTraverse(root.getRight());
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postTraverse(Node root) {
        //递归退出条件，回溯
        if (root == null) {
            return;
        }
        //递归遍历左子树
        postTraverse(root.getLeft());
        //递归遍历右子树
        postTraverse(root.getRight());
        //遍历根节点
        visit(root);
    }

    /**
     * 层序遍历，需要借助队列
     */
    public void levelTraverse() {
        Queue<Node> queue = new ArrayDeque<>();
        //首先根节点入队
        if (root != null) {
            queue.add(root);
        }
        //只要队列不为空，先出队，再将左右孩子入队
        while (!queue.isEmpty()) {
            //出队
            Node node = queue.poll();
            visit(node);
            //将左右孩子入队
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }


    /**
     * 求二叉树深度
     *
     * @return
     */
    public int getDepth(Node node) {
        //递归退出条件
        if (node == null) {
            return 0;
        }
        int leftDepth = getDepth(node.getLeft()) + 1;
        int rightDepth = getDepth(node.getRight()) + 1;
        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }

    /**
     * 获取叶子节点数目
     *
     * @param node
     * @return
     */
    public int getLeafCount(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.getRight() == null && node.getLeft() == null) {
            return 1;
        }
        int leftCount = getLeafCount(node.getLeft());
        int rightCount = getLeafCount(node.getRight());
        return leftCount + rightCount;
    }

    public void toArray(Node node, Node[] items, int i) {

        if (node == null) {
            return;
        }
        //把节点放入数组
        items[i] = node;
        if ((2 * i + 1) < items.length) {

            toArray(node.getLeft(), items, 2 * i + 1);
        }
        if ((2 * i + 2) < items.length) {
            toArray(node.getRight(), items, 2 * i + 2);
        }
    }

    public void toArray() {
        toArray(root, items, 0);
    }


    public int getCount() {
        getCount(root);
        return count;
    }

    public void getCount(Node node) {
        //
        if (node == null) {
            return;
        }
        count++;
        getCount(node.getLeft());
        getCount(node.getRight());

    }

    public boolean isFull() {
        return isFull(root);
    }

    /**
     * 判断是否完全二叉树
     *
     * @return
     */
    public boolean isFull(Node node) {
        if (node == null) {
            return true;
        }
        //只有一个孩子节点为空
        if ((node.getLeft() == null && node.getRight() != null) || (node.getLeft() != null && node.getRight() == null)) {
            return false;
        }
        //递归
        return isFull(node.getLeft()) && isFull(node.getRight());
    }

    /**
     * 复制一个二叉树
     *
     * @param newNode
     * @param oldNode
     */
    public Node copy(Node newNode, Node oldNode) {
        if (oldNode == null) {
            return null;
        }

        newNode = new Node(oldNode.getData());
        newNode.setLeft(copy(newNode.getLeft(), oldNode.getLeft()));
        newNode.setRight(copy(newNode.getRight(), oldNode.getRight()));
        return newNode;
    }

    public Node copy() {
        Node newRoot = null;
        copy(newRoot, root);
        return newRoot;
    }


    /**
     * 删除值为value的子树
     *
     * @param father
     * @param node
     * @param value
     */
    public void delete(Node father, Node node, T value) {
        if (node == null) {
            return;
        }
        //
        if (node.getData().equals(value)) {
        }
    }

    /**
     * 前序线索化建立
     * 线索：左孩子指向前驱 右孩子指向后继
     *
     * @param node
     */
    public void threadBTree(Node pre, Node node) {
        if (node == null) {
            return;
        }
        //左孩子为空，设置前驱
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        } else {
            threadBTree(node, node.getLeft());
        }

        //右孩子为空，指向后继
        if (node.getRight() == null) {
            //node.setRight();
        } else {
            threadBTree(node, node.getRight());
        }
    }
}

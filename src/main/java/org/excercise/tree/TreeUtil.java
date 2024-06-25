package org.excercise.tree;

import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TreeUtil.java
 *
 * @author zh
 * @since 2024/6/17 on 17:20
 */
public class TreeUtil {

    public static TreeNode inAndPre(int[] preorder, int[] inorder, int inStart, int inEnd){
        return null;
    }

    public static TreeNode inAndPost(int[] postorder, int[] inorder, int inStart, int inEnd){
        return null;
    }

    /**
     *
     * @param levelorder
     * @return
     * eg. [1,2,3,4,null,5,6,null,null,7]
     */
    public static TreeNode levelBuild(Integer[] levelorder){

        int length = levelorder.length;
        if (levelorder == null || length == 0) {
            return null;
        }
        int idx = 0;
        TreeNode root = new TreeNode(levelorder[idx]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty() && idx < length-1) {
            TreeNode node = queue.poll();
            Integer leftVal = levelorder[++idx];
            if (leftVal != null) {
                node.left = new TreeNode(leftVal);
                queue.offer(node.left);
            }

            if (idx == length - 1) {
                break;
            }

            Integer rightVal = levelorder[++idx];
            if (rightVal != null) {
                node.right = new TreeNode(rightVal);
                queue.offer(node.right);
            }
        }

        return root;
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public static List<Integer> levelorder(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return ans;
    }

    public static void preorder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        preorder(root.left, ans);
        preorder(root.right, ans);
    }

    public static void inorder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        inorder(root.left, ans);
        ans.add(root.val);
        inorder(root.right, ans);
    }

    public static void postorder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        postorder(root.left, ans);
        postorder(root.right, ans);
        ans.add(root.val);
    }

    public static void showTree(TreeNode root) {
        List<Integer> preorder = new LinkedList<>();
        List<Integer> inorder = new LinkedList<>();
        List<Integer> postorder = new LinkedList<>();
        preorder(root, preorder);
        inorder(root, inorder);
        postorder(root, postorder);
        List<Integer> levelorder = levelorder(root);
        System.out.println("=========树的结构==========");
        System.out.println("前序遍历：" + preorder.toString());
        System.out.println("中序遍历：" + inorder.toString());
        System.out.println("后序遍历：" + postorder.toString());
        System.out.println("层序遍历：" + levelorder.toString());
        System.out.println("=======================");
    }

}

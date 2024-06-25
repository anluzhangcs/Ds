package org.excercise.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Solution617.java
 * 合并两棵二叉树
 *
 * @author zh
 * @since 2024/6/24 on 20:54
 */
class Solution617 {

    public static void main(String[] args) {
        Solution617 solution = new Solution617();
        Integer[] nums1 = {1, 3, 2, 5};
        Integer[] nums2 = {2, 1, 3, null, 4, null, 7};
        TreeNode root1 = TreeUtil.levelBuild(nums1);
        TreeNode root2 = TreeUtil.levelBuild(nums2);

        TreeUtil.showTree(root1);
        TreeUtil.showTree(root2);

        solution.mergeTrees(root1, root2);
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root1);
        queue2.offer(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1 != null && node2 != null) {
                node1.val += node2.val;
            } else if (node1 == null) {
                node1 = node2;
                continue;
            } else {
                continue;
            }
            queue1.offer(node1.left);
            queue1.offer(node1.right);
            queue2.offer(node2.left);
            queue2.offer(node2.right);
        }

        return root1;
    }
}

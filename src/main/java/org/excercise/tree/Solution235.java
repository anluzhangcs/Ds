package org.excercise.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zh
 * @create 2024-06-26-10:13
 */
class Solution235 {

    public static void main(String[] args) {
        Solution235 solution = new Solution235();
        Integer[] nums = {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};
        TreeNode root = TreeUtil.levelBuild(nums);
        TreeNode p = null, q = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == 4) {
                p = node;
            }
            if (node.val == 5) {
                q = node;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        TreeNode ans = solution.lowestCommonAncestor(root, p, q);
        System.out.println(ans.val);

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) { // 始终保持p的值较小
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        return recur(root, p, q);
    }

    private TreeNode recur(TreeNode root, TreeNode low, TreeNode high) {
        if (root == null) {
            return null;
        }
        if (root == low || root == high) {
            return root;
        }
        if (root.val > low.val && root.val < high.val) {
            return root;
        } else if (root.val > high.val) {
            return recur(root.left, low, high);
        } else {
            return recur(root.right, low, high);
        }
    }
}

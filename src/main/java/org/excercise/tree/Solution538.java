package org.excercise.tree;

import java.util.Stack;

/**
 * @author zh
 * @create 2024-06-26-20:39
 */
class Solution538 {

    public static void main(String[] args) {
        Solution538 solution = new Solution538();
        Integer[] nums = {4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8};
        TreeNode root = TreeUtil.levelBuild(nums);
        TreeUtil.showTree(root);
        TreeNode newTree = solution.convertBST(root);
        TreeUtil.showTree(newTree);

    }

    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                sum += cur.val;
                cur.val = sum;
                cur = cur.left;
            }
        }
        return root;
    }
}

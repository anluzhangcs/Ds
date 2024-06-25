package org.excercise.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution654.java
 *
 * @author zh
 * @since 2024/6/17 on 19:52
 */
public class Solution654 extends Solution {
    Map<Integer, Integer> dict = new HashMap<>();

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            dict.put(nums[i], i);
        }
        return recur(nums, 0, len - 1);
    }

    private TreeNode recur(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        // 找到最大值
        int max = nums[start];
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        TreeNode root = new TreeNode(max);
        int rootIdx = dict.get(max);

        root.left = recur(nums, start, rootIdx - 1);
        root.right = recur(nums, rootIdx + 1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] tree = new int[]{3, 2, 1, 6, 0, 5};
        Solution654 solution = new Solution654();
        TreeNode root = solution.constructMaximumBinaryTree(tree);
        TreeUtil.showTree(root);

    }
}

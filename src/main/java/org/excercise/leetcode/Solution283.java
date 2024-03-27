package org.excercise.leetcode;

/**
 * @author Manny
 * @create 2024-03-18-14:16
 */
class Solution283 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void moveZeroes(int[] nums) {
        //置空
        if (nums.length == 0 || nums == null) {
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        //将后面的元素置0
        while (j < nums.length) {
            nums[j] = 0;
            j++;
        }
    }
}

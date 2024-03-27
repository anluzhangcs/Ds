package org.excercise.leetcode;

/**
 * @author Manny
 * @create 2024-03-18-17:00
 */
class Solution977 {
    public static void main(String[] args) {
        int[] squares = sortedSquares(new int[]{-7, -3, 2, 3, 11});
        for (int square : squares) {
            System.out.println(square);

        }
    }

    public static int[] sortedSquares(int[] nums) {
        int split = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                split++;
            }
            nums[i] *= nums[i];
        }
        //split为值小于0的个数，以split为界将数组分为（0，split-1）递减（split，nums.length）递增

        int i = split - 1;
        int j = split;
        int k = 0;
        //新数组
        int[] arr = new int[nums.length];
        while (i >= 0 && j < nums.length) {
            if (nums[i] < nums[j]) {
                arr[k++] = nums[i];
                i--;
            } else {
                arr[k++] = nums[j];
                j++;
            }
        }
        while (i >= 0) {
            arr[k++] = nums[i];
            i--;
        }
        while (j < nums.length) {
            arr[k++] = nums[j];
            j++;
        }

        return arr;

    }
}

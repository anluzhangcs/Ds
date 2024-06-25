package org.excercise.leetcode;

import java.util.*;

/**
 * @author Manny
 * @create 2024-03-17-14:59
 */
class Solution1 {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ints = searchRange(nums, 8);
        System.out.println("sfd");
        System.out.println(Arrays.toString(ints));
        Map<Integer, Integer> dict = new HashMap<>();
        dict.values().stream();
        List<Integer> ans = new LinkedList<>();
        Integer[] array = ans.toArray(new Integer[ans.size()]);

    }

    //非递减数组 nums[i]<=nums[i+1]
    //所以target位置肯定是相邻的
    //找出一个target，向前向后找他们的结束位置
    public static int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        int i;
        int left = -1;
        int right = -1;
        int[] res = {left, right};
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                //向前找开始位置
                i = mid - 1;
                while (i >= low && nums[i] == target) {
                    i--;
                }
                left = i + 1;
                //向后找结束位置
                i = mid + 1;
                while (i <= high && nums[i] == target) {
                    i++;
                }
                right = i - 1;
                res[0] = left;
                res[1] = right;
                return res;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            }
        }
        return res;
    }
}
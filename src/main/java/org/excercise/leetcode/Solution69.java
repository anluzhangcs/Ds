package org.excercise.leetcode;

/**
 * @author Manny
 * @create 2024-03-17-20:06
 */
public class Solution69 {
    public static void main(String[] args) {
        System.out.println(mySqrt(2147395600));
    }

    // 使用二分法
    public static int mySqrt(int x) {
        // 负数不能开平方
        if (x < 0) {
            return -1;
        }
        // 特殊值判断
        if (x == 1) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }
        // 平方根下界
        int left = 1;
        // 平方根上界
        int right = x;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            // 转成long类型避免x过大导致溢出
            if ((long) (mid * mid) == x) { // 此时mid正好是平方根
                return mid;
            } else if ((long) (mid * mid) > x) { // mid的平方大于x，mid肯定不是解
                right = mid - 1;
            } else if ((long) (mid * mid) < x) { // mid的平方小于x，但是mid可能是解。
                left = mid + 1;
            }
        }
        return left - 1;
    }
}

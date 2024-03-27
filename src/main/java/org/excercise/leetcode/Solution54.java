package org.excercise.leetcode;

import java.util.Arrays;

/**
 * @author Manny
 * @create 2024-03-23-13:49
 */
class Solution54 {
    public static void main(String[] args) {
        int[][] matrix = {{
                1, 2, 3, 4
        }, {
                5, 6, 7, 8
        }, {
                9, 10, 11, 12
        }};
        int[] ints = spiralOrder(matrix);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] spiralOrder(int[][] array) {
        // 定义左右上下边界
        int l = 0, r = array[0].length - 1, t = 0, b = array.length - 1;
        //总的元素数量
        int total = (array[0].length) * (array.length);
        int[] res = new int[total];
        int k = 0;
        while (k < total) {
            // 从左到右
            for (int i = l; i <= r && k < total; i++) {
                res[k++] = array[t][i];
            }

            t++;
            // 从上到下
            for (int i = t; i <= b && k < total; i++) {
                res[k++] = array[i][r];
            }
            r--;
            // 从右到左
            for (int i = r; i >= l && k < total; i--) {
                res[k++] = array[b][i];
            }
            b--;
            // 从下到上
            for (int i = b; i >= t && k < total; i--) {
                res[k++] = array[i][l];
            }
            l++;
        }
        return res;
    }
}

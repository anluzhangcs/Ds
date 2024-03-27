package org.excercise.leetcode;

import java.util.HashMap;

/**
 * @author Manny
 * @create 2024-03-21-16:26
 */
class Solution904 {
    public static void main(String[] args) {
        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(totalFruit(fruits));
    }

    // fruits[i]表示一种水果类型，只有两个篮子，每个篮子只能放一种类型水果，每种类型只能摘一个果子
    // 求能摘到水果个数的最大值
    public static int totalFruit(int[] fruits) {

        //判空
        if (fruits == null) {
            return -1;
        }

        //边界值判断
        if (fruits.length <= 2) {
            return fruits.length;
        }

        //i指向开始位置
        int i = 0;
        //j指向结束位置
        int j;
        //res用来记录开始和结束位置
        int[] res = new int[2];

        //map用来存储水果种类和个数
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (j = 0; j < fruits.length; j++) {
            if (map.containsKey(fruits[j])) {
                map.put(fruits[j], map.getOrDefault(fruits[j], 1) + 1);
            } else if (!map.containsKey(fruits[j]) && map.size() < 2) {
                map.put(fruits[j], 1);
            } else {
                //记录开始结束位置
                if (j - i > res[1] - res[0]) {
                    res[0] = i;
                    res[1] = j;
                }
                //不断调整开始位置,临界点为这种水果数量为1
                while (map.getOrDefault(fruits[i], 1) > 1) {
                    map.put(fruits[i], map.getOrDefault(fruits[i], 1) - 1);
                    i++;
                }
                //如果数量为1，说明删掉这个元素后 map 就有空放另一种水果了
                map.remove(fruits[i]);
                //注意i还指向此种水果在，需要+1
                i++;
                //将新品种放入
                map.put(fruits[j], 1);
            }
        }
        //最后也要判断一次，防止最后没有进入else
        if (j - i > res[1] - res[0]) {
            res[0] = i;
            res[1] = j;
        }
        int[][] arr = {{1}};
        return res[1] - res[0];
    }
}
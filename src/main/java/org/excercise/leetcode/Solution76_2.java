package org.excercise.leetcode;

import java.util.HashMap;

/**
 * @author Manny
 * @create 2024-03-21-11:56
 */
class Solution76_2 {
    public static void main(String[] args) {
        String minStr = minWindow("acbbaca", "aba");
        System.out.println(minStr);
    }

    public static String minWindow(String s, String t) {
        // 维护一个need字典，其中包含每个元素所需的数量，初始值为t中每个元素的数量
        HashMap<Character, Integer> need = new HashMap<>();
        // 维护一个needCount，它的值为各元素need总和
        Integer needCount = t.length();
        // 初始化need字典
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        // 定义最小结果集时的左右边界，初始化边界长度为最大值
        int[] minResult = new int[]{0, Integer.MAX_VALUE};
        //i指向开始位置
        int i = 0;
        //j指向结束位置
        int j;
        for (j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            //如果需要该元素
            if (need.getOrDefault(c, 0) > 0) {
                needCount--;
            }
            //更新need字典
            need.put(c, need.getOrDefault(c, 0) - 1);

            //如果needCount==0，说明已经包含t中所有元素
            //此时需要做的是不断调整开始位置，使获得minStr
            if (needCount == 0) {
                while (true) {
                    c = s.charAt(i);
                    //处于一个临界点,
                    if (need.getOrDefault(c, 0) == 0) {
                        break;
                    }
                    need.put(c, need.getOrDefault(c, 0) + 1);
                    //不断调整开始位置
                    i++;

                }
                //记录这个字符串,注意substring获得左闭右开的字串
                // 左边界也更新完了，这时该更新结果了,覆盖res
                if (j - i < minResult[1] - minResult[0]) {
                    minResult[1] = j;
                    minResult[0] = i;
                }
                //
                c = s.charAt(i);
                //维护need和needCount
                needCount++;
                need.put(c, need.getOrDefault(c, 0) + 1);
                i++;
            }

        }
        // 超过边界，返回空串
        if (minResult[1] > s.length()) {
            return "";
        } else {
            return s.substring(minResult[0], minResult[1] + 1);
        }

    }
}

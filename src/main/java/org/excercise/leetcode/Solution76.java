package org.excercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Manny
 * @create 2024-03-18-22:50
 */
class Solution76 {

    public static void main(String[] args) {
        String minStr = minWindow("acbbaca", "aba");
        System.out.println(minStr);
    }

    public static String minWindow(String s, String t) {
        // 参数校验
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        // 保存t字符出现的次数，表示每个字符待需要的数量
        Map<Character, Integer> tMap = new HashMap<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 判断窗口是否包含了t的所有元素，避免每次去遍历tMap，增加耗时
        int needCnt = t.length();
        // 定义最小结果集时的左右边界，初始化边界长度为最大值
        int[] minResult = new int[]{0, Integer.MAX_VALUE};
        int length = s.length();
        // 定义滑动窗口左右边界，右边界开始移动
        for (int i = 0, j = 0; j < length; j++) {
            char c = s.charAt(j);
            // 包含t，待需要的数量减一
            if (tMap.getOrDefault(c, 0) > 0) {
                needCnt--;
            }
            // 同时map需要的字符数量减一
            tMap.put(c, tMap.getOrDefault(c, 0) - 1);
            // 都包含了，此时右边界j不动，开始移动左边界，尝试缩小窗口
            if (needCnt == 0) {

                while (true) {
                    c = s.charAt(i);
                    // 左边界字符已经满足了，不再需要了，则退出循环，没办法继续缩小了
                    // 否则继续缩小，那么会执行下面的+1，所需要的字符又增加了
                    if (tMap.get(c) == 0) {
                        break;
                    }
                    // 左边界字符
                    // map还有多余数量可以缩小
                    // 缩小左边界，该字符所需要的数量+1
                    tMap.put(c, tMap.getOrDefault(c, 0) + 1);
                    i++;
                }
                // 左边界也更新完了，这时该更新结果了,覆盖res
                if (j - i < minResult[1] - minResult[0]) {
                    minResult[1] = j;
                    minResult[0] = i;
                }
                c = s.charAt(i);
                //将左边界右移,执行下一个窗口
                // 由于左边界是t需要的字符，右移后，需要更新tMap和needCnt，表示还需要增加一个字符
                tMap.put(c, tMap.getOrDefault(c, 0) + 1);
                needCnt++;
                i++;
            }
        }
        // 超过边界，返回空串
        if (minResult[1] > length) {
            return "";
        } else {
            return s.substring(minResult[0], minResult[1] + 1);
        }
    }
}

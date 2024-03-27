package org.excercise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manny
 * @create 2024-03-27-16:56
 */
class Solution438 {

    public static void main(String[] args) {
        findAnagrams("abab", "ab");
    }

    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList<>();

        //用来记录p中每个字符出现的次数
        int[] scnt = new int[26];
        int[] pcnt = new int[26];

        for (char c : p.toCharArray()) {
            scnt[c - 'a']++;
            pcnt[c - 'a']++;
        }
        int total = p.length();

        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (scnt[c - 'a'] > 0) {
                scnt[c - 'a']--;
                total--;
            }
            //找到了一个
            if (total == 0) {
                ans.add(i);
                if (scnt[c - 'a'] > 0) {
                    total++;
                }
                i++;
            }
        }
        return ans;
    }
}

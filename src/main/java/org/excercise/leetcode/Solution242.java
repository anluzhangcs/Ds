package org.excercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Manny
 * @create 2024-03-24-16:06
 */
class Solution242 {

    public static void main(String[] args) {
        String s = "aa";
        String t = "aab";
        boolean anagram = canConstruct(s, t);
        System.out.println(anagram);
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() > ransomNote.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) - 1);
            if (map.get(ch) < 0) {
                return false;
            }
        }

        return true;
    }
}

package org.excercise.leetcode;

import java.util.*;

/**
 * @author Manny
 * @create 2024-03-27-14:27
 */
class Solution49 {
    public static void main(String[] args) {
        List<List<String>> lists = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();

        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            List<String> list = res.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            res.put(key, list);
        }

        return new ArrayList(res.values());
    }
}

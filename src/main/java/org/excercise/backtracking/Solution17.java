package org.excercise.backtracking;

import java.util.*;

/**
 * Solution17.java
 *
 * @author zh
 * @since 2024/6/28 on 16:10
 */
class Solution17 {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("b");
        sb.append("cd");
        System.out.println(sb.toString());
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
        Solution17 solution = new Solution17();
        List<String> strings = solution.letterCombinations("23");
        System.out.println(Arrays.toString(strings.toArray()));
    }
    List<String> ans = new LinkedList<>();
//    LinkedList<String> path = new LinkedList<>();
    StringBuilder path = new StringBuilder();
    Map<String,String> dict = new HashMap<>();
    public List<String> letterCombinations(String digits) {

        if(digits == null || digits.length() == 0){
            return ans;
        }
        dict.put("2","abc");
        dict.put("3","def");
        dict.put("4","ghi");
        dict.put("5","jkl");
        dict.put("6","mno");
        dict.put("7","pqrs");
        dict.put("8","tuv");
        dict.put("9","wxyz");
        backtracking(digits,0);
        return ans;
    }

    /**
     *
     * @param digits
     * @param digitsBegin 这一层的数字下标
     */
    private void backtracking(String digits, int digitsBegin){
        if(path.length() == digits.length()){
            ans.add(path.toString());
            return;
        }
        if (digitsBegin == digits.length()) {
            return;
        }
        // 获取数字对应的字母组合
        String letters = dict.get(digits.substring(digitsBegin,digitsBegin+1));

        for(int i = 0; i< letters.length(); i++){
            path.append(letters.substring(i,i+1));
            backtracking(digits,digitsBegin+1);
            // 回溯
            path.deleteCharAt(path.length()-1);
        }
    }
}

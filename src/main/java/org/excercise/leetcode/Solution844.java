package org.excercise.leetcode;

import java.util.Stack;

/**
 * @author Manny
 * @create 2024-03-18-14:26
 */
class Solution844 {

    public static void main(String[] args) {
        String str = "helloworld";
        String t = "abc";
        System.out.println(str.substring(0, 4));

        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));
    }

    // # 就是 backspace
    public static boolean backspaceCompare(String s, String t) {
        // 字符串都相等，肯定相等
        if (s.equals(t)) {
            return true;
        }

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        Stack<Character> stackS = new Stack<>();
        for (char ch : sArr) {
            if (ch == '#' && !stackS.isEmpty()) {
                stackS.pop();
            } else {
                stackS.push(ch);

            }
        }
        Stack<Character> stackT = new Stack<>();
        for (char ch : tArr) {
            if (ch == '#' && !stackT.isEmpty()) {
                stackT.pop();
            } else if (ch != '#') {
                stackT.push(ch);
            }
        }
        while (!stackS.isEmpty() && !stackT.isEmpty()) {
            if (stackS.pop() != stackT.pop()) {
                return false;
            }
        }
        if (!stackS.isEmpty() || !stackT.isEmpty()) {
            return false;
        }
        return true;
    }
}

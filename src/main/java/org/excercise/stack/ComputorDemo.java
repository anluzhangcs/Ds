package org.excercise.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Manny
 * @create 2024-03-08-12:55
 * 使用栈计算表达式
 * 中缀表达式
 * 前缀（波兰）表达式 只需要操作数栈，从右到左扫描表达式，操作数入栈，遇到操作符弹出两个数再入栈
 * 后缀（逆波兰）表达式 与前缀类似，但是从左到右扫描
 */
public class ComputorDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp;
        Calculator calculator = new Calculator();

        while (true) {
            System.out.println("请输入计算表达式:");
            exp = scanner.nextLine();
            if (exp.equals("exit")) {
                System.out.println("退出程序");
                break;
            } else {
                String suffixExp = calculator.toSuffixExp(exp);
                System.out.println(suffixExp);
                int res = calculator.calInSuffix(exp);
                System.out.println("结果是：" + res);

            }
        }


        scanner.close();

    }
}


class Calculator {

    /**
     * 通过后缀表达式计算
     * 从走到右扫描表达式，操作数直接入栈，遇到操作符弹出弹出两个操作数计算后入栈
     *
     * @param exp
     * @return
     */
    public int calInSuffix(String exp) {
        String suffixExp = toSuffixExp(exp);
        String[] strings = suffixExp.split(" ");
        //操作数栈
        Stack<Integer> numbers = new Stack<>();
        //LinkedStack<Integer> numbers = new LinkedStack<>();
        for (String item : strings) {
            if (item.matches("\\d+")) { //匹配多位数
                int num = Integer.parseInt(item);
                numbers.push(num);
            } else {
                Integer num2 = numbers.pop();
                Integer num1 = numbers.pop();
                Integer res = 0;
                if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                }
                numbers.push(res);
            }
        }
        return numbers.pop();
    }

    /**
     * 中缀表达式 ==> 后缀表达式
     * 使用两个栈，一个栈暂存结果，一个栈存运算符 和 括号
     * 遇到操作数，直接存入结果栈
     * 遇到操作符：如果优先级高于栈顶元素（包括'('），入栈
     * 操作符栈弹出栈顶元素，存入结果栈,继续判断，直至栈为空或优先级高于栈顶元素
     * 遇到（：直接存入运算符栈
     * 遇到）：不断弹出操作符栈并存入结果栈，直至）
     * eg. 10 + 5*2 - 100/(5+5)  ==> 10 5 2 * + 100 5 5 + / -
     *
     * @param prefixExp
     * @return
     */
    public String toSuffixExp(String prefixExp) {
        //操作符栈
        Stack<String> operator = new Stack<>();
        //LinkedStack<String> operator = new LinkedStack<>();
        //结果栈
        Stack<String> suffixExp = new Stack<>();
        //LinkedStack<String> suffixExp = new LinkedStack<>();
        String str = "";
        //用于暂存多位数，多位数之后可能是操作符、结束、）
        String tmpNum = "";
        for (int i = 0; i < prefixExp.length(); i++) {
            str = prefixExp.substring(i, i + 1);
            if (str.charAt(0) >= 48 && str.charAt(0) <= 57) { //是操作数
                tmpNum += str;
            } else if (isOperator(str.charAt(0))) { //是操作符
                if (!tmpNum.isEmpty()) { //存入操作数
                    suffixExp.push(tmpNum);
                    tmpNum = ""; //重置tmpNum
                }
                while (!operator.isEmpty()) {
                    //获取栈顶元素
                    String peek = operator.peek();
                    if (peek.charAt(0) == '(' || priority(str.charAt(0)) > priority(peek.charAt(0))) {
                        operator.push(str);
                        break;
                    } else {
                        String pop = operator.pop();
                        suffixExp.push(pop);
                    }
                }
                if (operator.isEmpty()) {
                    operator.push(str);
                }
            } else if (str.charAt(0) == '(') {
                //直接入栈
                operator.push(str);
            } else if (str.charAt(0) == ')') {
                if (!tmpNum.isEmpty()) { //存入操作数
                    suffixExp.push(tmpNum);
                    tmpNum = ""; //重置tmpNum
                }
                while (true) {
                    String pop = operator.pop();
                    if (pop.charAt(0) == '(') {
                        break;
                    } else {
                        suffixExp.push(pop);
                    }
                }
            }
        }
        if (!tmpNum.isEmpty()) { //存入操作数
            suffixExp.push(tmpNum);
            tmpNum = ""; //重置tmpNum
        }
        //弹出剩余的操作符
        while (!operator.isEmpty()) {
            String pop = operator.pop();
            suffixExp.push(pop);
        }
        String res = "";
        while (!suffixExp.isEmpty()) {
            res = suffixExp.pop() + " " + res;
        }
        System.out.println(res);
        return res;
    }

    /**
     * 判断操作符的优先级，注意（、）不算操作符
     *
     * @param ch
     * @return
     */
    public int priority(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else {
            throw new RuntimeException("不是合法的操作符");
        }
    }

    public boolean isOperator(char ch) {
        if (ch == '*' || ch == '/' || ch == '+' || ch == '-') {
            return true;
        }
        return false;
    }
}







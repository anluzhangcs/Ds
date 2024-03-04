package org.excercise.queue;

import java.util.Scanner;

/**
 * @author Manny
 * @create 2024-03-04-11:32
 * 详见 java.util.Scanner
 * Scanner类：一个简单的文本扫描器，可以扫描输入流、文件等中的数据
 * scanner使用分隔符将输入分割为tokens，默认分隔符是空格（whitespace）。
 * 可以使用不同的next方法将这些tokens转换为不同的类型:
 * next()和其他nextXXX() 一开始会跳过读取分隔符，读取到有效数据后根据分隔符进行分割
 * nextLine可以读取空格，即nextLine是读取一行数据,换行为结束符
 * hasNextXXX()判断后面是否还有符合条件的数据
 * next()和hasNext()都可能会堵塞等待进一步输入
 */
public class ScannerDemo {

    public static void main(String[] args) {
        //System.in（标准输入流），用来响应键盘输入
        Scanner scanner = new Scanner(System.in);

        String next;

        int nextInt;

        while (scanner.hasNext()) {
            next = scanner.next();
            System.out.println(next);
        }

        //nextLine可以读取空格，即nextLine是读取一行数据
        //while (scanner.hasNextLine()) {
        //    next = scanner.nextLine();
        //    System.out.println(next);
        //}

        //nextInt
        //while (scanner.hasNextInt()) {
        //    nextInt = scanner.nextInt();
        //    System.out.println(nextInt);
        //}


        //注意最后scanner需要关闭
        scanner.close();

    }

}

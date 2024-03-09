package org.excercise.stack;

/**
 * @author Manny
 * @create 2024-03-08-14:20
 * <p>
 * TODO 扩展知识 Java字符编码-Unicode
 * 参考链接：https://www.cnblogs.com/binarylei/p/10760233.html
 * ASCII（美国信息交换标准代码）是最早的一种编码方案，占用1字节。最高位没用，所以只能表示128个字符（0-127）
 * Unicode是一种字符集、一种编码方案。本质是给世界上每种文字系统的每一个字符分配一个唯一的整数，这样就不会冲突了
 *  代码点：代表这个字符的整数就是代码点
 * UTF（Unicode Transformation Format）：即定义Unicode字符集的二进制形式如何存储
 *  UTF-8：可变长编码方式，使用1-4个字节表示一个字符。
 *      单个字节，第一位设为0，可以完美实现对ASCII的兼容。
 *      对于需要使用 N 个字节来表示的字符（N > 1），第一个字节的前 N 位都设为 1，第 N + 1 位设为0，剩余的 N - 1 个字节的前两位都设位 10，剩下的二进制位则使用这个字符的 Unicode 码点来填充。
 *          eg. 010000-10FFFF	11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * <p>
 * Java 中 char 数据类型是定长的，其长度永远只有 16 位，采用UTF-16定长编码方式
 * 转义字符：用来表示那些不能显示的ASCII字符，如 \0 表示 NUT
 */
public class CharacterDemo {
    public static void main(String[] args) {
        char ch = '\u0061';
        System.out.printf("%c\n", ch);
    }
}

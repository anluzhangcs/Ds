package org.excercise.array;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Manny
 * @create 2024-02-29-12:51
 * 普通二维数组 <==> 稀疏数组(为了压缩数组,减少空间浪费)
 * 稀疏数组:
 *    0 rows cols count count是有效数据个数,rows cols为原数组大小
 *    1 i     j   value  i,j为坐标,value是具体值
 *      ....
 *
 */
public class SparseArray {

    public static void main(String[] args) {

        int[][] array = {{0, 0, 0, 1}, {0, 0, 2, 0}, {0, 3, 0, 0}, {4, 0, 0, 0}};
        System.out.println("原始数组:");
        printArray(array);
        int[][] target = toSparse(array);
        toSparseThenSave(array);

        //System.out.println("目标数组:");
        //printArray(target);
        //
        //int[][] normal = toNormal(target);
        int[][] normal = readThenToNormal(new File("chess.txt"));
        System.out.println("数组:");
        printArray(normal);

    }

    /**
     * 二维数组转稀疏数组
     * @param normalArray
     * @return
     */
    public static int[][] toSparse(int[][] normalArray) {

        //有效值计数器
        int count = 0;
        //最多的值
        int flag = 0;

        //
        int rows = normalArray.length;
        int cols = normalArray[0].length;
        int maxRows = rows * cols;
        int[][] tmp = new int[maxRows][3];

        //遍历原始二维数组,找出稀疏的几个值
        for (int i = 0; i < normalArray.length; i++) {
            for (int j = 0; j < normalArray[i].length; j++) {
                if (normalArray[i][j] != flag) {
                    count++;
                    tmp[count][0] = i;
                    tmp[count][1] = j;
                    tmp[count][2] = normalArray[i][j];
                }
            }
        }

        tmp[0][0] = rows;
        tmp[0][1] = cols;
        tmp[0][2] = count;

        int[][] sparseArray = new int[count+1][3];

        //copy数组
        if (count < maxRows) {
            for (int i = 0; i <= count; i++) {
                for (int j = 0; j < 3; j++) {
                    sparseArray[i][j] = tmp[i][j];
                }
            }
        }

        //返回稀疏数组
        return sparseArray;
    }

    /**
     * 二维数组转稀疏数组并保存到磁盘
     * @param normalArray
     * @return
     */
    public static void toSparseThenSave(int[][] normalArray) {

        //有效值计数器
        int count = 0;
        //最多的值
        int flag = 0;

        //
        int rows = normalArray.length;
        int cols = normalArray[0].length;
        int maxRows = rows * cols;
        int[][] tmp = new int[maxRows][3];

        //遍历原始二维数组,找出稀疏的几个值
        for (int i = 0; i < normalArray.length; i++) {
            for (int j = 0; j < normalArray[i].length; j++) {
                if (normalArray[i][j] != flag) {
                    count++;
                    tmp[count][0] = i;
                    tmp[count][1] = j;
                    tmp[count][2] = normalArray[i][j];
                }
            }
        }

        tmp[0][0] = rows;
        tmp[0][1] = cols;
        tmp[0][2] = count;

        int[][] sparseArray = new int[count+1][3];

        //copy数组
        if (count < maxRows) {
            for (int i = 0; i <= count; i++) {
                for (int j = 0; j < 3; j++) {
                    sparseArray[i][j] = tmp[i][j];
                }
            }
        }

        //保存稀疏数组
        File file = new File("chess.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(file);

            for (int[] row : sparseArray) {
                for (int item : row) {

                    writer.write(""+item);
                    writer.write("\t");
                }
                writer.write("\n");
            }

            //将缓冲区内容强制写出
            writer.flush();
            //关闭输出流
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 稀疏数组 ==> 普通二维数组
     * @param array
     * @return
     */
    public static int[][] toNormal(int[][] array) {
        //根据稀疏数组第一行构建出二维数组
        int[][] normal = new int[array[0][0]][array[0][1]];

        //对二维数组进行赋值
        for (int i = 1; i < array.length; i++) {
            int row = array[i][0];
            int col = array[i][1];
            int value = array[i][2];
            normal[row][col] = value;
        }
        return normal;
    }

    /**
     * 从文件中读取稀疏数组 ==> 普通二维数组
     *
     * @param file
     * @return
     */
    public static int[][] readThenToNormal(File file) {

        FileReader reader = null;
        BufferedReader bufferedReader = null;
        int[][] normal;
        try {
            //创建字符读取流
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            String topline = bufferedReader.readLine();
            String[] first = topline.split("\t");
            int rows = Integer.parseInt(first[0]);
            int cols = Integer.parseInt(first[1]);
            int count = Integer.parseInt(first[2]);
            normal = new int[rows][cols];
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split("\t");
                int row = Integer.parseInt(split[0]);
                int col = Integer.parseInt(split[1]);
                int value = Integer.parseInt(split[2]);
                normal[row][col] = value;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }

        return normal;
    }


    // todo 另一种方式的二维数组遍历
    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int value : row) {
                //\t表示制表符
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }
    }

}

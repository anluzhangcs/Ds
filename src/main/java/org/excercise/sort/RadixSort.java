package org.excercise.sort;

import java.util.Arrays;

/**
 * @author Manny
 * @create 2024-03-13-18:58
 * 基数排序-从最低位开始，依次进行一次排序
 * 这样从最低位排序一直到最高位排序完成以后，序列就变成了一个有序序列
 * <p>
 * <p>
 * 获得一个数4312的       个位数 value%10
 * 十位数 value/10%10
 * 百位数 value/100
 * 千位数 value/1000
 * value/(n*10)%10
 */
public class RadixSort {

    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        System.out.println("原始数组：" + Arrays.toString(arr));
        RadixSort radixSort = new RadixSort();
        radixSort.sort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public void sort(int[] arr) {

        //找到最大值
        int max = 0;
        for (int item : arr) {
            if (item > max) {
                max = item;
            }
        }

        //获取最大值的位数，即排序次数
        int turns = (max + "").length();

        //创建二维数组表示10个桶
        int[][] buckets = new int[10][arr.length];
        //用于存放每个桶的元素个数
        int[] bucketCount = new int[10];

        int n;
        int step = 1;
        //共进行turns排序
        for (int i = 0; i < turns; i++, step *= 10) {
            //分配到各个桶中
            for (int item : arr) {
                int j = item / (step) % 10;
                buckets[j][bucketCount[j]] = item;
                bucketCount[j]++;
            }
            //收集
            n = 0;
            for (int l = 0; l < 10; l++) {
                for (int ii = 0; ii < bucketCount[l]; ii++) {
                    arr[n++] = buckets[l][ii];
                }
                //重置bucketCount
                bucketCount[l] = 0;
            }
        }
    }
}

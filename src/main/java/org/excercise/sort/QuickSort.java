package org.excercise.sort;

import java.util.Arrays;

/**
 * @author Manny
 * @create 2024-03-13-14:59
 * 快速排序 - 基于交换,是对冒泡排序的改进
 * 原理:选中一个基准元素,保证左边全小于,右边全大于。然后继续对左右两个子序列划分，直到序列长度为1
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 5, 9, 0, 6, 8, 7, 4};

        QuickSort quickSort = new QuickSort();
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        quickSort.sort(arr, 0, arr.length - 1);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr, int from, int to) {

        //当from==to时，会回溯
        if (from < to) {
            int pos = partition(arr, from, to);
            sort(arr, from, pos - 1);
            sort(arr, pos + 1, to);
        }

    }

    //约定中轴元素为左边第一个元素,对待排序序列进行分割,找到中轴并返回
    public int partition(int[] arr, int from, int to) {
        //
        int tmp;
        int i = from;
        int j = to;

        while (i < j) {

            //从右边开始找到一个比基准元素小的
            while (i < j && arr[j] > arr[i]) {
                j--;
            }
            //如果此时i==j，那么说明右边的都比它大，中轴就是该位置
            if (i < j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                //此时交换过后arr[i]肯定小于基准
                i++;
            }

            //从左边开始找到一个比基准元素大的
            while (i < j && arr[i] < arr[j]) {
                i++;
            }
            if (i < j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                //此时交换过后arr[j]肯定d大于基准
                j--;
            }
        }
        //只要i、j相等，证明中轴位置找到了
        return i;
    }
}

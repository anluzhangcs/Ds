package org.excercise.sort;

import java.util.Arrays;

/**
 * @author Manny
 * @create 2024-03-13-16:52
 * 归并排序
 * 2, 3, 1, 5, 9, 0, 6, 8, 7, 4
 * mid=5          2 3 1 5 9 0                   6 8 7 4
 * mid=2    2 3 1       5 9 0
 * mid=1  2 3     1
 * mid=0 2   3
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 5, 9, 0, 6, 8, 7, 4};
        int[] temp = new int[arr.length];
        MergeSort mergeSort = new MergeSort();
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        mergeSort.sort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr, int from, int to, int[] temp) {
        //当from=to时，回溯到上一层
        if (from < to) {
            int mid = (from + to) / 2;
            //递归左半部分
            sort(arr, from, mid, temp);
            //递归右半部分
            sort(arr, mid + 1, to, temp);
            //合并
            merge(arr, from, to, temp);
        }
    }

    /**
     * 合并两个有序数组
     *
     * @param arr
     * @param from
     * @param to
     * @param temp
     */
    public void merge(int[] arr, int from, int to, int[] temp) {
        int mid = (from + to) / 2;
        int i = from;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= to) {
            //左边大于右边
            if (arr[i] > arr[j]) {
                temp[k++] = arr[j++];
            } else {
                temp[k++] = arr[i++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i];
        }
        while (j <= to) {
            temp[k++] = arr[j];
        }
        //将临时数组拷贝到原数组
        for (i = from; i <= to; i++) {
            arr[i] = temp[i];
        }
    }
}

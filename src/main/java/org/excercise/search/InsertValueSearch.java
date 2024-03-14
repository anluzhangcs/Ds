package org.excercise.search;

/**
 * @author Manny
 * @create 2024-03-14-15:02
 * 插值查找-用于解决二分查找在边缘处效率不高的问题
 * 本质上是更改得到mid的算法,采用自适应mid
 * 适用于关键字比较均匀
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        //int[] arr = new int[100];
        //for (int i = 0; i < 100; i++) {
        //    arr[i] = i + 1;
        //}
        int[] arr = new int[]{1, 8, 10, 89, 1000, 1000, 1234, 1234};
        int search = search(arr, 0, 7, 89);
        System.out.println(search);

    }

    public static int search(int[] arr, int low, int high, int value) {
        System.out.println("被调用了~~");
        if (low > high || value > arr[high] || value < arr[low]) {
            return -1;
        }
        int mid = low + (high - low) * (value - arr[low]) / (arr[high] - arr[low]);
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] > value) {
            //左边找
            return search(arr, low, mid - 1, value);
        } else {
            //右边找
            return search(arr, mid + 1, high, value);
        }
    }
}

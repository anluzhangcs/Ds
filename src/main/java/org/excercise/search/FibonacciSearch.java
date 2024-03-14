package org.excercise.search;

import java.util.Arrays;

/**
 * @author Manny
 * @create 2024-03-14-16:02
 * 和二分查找类似，区别是通过斐波那契数列找到分割点mid
 * 斐波那契数列 1 1 2 3 5... F[k] = F[k-1]+F[k-2]
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 10, 89, 1000, 1000, 1234};
        int search = search(arr, 1234);
        System.out.println(search);
    }

    /**
     * 创建指定大小的斐波那契数列
     *
     * @param maxSize
     * @return
     */
    public static int[] createFibonacci(int maxSize) {
        int[] fibonacci = new int[maxSize];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    public static int search(int[] arr, int value) {
        //创建斐波那契数列
        int[] fibonacci = createFibonacci(20);

        //将数组长度和f[k]符合
        int k = 0;
        while (arr.length > fibonacci[k]) {
            k++;
        }
        //判断f[k]是否和数组长度相等，不等需要扩展原数组
        int[] temp;
        if (fibonacci[k] != arr.length) {
            temp = Arrays.copyOf(arr, fibonacci[k]);
            for (int i = arr.length; i < fibonacci[k]; i++) {
                //扩展数组的值用原数组最后一个元素填充
                temp[i] = arr[arr.length - 1];
            }
        } else { // 相等temp还是指向原数组
            temp = arr;
        }

        //开始查找
        int low = 0;
        //注意：high还是原数组最后一个元素的下标，temp数组只是为了得到mid分割点
        int high = arr.length - 1;
        int mid;
        while (low <= high) { //当low<=high时不断查找
            //此时待排序序列长度为f[k] = f[k-1] + f[k-2]
            mid = low + fibonacci[k - 1] - 1;
            if (temp[mid] > value) { //左边找
                high = mid - 1;
                k--;
            } else if (temp[mid] < value) { //右边找
                low = mid + 1;
                k -= 2;
            } else { //找到了,因为mid值根据斐波那契得到，可能超过high，如果大于high说明high即为要找的元素
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }

            }
        }
        //没有找到
        return -1;

    }
}

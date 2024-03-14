package org.excercise.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Manny
 * @create 2024-03-13-20:09
 * <p>
 * 二分查找
 * 前提：有序
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {3, 3, 53, 14, 542, 748};
        System.out.println("原始数组：" + Arrays.toString(arr));
        BinarySearch bs = new BinarySearch();
        //int[] indexs = new int[arr.length];
        //int index = bs.query(arr, 0, arr.length - 1, 3);
        //if (index != -1) {
        //
        //}
        List<Integer> result = bs.queryAll(arr, 0, arr.length - 1, 3);
        if (result != null) {
            for (Integer i : result) {
                System.out.println("该元素的下标为：" + i);
            }
        }

    }

    public int query(int[] arr, int from, int to, int value) {
        if (from <= to) {

            int mid = (from + to) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] > value) {
                //在左半区找
                return query(arr, from, mid - 1, value);
            } else if (arr[mid] < value) {
                return query(arr, mid + 1, to, value);
            }
        }
        //如果from>to，回溯到上一层
        return -1;
    }

    //查询所有的
    public List queryAll(int[] arr, int from, int to, int value) {


        if (from <= to) {

            int mid = (from + to) / 2;
            if (arr[mid] == value) { //找到了向左右继续查找，因为序列是有序的
                //
                List<Integer> list = new ArrayList<>();
                list.add(mid);
                int i = mid - 1, j = mid + 1;
                while (i >= from) {
                    if (arr[i] == value) {
                        list.add(i);
                        i--;
                    } else {
                        break;
                    }
                }
                while (j <= to) {
                    if (arr[j] == value) {
                        list.add(j);
                        j++;
                    } else {
                        break;
                    }
                }
                return list;
            } else if (arr[mid] > value) {
                //在左半区找
                return queryAll(arr, from, mid - 1, value);
            } else if (arr[mid] < value) {
                return queryAll(arr, mid + 1, to, value);
            }
        }
        //如果from>to，回溯到上一层
        return null;
    }

}

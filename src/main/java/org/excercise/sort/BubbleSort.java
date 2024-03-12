package org.excercise.sort;

/**
 * @author Manny
 * @create 2024-03-12-16:55
 * 冒泡排序：基于交换的排序
 * 冒泡：相邻的两个元素不断比较，如果逆序则交换
 * 每一趟排完序后都有一个元素处于最终位置
 * 冒泡趟数：n -1 次，因为排了n-1趟后，最小或者最大的的肯定在最前面，无需排序
 * 冒泡排序可能提前完成，不需要一定走完n-1趟
 * 冒泡和选择排序很类似，都是每次把最值确定好位置，区别是冒泡还在过程中不断的排好局部，而选择不会
 */
public class BubbleSort {


    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 2, 0, 6, 8, 4, 7, 9};

        BubbleSort bubbleSort = new BubbleSort();
        System.out.println("排序前：");
        bubbleSort.print(arr);
        bubbleSort.sort(arr);
        System.out.println("排序后：");
        bubbleSort.print(arr);
    }


    //从小到大
    public void sort(int[] arr) {

        int tmp;

        //是否有序的标记
        boolean flag;

        //比较趟数
        int count = 0;


        //冒泡次数：arr.length -1 次，因为排了n-1趟后，最小的肯定在最前面，无需排序
        for (int i = 0; i < arr.length - 1; i++) {
            //每趟开始假设已经排好序了
            flag = true;

            //不断交换,比较次数为 n-1-i。因为后面的已经排好了，无需参与比较
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //交换
                if (arr[j] > arr[j + 1]) {
                    System.out.println(arr[j] + "和" + arr[j + 1] + "进行交换");
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    //只要有一个交换，说明可能还没有完全有序，等下一趟确定
                    flag = false;
                }
            }
            count++;
            //如果一整趟一次交换都没有，说明已经有序了，无需再排
            if (flag) {
                //有序退出循环，提升效率
                break;
            }
        }

        System.out.println("这次排序比较趟数：" + count);

    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }
}

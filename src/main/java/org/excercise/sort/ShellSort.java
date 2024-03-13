package org.excercise.sort;

import java.util.Arrays;

/**
 * @author Manny
 * @create 2024-03-13-12:25
 * 希尔排序：对简单插入排序的优化，也叫缩小增量排序。目的是尽量让较小的数据在简单插入之前往前面放
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 5, 9, 0, 6, 8, 7, 4};

        ShellSort shellSort = new ShellSort();
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        shellSort.sort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 每次根据gap进行分组，然后将每组进行简单插入排序
     * i，i+gap，...为一组，所以会分为gap组
     *
     * @param arr
     */
    public void sort(int[] arr) {

        int tmp;
        int index;

        int gap = arr.length / 2;
        //逐渐缩小gap
        while (gap >= 1) {

            //i从gap开始,因为从叫第二张牌才需要排序,一张牌不需要排序
            for (int i = gap; i < arr.length; i++) {
                //arr[i]表示新叫到的牌
                tmp = arr[i];
                //index表示新牌要插入的位置
                index = i;
                //步长变为gap,当手牌大于新牌时,进行移动
                for (int j = i - gap; j >= 0 && arr[j] > tmp; j -= gap) {
                    arr[j + gap] = arr[j];
                    //更新新牌的位置
                    index = j;
                }
                //将新牌插入到正确位置
                arr[index] = tmp;
            }
            System.out.println("增量为" + gap + "排序完之后的序列:" + Arrays.toString(arr));
            gap = gap / 2;
        }

    }
}

package org.excercise.sort;

/**
 * @author Manny
 * @create 2024-03-12-20:26
 * 插入排序-基于插入
 * 和叫牌一样，每次新叫到一张牌，就把他放入相应的位置
 * 插入排序，第一趟无需进行，因为相当于只有一张牌，默认有序。从第二趟到第n趟
 * 当要插入的数据比较小时，后移次数明显增多，降低效率
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 5, 9, 0, 6, 8, 7, 4};

        InsertionSort insertionSort = new InsertionSort();
        System.out.println("排序前：");
        insertionSort.print(arr);
        insertionSort.sort(arr);
        System.out.println("排序后：");
        insertionSort.print(arr);
    }

    public void sort(int[] arr) {

        int tmp;

        //用来记录新牌的位置
        int index;

        //插入排序，必须排n趟，因为它每一趟排好后并没有确定一个元素的最终位置
        for (int i = 1; i < arr.length; i++) {
            //arr[i]表示新叫到的牌
            tmp = arr[i];
            index = i;
            //把新叫到的牌手牌进行比较
            for (int j = i - 1; j >= 0 && arr[j] > tmp; j--) {
                //手牌大于新牌，不断移动手牌位置
                arr[j + 1] = arr[j];
                //记录新牌的位置
                index = j;
            }
            //把新牌插入正确位置
            arr[index] = tmp;
        }
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }
}

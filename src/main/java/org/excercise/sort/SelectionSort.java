package org.excercise.sort;

/**
 * @author Manny
 * @create 2024-03-12-19:13
 * 选择排序-基于选择
 * 每一趟排完序后都有一个元素处于最终位置
 * 必须完成n-1趟才能确保完全有序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 5, 9, 0, 6, 8, 7};
        SelectionSort selectionSort = new SelectionSort();
        System.out.println("排序前：");
        selectionSort.print(arr);
        selectionSort.sort(arr);
        System.out.println("排序后：");
        selectionSort.print(arr);
    }

    public void sort(int[] arr) {

        int winner;

        int tmp;

        //打擂台，每次确定一个元素的位置，所以也只需要n-1趟
        for (int i = arr.length - 1; i > 0; i--) {
            //首先设置擂台为当前元素
            winner = i;
            //打擂台找到最大的或者最小的
            //注意j<i，因为后面的已经有序了，再比反而会造成混乱
            //第一趟比较n-1次，第二趟比较n-2次，...
            for (int j = 0; j < i; j++) {
                //打擂台输了
                if (arr[j] > arr[winner]) {
                    //更换擂主
                    winner = j;
                }
            }
            //优化：如果擂主就是当前元素，则无需交换，提升效率
            if (winner == i) {
                continue;
            }
            //把这一轮的擂主放到相应的位置
            tmp = arr[winner];
            arr[winner] = arr[i];
            arr[i] = tmp;
        }
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
    }

}

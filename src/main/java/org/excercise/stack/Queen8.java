package org.excercise.stack;

/**
 * @author Manny
 * @create 2024-03-11-15:31
 * 8皇后问题 : 在8行8列的棋盘上放置8个皇后，每两个皇后不能再同一列，同一行，同一斜线上，否则会冲突
 */
public class Queen8 {

    int max = 8;
    //使用一维数组来模拟每个皇后的位置，arr[i] = j 表示皇后的位置为第 i+1 行 j+1列
    int[] arr = new int[max];

    static int count = 0; //可能的解法数量


    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.put(0);
        System.out.println("所有可能的解法：" + count);
    }


    /**
     * 放置第n+1个皇后
     *
     * @param n
     */
    public void put(int n) {

        //递归退出条件
        if (n == max) { //说明已经找到了最后一个皇后的位置
            //展示解法
            show();
            //回溯到上一层
            return;
        }

        //遍历每一列，选择放置皇后的位置
        for (int i = 0; i < max; i++) {
            //假设皇后在这个位置
            arr[n] = i;
            if (!conflict(n)) { //没有冲突
                //递归
                put(n + 1);
            }
            //如果冲突了，就放在下一列继续
        }
        //每一列都找了还是没有合适的位置，回溯到上一层
    }

    /**
     * 判断此皇后和其它n个皇后是否有冲突
     *
     * @param n
     * @return
     */
    public boolean conflict(int n) {
        //无需判断是否在同一行，因为肯定不在
        for (int i = 0; i < n; i++) {
            //在同一列或者同一斜线上
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return true;
            }
        }
        //没有冲突
        return false;
    }

    /**
     * 输出8皇后可能的解法
     */
    public void show() {
        for (int i = 0; i < max; i++) {
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
        count++;
    }

}

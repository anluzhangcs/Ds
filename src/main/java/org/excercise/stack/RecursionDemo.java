package org.excercise.stack;

/**
 * @author Manny
 * @create 2024-03-09-14:34
 * 递归：自己调用自己
 * 原则：
 * - 执行一个方法时创建一个新的栈空间
 * - 局部变量一般是独立的，除非是引用类型
 * - 遇到return 或者 方法执行完成后 返回到调用该方法的位置
 * - 递归需不断向推出递归的条件逼近，否则会出现StackOverflowError
 */
public class RecursionDemo {

    public static void main(String[] args) {
        int[][] map = initMap(8, 7);
        System.out.println("原始迷宫为：");
        printMap(map);
        setWay(map, 1, 1);
        System.out.println("迷宫可能的路线：");

        printMap(map);
    }


    /**
     * 迷宫问题
     * 约定：i，j表示从哪个点出发
     * map[i][j] ：1代表墙、0代表没有走过、2代表可以走、3代表走过但不通
     * map[6][5] =2，说明通路找到
     * 走不通时 先 下 -》 右 - 上 -> 左
     *
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int map[][], int i, int j) {
        if (map[6][5] == 2) { //递归推出条件，已经走通
            return true;
        } else { //还没有走通
            if (map[i][j] == 0) {
                //假设可以走通
                map[i][j] = 2;
                //继续按照策略走
                if (setWay(map, i + 1, j)) { //往下走通了
                    return true;
                } else if (setWay(map, i, j + 1)) { //往右走通了
                    return true;
                } else if (setWay(map, i - 1, j)) { //往上走通了
                    return true;
                } else if (setWay(map, i, j - 1)) { //往走走通了
                    return true;
                }
                //都不行，此路已死。标记后返回false
                map[i][j] = 3;
                return false;
            } else if (map[i][j] == 1) { //是墙
                return false;
            } else if (map[i][j] == 3) { //走不通
                return false;
            } else if (map[i][j] == 2) { //已经走过，不需要再走
                return false;
            } else {
                return false;
            }
        }
    }

    private static int[][] initMap(int row, int cloum) {
        // 构建一个 8 行 7 列的地图
        int[][] map = new int[row][cloum];
        // 数字 1 表示挡板，构建一个有挡板的地图

        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;  // 顶部增加挡板
            map[map.length - 1][i] = 1;  // 底部增加挡板
        }

        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;  // 左侧增加挡板
            map[i][map[0].length - 1] = 1; // 右侧增加挡板
        }

        // 中间的其他固定挡板
        map[3][1] = 1;
        map[3][2] = 1;
        return map;
    }

    public static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}



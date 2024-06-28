package org.excercise.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Solution216.java
 *
 * @author zh
 * @since 2024/6/27 on 19:54
 */
class Solution216 {
    List<List<Integer>> ans = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        Solution216 solution = new Solution216();
        List<List<Integer>> lists = solution.combinationSum3(3, 9);
        System.out.println(Arrays.toString(lists.toArray()));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {

        if (n < 0 || k > 9 || k < 0) {
            return ans;
        }
        backtracking(k, n, 1);
        return ans;
    }

    /**
     *
     * @param k 组合的长度
     * @param n 剩余和
     * @param start 搜寻起始位置
     */
    private void backtracking(int k, int n, int start) {
        if(path.size() == k){ // 退出条件
            if (n == 0) { // 验证组合是否符合要求
                ans.add(new LinkedList(path));
            }
            return;
        }
        // 剪枝优化：控制搜索上界[1,9] 并且 小于等于n（剩余和）
        for(int i = start; i <= 9 && i <= n; i++){
            path.add(i);
            backtracking(k,n-i,i+1);
            // 回溯
            path.removeLast();
        }
    }
}

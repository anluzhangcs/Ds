package org.excercise.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zh
 * @create 2024-06-27-11:48
 */
class Solution77 {

    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n < k) {
            return ans;
        }
        backtracking(n, k, 1);
        return ans;
    }

    // 1. 确定回溯函数参数
    private void backtracking(int n, int k, int start) {
        // 2. 确定终止条件
        if (path.size() == k) {
            // 组合已经找到了，进行收割
            ans.add(new LinkedList(path));
        }

        for (int i = start; i <= n; i++) {
            // 将当前层节点加入，然后进入下一层
            path.add(i);
            backtracking(n, k, i + 1);

            // !!!回溯！！！, 移除当前层元素并返回上一层
            path.remove(path.size() - 1);
        }
    }
}

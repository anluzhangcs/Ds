package org.excercise.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zh
 * @create 2024-06-27-11:48
 */
class Solution77 {

    public static void main(String[] args) {
        Solution77 solution = new Solution77();
        List<List<Integer>> combine = solution.combine(4, 2);
        System.out.println(Arrays.toString(combine.toArray()));

    }

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
            return;
        }
        // 剪枝优化
        // 搜索起点是有上界的，以n=4，k=2为例，当start=4是没有意义，因为后面没有可以选的了，组合长度达不到2
        // 上界的计算：n - (还要选的个数) + 1 = n-(k-path.size())+1 = n+path.size()-k+1
        for (int i = start; i <= n-(k-path.size())+1; i++) {
            // 将当前层节点加入，然后进入下一层
            path.add(i);
            backtracking(n, k, i + 1);

            // !!!回溯！！！, 移除当前层元素并返回上一层
            path.remove(path.size() - 1);
        }
    }
}

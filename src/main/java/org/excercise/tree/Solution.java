package org.excercise.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Solution.java
 *
 * @author zh
 * @since 2024/6/17 on 17:52
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Integer[] levelorder = new Integer[]{1,2,3,4,null,5,6,null,null,7};
        TreeNode root = TreeUtil.levelBuild(levelorder);
        TreeUtil.showTree(root);

    }
}

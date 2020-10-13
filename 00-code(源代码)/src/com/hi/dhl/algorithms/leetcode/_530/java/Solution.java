package com.hi.dhl.algorithms.leetcode._530.java;

import com.hi.dhl.algorithms.model.TreeNode;

import java.util.Stack;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  :
 * </pre>
 */
class Solution {
    private int pre = Integer.MAX_VALUE;
    private int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return min;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre != Integer.MAX_VALUE) {
            min = Math.min(min, root.val - pre);
        }
        pre = root.val;
        dfs(root.right);
    }

    private void bfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode node = stack.pop();
            if (pre != Integer.MAX_VALUE) {
                min = Math.min(min, node.val - pre);
            }
            pre = node.val;
            root = node.right;
        }
    }
}
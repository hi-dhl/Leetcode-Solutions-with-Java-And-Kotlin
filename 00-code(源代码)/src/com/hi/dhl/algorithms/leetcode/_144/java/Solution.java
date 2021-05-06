package com.hi.dhl.algorithms.leetcode._144.java;

import java.util.*;

/**
 * <pre>
 *     author: dhl
 *     desc  : 144. Binary Tree Preorder Traversal
 *
 *     题目来源于 LeetCode 上第 144 号问题：二叉树的前序遍历。题目难度为 Medium。
 *     题目地址: https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 *     Given a binary tree, return the preorder traversal of its nodes' values.
 *     给定一个二叉树，返回它的前序遍历
 *
 *     Example:
 *     Input: [1,null,2,3]
 *         1
 *          \
 *          2
 *          /
 *         3
 *     Output: [1,2,3]
 *
 *     题目解析：
 *     二叉树的先序遍历过程: 根节点 -> 左节点 -> 右边节点
 *
 *     1. 循环检测栈是否为空，或者根节点是否为空
 *     2. 循环检测左节点，保存到栈中
 *     3. 当左节点遍历结束之后，取出栈顶的右节点，再次执行步骤2
 *
 * </pre>
 */

class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 先序遍历
     *
     * @param root 根节点
     * @return 先序遍历的集合
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList();

        /**
         * 步骤1
         * 循环检测栈是否为空，或者根节点是否为空
         */
        while (!stack.isEmpty() || root != null) {

            /**
             * 步骤2
             * 循环检测左节点，保存到栈中
             */
            while (root != null) {
                stack.push(root);
                list.add(root.val);
                root = root.left;
            }

            /**
             * 步骤3
             * 取出栈顶的右节点，再次执行步骤2
             */
            if (!stack.isEmpty()) {
                root = stack.pop().right;
            }
        }
        return list;
    }

    // 方法二
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> data = new LinkedList<>();
        if (root == null) return data;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
            }

            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                data.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);
                }
                root = node.left;
            }
        }
        return data;
    }
}
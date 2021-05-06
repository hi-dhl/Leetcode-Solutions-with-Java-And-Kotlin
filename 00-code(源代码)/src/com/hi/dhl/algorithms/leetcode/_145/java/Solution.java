package com.hi.dhl.algorithms.leetcode._145.java;

import java.util.*;

/**
 * <pre>
 *     author: dhl
 *     desc  : 145. Binary Tree Postorder Traversal
 *
 *     题目来源于 LeetCode 上第 145 号问题：二叉树的后序遍历。题目难度为 Medium。
 *     题目地址: https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 *     Given a binary tree, return the postorder traversal of its nodes' values.
 *     给一个二叉树，返回它的后续遍历
 *
 *     Example:
 *
 *     Input: [1,null,2,3]
 *       1
 *       \
 *       2
 *       /
 *       3
 *     Output: [3,2,1]
 *
 *     题目解析：
 *     二叉树的先序遍历过程: 左节点 -> 右节点 -> 根节点
 *
 *     1. 先将根节点入栈
 *     2. 循环判断判断栈不为空
 *     3. 取出栈顶元素，并添加到list集合（逆序添加）
 *     4. 如果左节点不为空，并将左节点入栈
 *     5. 如果右节点不为空，并将右节点入栈
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

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        //先将根节点入栈
        stack.push(root);
        //循环判断判断栈不为空
        while (!stack.isEmpty()) {
            root = stack.pop();
            //取出栈顶元素，并添加到list集合（逆序添加）
            list.add(0, root.val);
            //如果左节点不为空，并将左节点入栈
            if (root.left != null) stack.push(root.left);
            //如果右节点不为空，并将右节点入栈
            if (root.right != null) stack.push(root.right);
        }
        return list;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> data = new LinkedList<>();
        if (root == null) return data;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (true) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.peek();
                    if (root.right != null && root.right != pre) {
                        root = root.right;
                    } else {
                        break;
                    }
                }
            }

            pre = stack.pop();
            data.add(pre.val);
            root = null;
        }
        return data;
    }
}
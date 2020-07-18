package com.hi.dhl.algorithms._94.java;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 *     author: dhl
 *     desc  : 94. Binary Tree Inorder Traversal
 *
 *     题目来源于 LeetCode 上第 94 号问题：二叉树的中序遍历。题目难度为 Medium。
 *     题目地址: https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 *     Given a binary tree, return the inorder traversal of its nodes' values
 *     给一个二叉树，返回二叉树的中序遍历
 *
 *     Example:
 *     Input: [1,null,2,3]
 *       1
 *       \
 *       2
 *       /
 *       3
 *     Output: [1,3,2]
 *
 *     题目解析：
 *     二叉树的先序遍历过程: 左节点 -> 根节点 -> 右节点
 *
 *     1. 循环检测栈是否为空，或者根节点是否为空
 *     2. 循环检测左节点，保存在栈中
 *     3. 弹出栈顶元素，再将当前指针移到其右子节点上，若存在右子节点，在次执行步骤2
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

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        /**
         * 步骤1
         * 循环检测栈是否为空，或者根节点是否为空
         */
        while (!stack.isEmpty() || root != null) {

            /**
             * 步骤2
             * 循环检测左节点，保存在栈中
             */
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            //弹出栈顶元素
            TreeNode node = stack.pop();
            list.add(node.val);

            /**
             * 再将当前指针移到其右子节点上，若存在右子节点，在次执行步骤2
             */
            root = node.right;
        }
        return list;
    }
}

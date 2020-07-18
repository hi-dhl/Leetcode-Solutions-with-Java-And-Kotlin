package com.hi.dhl.algorithms._222.java;

import com.hi.dhl.algorithms.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 *      Definition for a binary tree node.
 *     public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 *     }
 * </pre>
 */
public class Solution {


    // method 1: dfs
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


    //
    public int countNodesBFS1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                count++;
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return count;
    }

    public int countNodesBFS2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack();
        int count = 0;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                count++;
                stack.push(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
        return count;
    }

}

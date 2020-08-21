package com.hi.dhl.algorithms._114.java;

import com.hi.dhl.algorithms.model.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/23
 *     desc  :
 * </pre>
 */
public class Solution {


    /**
     * 方法 一
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList();

        // 循环检测栈是否为空，或者根节点是否为空
        while (!stack.isEmpty() || root != null) {

            // 循环检测左节点，保存到栈中
            while (root != null) {
                stack.push(root);
                list.add(root.val);
                root = root.left;
            }

            // 取出栈顶的右节点，再次执行步骤2
            if (!stack.isEmpty()) {
                root = stack.pop().right;
            }
        }
        return list;
    }

    /**
     * 方法二
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        if (root == null) return output;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return output;
    }
}

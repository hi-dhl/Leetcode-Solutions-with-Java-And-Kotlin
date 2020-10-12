package com.hi.dhl.algorithms.leetcode._114.kotlin

import com.hi.dhl.algorithms.model.TreeNode
import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/23
 *     desc  :
 * </pre>
 */

class Solution {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        stack.push(root);
        while (!stack.isEmpty()) {
            stack.pop()?.let { root ->
                list.add(root.`val`)
                if (root.right != null) stack.push(root.right)
                if (root.left != null) stack.push(root.left)
            }
        }
        return list
    }
}
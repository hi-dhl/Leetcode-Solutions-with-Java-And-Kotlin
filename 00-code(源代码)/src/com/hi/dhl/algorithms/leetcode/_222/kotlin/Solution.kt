package com.hi.dhl.algorithms.leetcode._222.kotlin

import java.util.*

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private tailrec fun countNodes(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        return countNodes(root.left) + countNodes(root.right) + 1
    }

    fun countNodesBFS(root: TreeNode?): Int {
        var count = 0
        root?.let { it ->
            val queue = LinkedList<TreeNode>()
            queue.offer(root)
            while (!queue.isEmpty()) {
                var size = queue.size
                while (size > 0) {
                    count++
                    val node = queue.poll()
                    if (node.left != null) {
                        queue.offer(node.left)
                    }
                    if (node.right != null) {
                        queue.offer(node.right)
                    }
                    size--
                }
            }

        }
        return count
    }
}
package com.hi.dhl.algorithms.leetcode._530.kotlin

import com.hi.dhl.algorithms.model.TreeNode
import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  :
 * </pre>
 */

class Solution {
    var pre: Int = Int.MAX_VALUE
    var min: Int = Int.MAX_VALUE

    fun getMinimumDifference(root: TreeNode?): Int {
        if (root == null) return 0
        bfs(root)
        return min
    }

    fun dfs(root: TreeNode?) {
        if (root == null) {
            return
        }

        dfs(root.left)
        if (pre != Int.MAX_VALUE) {
            val sub = root.`val` - pre
            min = Math.min(min, sub)
        }
        pre = root.`val`
        dfs(root.right)
    }

    fun bfs(root: TreeNode?) {
        val stack = Stack<TreeNode>()
        var cur = root
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur)
                cur = cur.left
            }

            val node = stack.pop()
            if (pre != Int.MAX_VALUE) {
                min = Math.min(min, node.`val` - pre)
            }
            pre = node.`val`
            cur = node.right
        }
    }

}
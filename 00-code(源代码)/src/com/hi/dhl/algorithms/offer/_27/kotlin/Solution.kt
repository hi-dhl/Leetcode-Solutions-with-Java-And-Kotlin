package com.hi.dhl.algorithms.offer._27.kotlin

import com.hi.dhl.algorithms.model.TreeNode
import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/9/1
 *     desc  :
 * </pre>
 */

class Solution {

    /**
     * 根据后续遍历的特点 左 -> 右 -> 根，交换左右节点的顺序即可
     */
    fun mirrorTree(root: TreeNode?): TreeNode? {
        if (root == null) return root

        mirrorTree(root.left)
        mirrorTree(root.right)
        val tmp = root.left
        root.left = root.right
        root.right = tmp
        return root
    }

    /**
     * 利用 后续遍历 非递归的方式，交换左右节点顺序
     */
    fun mirrorTree2(root: TreeNode?): TreeNode? {
        if (root == null) return root

        val stack = Stack<TreeNode>()
        stack.push(root)
        while (!stack.isEmpty()) {
            val node = stack.pop()
            if (node.left != null) stack.push(node.left)
            if (node.right != null) stack.push(node.right)

            val tmp = node.left
            node.left = node.right
            node.right = tmp
        }
        return root
    }
}
package com.hi.dhl.algorithms.offer._28.kotlin

import com.hi.dhl.algorithms.model.TreeNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/9/1
 *     desc  :
 * </pre>
 */

class Solution {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        val mirror = mirrorTree(root)
        return isSame(mirror, root)
    }

    /**
     * 根据先序遍历，判断镜像二叉树和原二叉树是否相等
     */
    fun isSame(a: TreeNode?, b: TreeNode?): Boolean {
        if (a == null && b == null) return true
        if (a == null || b == null || a.`val` != b.`val`) return false

        return return isSame(a.left, b.left) && isSame(a.right, b.right)
    }

    /**
     * 生成二叉树的镜像
     */
    fun mirrorTree(root: TreeNode?): TreeNode? {
        if (root == null) return root
        val node = TreeNode(root.`val`)
        node.left = mirrorTree(root.right)
        node.right = mirrorTree(root.left)
        return node
    }
}
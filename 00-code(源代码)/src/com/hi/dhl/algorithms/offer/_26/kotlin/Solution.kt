package com.hi.dhl.algorithms.offer._26.kotlin

import com.hi.dhl.algorithms.model.TreeNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/9/1
 *     desc  :
 * </pre>
 */

class Solution {
    fun isSubStructure(A: TreeNode?, B: TreeNode?): Boolean {
        // 只要其中一个为空，返回 false 不是 A 的子树
        if (A == null || B == null) return false

        val sub = isSub(A, B) // 从根节点开始遍历
                || isSubStructure(A.left, B) // 从左子树开始遍历
                || isSubStructure(A.right, B) // 从右子树开始遍历
        return sub
    }

    fun isSub(A: TreeNode?, B: TreeNode?): Boolean {
        // 如果 B 为空，说明每个节点都相等，说明是 A 的子树
        if (B == null) return true

        // 如果 B 不为空 A 为空 说明不是 A 的子树
        // 如果两个节点值不相等，说明也不是 A 的子树
        if (A == null || A.`val` != B.`val`) return false

        return isSub(A.left, B.left) && isSub(A.right, B.right);
    }
}
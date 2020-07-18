package com.hi.dhl.algorithms._100.kotlin

import com.hi.dhl.algorithms.model.TreeNode
import java.util.*

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    tailrec fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) {
            return true
        }

        if (p == null || q == null) {
            return false
        }

        if (p.`val` == q.`val`) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }

        return false
    }

    fun isSameTree2(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) {
            return true
        }

        if (p == null || q == null) {
            return false
        }

        val pWrapQueue = LinkedList<TreeNode>()
        val qWrapQueue = LinkedList<TreeNode>()
        pWrapQueue.offer(p)
        qWrapQueue.offer(q)
        while (!pWrapQueue.isEmpty() && !qWrapQueue.isEmpty()) {
            val pNode = pWrapQueue.poll()
            val qNode = qWrapQueue.poll()
            if (pNode == null && qNode == null) {
                continue;
            }

            if (pNode == null || qNode == null) {
                return false;
            }

            if (pNode.`val` != qNode.`val`) {
                return false;
            }

            pWrapQueue.offer(pNode.left)
            pWrapQueue.offer(pNode.right)
            qWrapQueue.offer(qNode.left)
            qWrapQueue.offer(qNode.right)
        }

        return pWrapQueue.size == qWrapQueue.size
    }
}
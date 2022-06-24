package com.hi.dhl.algorithms.leetcode._025.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/6/24
 *     desc  :
 * </pre>
 */
class Solution {

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null) {
            return head
        }
        val len = getListLength(head)
        val dumpHead = ListNode(0)
        dumpHead.next = head
        var p = dumpHead
        var q = dumpHead.next
        var index = 0
        while (q != null) {
            if (len - index < k) {
                return dumpHead.next
            }
            index = index + k
            var m = k;
            while (--m != 0) {
                val node = q.next
                q.next = q.next.next
                node.next = p.next
                p.next = node
            }
            p = q
            q = q.next
        }
        return dumpHead.next
    }

    private fun getListLength(head: ListNode?): Int {
        var cur = head
        var len = 0
        while (cur != null) {
            cur = cur?.next
            len++
        }
        return len
    }
}
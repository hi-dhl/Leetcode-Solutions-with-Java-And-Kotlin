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
        val dummy = ListNode(0)
        dummy.next = head
        var p = dummy
        var q = dummy.next
        var index = 0
        while (q != null) {
            if (len - index < k) {
                return dummy.next
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
        return dummy.next
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

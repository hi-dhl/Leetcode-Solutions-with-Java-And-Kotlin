package com.hi.dhl.algorithms._141.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/17
 *     desc  :
 * </pre>
 */

class Solution {
    fun hasCycle(head: ListNode?): Boolean {
        return head?.let {
            if (head.next == null) return false
            var p: ListNode? = head
            var q: ListNode? = head
            do {
                if (q == null || q?.next == null) return false
                p = p?.next;
                q = q?.next.next;
            } while (p != q)
            true
        } ?: false
    }
}
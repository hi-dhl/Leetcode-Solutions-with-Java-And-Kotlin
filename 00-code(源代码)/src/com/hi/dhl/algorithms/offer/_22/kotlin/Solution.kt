package com.hi.dhl.algorithms.offer._22.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/29
 *     desc  :
 * </pre>
 */
class Solution {
    fun getKthFromEnd(head: ListNode?, k: Int): ListNode? {
        if (head == null || k < 0) return head
        var fast = head;

        (1 until k).forEach {
            fast = fast?.next
        }

        var slow = head;
        while (fast?.next != null) {
            fast = fast?.next;
            slow = slow?.next;
        }

        return slow;
    }
}
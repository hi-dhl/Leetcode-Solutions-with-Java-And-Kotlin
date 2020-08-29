package com.hi.dhl.algorithms.offer._25.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/29
 *     desc  :
 * </pre>
 */

class Solution {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var head = ListNode(0)
        var cur = head
        var pl1 = l1;
        var pl2 = l2;
        while (pl1 != null && pl2 != null) {
            if (pl1.`val` < pl2.`val`) {
                cur.next = pl1
                pl1 = pl1.next
            } else {
                cur.next = pl2
                pl2 = pl2.next

            }
            cur = cur.next
        }

        cur.next = if (pl1 == null) pl2 else pl1
        return head.next
    }
}
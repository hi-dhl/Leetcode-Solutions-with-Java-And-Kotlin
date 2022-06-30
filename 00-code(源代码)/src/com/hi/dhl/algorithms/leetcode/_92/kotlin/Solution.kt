package com.hi.dhl.algorithms.leetcode._92.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/6/30
 *     desc  :
 * </pre>
 */
class Solution {
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        val dummyNode = ListNode(0)
        dummyNode.next = head
        var p = dummyNode
        var m = left
        while (--m != 0) {
            p = p.next
        }
        var q = p.next
        var k = right - left
        while (k-- != 0) {
            val node = q.next
            q.next = q.next.next
            node.next = p.next
            p.next = node
        }
        return dummyNode.next
    }
}
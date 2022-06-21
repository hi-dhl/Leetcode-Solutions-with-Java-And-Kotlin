package com.hi.dhl.algorithms.leetcode._002.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/5/12
 *     desc  :
 * </pre>
 */
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var p1 = l1
        var p2 = l2
        var carry = 0
        val head = ListNode(0)
        var pre = head
        while (p1 != null || p2 != null || carry != 0) {
            val a = p1?.`val` ?: 0
            val b = p2?.`val` ?: 0
            val sum = a + b + carry
            carry = sum / 10
            val node = ListNode(sum % 10)
            pre.next = node
            pre = node
            p1 = p1?.next
            p2 = p2?.next
        }
        return head.next
    }
}
package com.hi.dhl.algorithms.leetcode._449.kotlin

import com.hi.dhl.algorithms.model.ListNode
import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/4/29
 *     desc  :
 * </pre>
 */
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution2 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val stack1 = ArrayDeque<Int>()
        val stack2 = ArrayDeque<Int>()

        var p1 = l1
        var p2 = l2
        while (p1 != null) {
            stack1.push(p1.`val`)
            p1 = p1.next
        }

        while (p2 != null) {
            stack2.push(p2.`val`)
            p2 = p2.next
        }

        var carry = 0
        var head: ListNode? = null
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            val a = if (!stack1.isEmpty()) stack1.pop() else 0
            val b = if (!stack2.isEmpty()) stack2.pop() else 0
            val sum = a + b + carry
            carry = sum / 10
            val node = ListNode(sum % 10)
            node.next = head
            head = node
        }
        return head
    }
}
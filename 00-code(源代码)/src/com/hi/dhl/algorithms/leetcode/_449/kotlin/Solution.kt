package com.hi.dhl.algorithms.leetcode._449.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/4/27
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
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var p1 = reverse(l1)
        var p2 = reverse(l2)
        var head: ListNode? = null;
        var carry = 0;
        while(p1 != null || p2 != null || carry > 0){
            val a = p1?.`val` ?:0
            val b = p2?.`val` ?:0
            val sum = a +b + carry
            carry = sum / 10;
            var node = ListNode(sum % 10)
            node.next = head
            head = node
            p1 = p1?.next
            p2 = p2?.next
        }
        return head
    }

    fun reverse(head:ListNode?): ListNode?{
        var cur = head
        var pre:ListNode? = null
        while(cur != null){
            var next = cur.next;
            cur.next = pre
            pre = cur
            cur = next
        }
        return pre
    }
}
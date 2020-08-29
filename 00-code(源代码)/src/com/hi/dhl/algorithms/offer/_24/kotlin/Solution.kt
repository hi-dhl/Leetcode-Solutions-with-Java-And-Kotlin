package com.hi.dhl.algorithms.offer._24.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/29
 *     desc  :
 * </pre>
 */

class Solution {

    /**
     * 非递归
     */
    fun reverseList(head: ListNode?): ListNode? {
        var pre: ListNode? = null
        var current = head
        while (current != null) {
            val next = current.next
            current.next = pre;
            pre = current;
            current = next
        }
        return pre;
    }

    /**
     * 递归
     */
    fun reverseList2(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head;
        val cur = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
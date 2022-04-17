package com.hi.dhl.algorithms.leetcode._019.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/4/17
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
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) {
            return head;
        }
        val dummyHead = ListNode(n, head);
        var p1 = dummyHead;
        var p2 = head;
        var m = n;
        while (m-- != 0) {
            p2 = p2?.next;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = p1.next?.next;
        return dummyHead.next;
    }
}
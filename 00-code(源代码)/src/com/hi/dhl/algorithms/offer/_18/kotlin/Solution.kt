package com.hi.dhl.algorithms.offer._18.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/5
 *     desc  :
 * </pre>
 */

class Solution {
    fun deleteNode(head: ListNode?, `val`: Int): ListNode? {
        if (head == null) return head
        if (head.`val` == `val`) return head.next

        var current = head
        while (current?.next != null) {
            val tmpNode = current.next;
            if (tmpNode.`val` == `val`) {
                current.next = tmpNode.next
                break;
            }
            current = tmpNode
        }
        return head
    }
}
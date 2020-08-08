package com.hi.dhl.algorithms.offer._18.java;

import com.hi.dhl.algorithms.model.ListNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/5
 *     desc  :
 * </pre>
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        if (head.val == val) return head.next;

        ListNode current = head;
        while (current.next != null) {
            ListNode tmp = current.next;
            if (tmp.val == val) {
                current.next = tmp.next;
                break;
            }
            current = tmp;
        }
        return head;
    }
}
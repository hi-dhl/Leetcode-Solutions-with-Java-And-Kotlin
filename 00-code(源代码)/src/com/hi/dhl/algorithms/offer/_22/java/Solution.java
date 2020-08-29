package com.hi.dhl.algorithms.offer._22.java;

import com.hi.dhl.algorithms.model.ListNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/29
 *     desc  :
 * </pre>
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k < 0) return head;

        ListNode fast = head;
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }

        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
package com.hi.dhl.algorithms._141.java;

import com.hi.dhl.algorithms.model.ListNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/17
 *     desc  :
 * </pre>
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode p = head;
        ListNode q = head;
        do {
            if (q == null || q.next == null) return false;
            p = p.next;
            q = q.next.next;
        } while (p != q);
        return true;
    }
}
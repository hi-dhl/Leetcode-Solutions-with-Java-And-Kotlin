package com.hi.dhl.algorithms.offer._25.java;

import com.hi.dhl.algorithms.model.ListNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/29
 *     desc  :
 * </pre>
 */

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }
}
package com.hi.dhl.algorithms.leetcode._92.java;

import com.hi.dhl.algorithms.model.ListNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/6/30
 *     desc  :
 * </pre>
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode p = dummyNode;
        int m = left;
        while (--m != 0) {
            p = p.next;
        }
        ListNode q = p.next;
        int k = right - left;
        while (k-- != 0) {
            ListNode node = q.next;
            q.next = q.next.next;
            node.next = p.next;
            p.next = node;
        }
        return dummyNode.next;
    }
}
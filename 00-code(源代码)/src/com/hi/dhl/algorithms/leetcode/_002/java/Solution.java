package com.hi.dhl.algorithms.leetcode._002.java;

import com.hi.dhl.algorithms.model.ListNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/5/12
 *     desc  :
 * </pre>
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(0);
        ListNode pre = head;
        while (l1 != null || l2 != null || carry > 0) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = a + b + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            pre.next = node;
            pre = node;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return head.next;
    }
}
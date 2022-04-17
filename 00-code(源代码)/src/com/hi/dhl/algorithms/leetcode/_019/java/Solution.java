package com.hi.dhl.algorithms.leetcode._019.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/4/15
 *     desc  :
 * </pre>
 */

import com.hi.dhl.algorithms.model.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(0, head); // 构建一个伪节点
        ListNode p1 = dummyHead;
        ListNode p2 = head;
        while (n-- != 0) {
            p2 = p2.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return dummyHead.next;
    }
}
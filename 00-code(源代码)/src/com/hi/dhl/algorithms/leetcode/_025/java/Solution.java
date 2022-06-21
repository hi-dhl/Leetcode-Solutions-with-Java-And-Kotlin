package com.hi.dhl.algorithms.leetcode._025.java;

import com.hi.dhl.algorithms.model.ListNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/6/21
 *     desc  :
 * </pre>
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        int len = getListLength(head);
        ListNode dumpNode = new ListNode(0);
        dumpNode.next = head;
        ListNode p = dumpNode;
        ListNode q = dumpNode.next;
        int index = 0;
        while (q != null) {
            if (len - index < k) {
                break;
            }
            index = index + k;
            int m = k;
            while (--m != 0) {
                ListNode node = q.next;
                q.next = q.next.next;
                node.next = p.next;
                p.next = node;
            }
            p = q;
            q = q.next;
        }
        return dumpNode.next;
    }

    private int getListLength(ListNode head) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }
}
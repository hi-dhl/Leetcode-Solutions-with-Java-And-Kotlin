package com.hi.dhl.algorithms.leetcode._021.java;

/**
 * <pre>
 *     author: dhl
 *     desc  : merge-two-sorted-lists
 *     site: https://leetcode.com/problems/merge-two-sorted-lists/
 *
 *     Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *     Example:
 *          Input: 1->2->4, 1->3->4
 *          Output: 1->1->2->3->4->4
 * </pre>
 */

class Solution {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode headNode = new ListNode(0);
        ListNode nextNode = headNode;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                nextNode.next = l1;
                l1 = l1.next;
            } else {
                nextNode.next = l2;
                l2 = l2.next;
            }
            nextNode = nextNode.next;
        }

        if (l1 != null) nextNode.next = l1;
        if (l2 != null) nextNode.next = l2;
        return headNode.next;
    }

    public static void main(String... args) {
        ListNode l1 = generateNode(1, 2, 4);
//        printNodes(l1);

        ListNode l2 = generateNode(1, 3, 4);
//        printNodes(l2);

        Solution solution = new Solution();
        printNodes(solution.mergeTwoLists(l1, l2));
    }

    public static ListNode generateNode(int... args) {
        ListNode headNode = new ListNode(0);
        ListNode nextNode = headNode;
        for (int s : args) {
            ListNode newNode = new ListNode(s);
            nextNode.next = newNode;
            nextNode = nextNode.next;
        }
        return headNode.next;
    }

    public static void printNodes(ListNode listNode) {
        while (listNode.next != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(listNode.val);
    }
}

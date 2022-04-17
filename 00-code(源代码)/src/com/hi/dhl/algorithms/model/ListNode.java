package com.hi.dhl.algorithms.model;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
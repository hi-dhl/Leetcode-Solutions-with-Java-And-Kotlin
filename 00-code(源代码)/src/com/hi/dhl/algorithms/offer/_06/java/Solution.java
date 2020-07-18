package com.hi.dhl.algorithms.offer._06.java;

import com.hi.dhl.algorithms.model.ListNode;

import java.util.LinkedList;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        int count = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }
        int[] reverse = new int[count];
        currentNode = head;
        while (currentNode != null) {
            reverse[--count] = currentNode.val;
            currentNode = currentNode.next;
        }
        return reverse;
    }
}
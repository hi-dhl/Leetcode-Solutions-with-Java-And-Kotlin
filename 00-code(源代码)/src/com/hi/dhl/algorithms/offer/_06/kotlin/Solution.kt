package com.hi.dhl.algorithms.offer._06.kotlin

import com.hi.dhl.algorithms.model.ListNode


/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {

    fun reversePrint(head: ListNode?): IntArray = head?.let {
        var count = 0
        var currentNode = head
        while (currentNode != null) {
            count++
            currentNode = currentNode.next
        }

        val reverse = IntArray(count) { 0 }
        currentNode = head
        while (currentNode != null) {
            reverse[--count] = currentNode.`val`
            currentNode = currentNode.next
        }
        reverse
    } ?: intArrayOf()
}
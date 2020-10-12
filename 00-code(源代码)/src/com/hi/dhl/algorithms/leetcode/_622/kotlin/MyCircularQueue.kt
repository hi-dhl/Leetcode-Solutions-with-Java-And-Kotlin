package com.hi.dhl.algorithms.leetcode._622.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/9
 *     desc  :
 * </pre>
 */

class MyCircularQueue(k: Int) {

    var head = -1;
    var tail = -1;
    var data = IntArray(k);
    val size = k;

    fun enQueue(value: Int): Boolean {
        if (isFull()) return false
        if (isEmpty()) head = 0
        tail = (tail + 1) % size
        data[tail] = value
        return true
    }

    fun deQueue(): Boolean {
        if (isEmpty()) return false
        if (head == tail) {
            head = -1
            tail = -1
            return true
        }
        head = (head + 1) % size
        return true
    }

    fun Front(): Int {
        if (isEmpty()) return -1
        return data[head]
    }

    fun Rear(): Int {
        if (isEmpty()) return -1
        return data[tail]
    }

    fun isEmpty(): Boolean {
        return head == -1
    }

    fun isFull(): Boolean {
        return (tail + 1) % size == head
    }

}
package com.hi.dhl.algorithms.leetcode._155.kotlin

import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/9
 *     desc  :
 * </pre>
 */

class MinStack() {

    /** initialize your data structure here. */
    val stack = ArrayDeque<Int>()
    val minStack = ArrayDeque<Int>()

    fun push(x: Int) {
        stack.push(x)
        if (minStack.peek() == null) {
            minStack.push(x)
        } else {
            minStack.push(Math.min(x, minStack.peek()))
        }
    }

    fun pop() {
        stack.poll()
        minStack.poll()
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }

}
package com.hi.dhl.algorithms.offer._09.kotlin

import java.util.*

/**
 * <pre>
 *     author: dhl
 *     desc  : 两个栈实现一个队列
 * </pre>
 */

class CQueue() {
    val stack1 = Stack<Int>()
    val stack2 = Stack<Int>()

    fun appendTail(value: Int) {
        stack1.push(value);
    }

    fun deleteHead(): Int {
        return when {
            !stack2.isEmpty() -> stack2.pop()
            stack1.isEmpty() -> -1
            else -> {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop())
                }
                return stack2.pop()
            }
        }
    }
}
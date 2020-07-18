package com.hi.dhl.algorithms.offer._09.java;

import java.util.Stack;

/**
 * <pre>
 *     author: dhl
 *     desc  : 两个栈实现一个队列
 * </pre>
 */

class CQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else if (stack1.isEmpty()) {
            return -1;
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }
}
package com.hi.dhl.algorithms._155.java;

import java.util.ArrayDeque;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/9
 *     desc  :
 * </pre>
 */
class MinStack {
    ArrayDeque<Integer> stack;
    ArrayDeque<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new ArrayDeque<Integer>();
        minStack = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.peek() == null) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    public void pop() {
        stack.poll();
        minStack.poll();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

package com.hi.dhl.algorithms.leetcode._622.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/9
 *     desc  :
 * </pre>
 */
class MyCircularQueue {
    int[] data;
    int head;
    int tail;
    int size;

    public MyCircularQueue(int k) {
        // k + 1 有两个原因
        // 1. 为了避免冲突循环数组中任何时刻一定至少有一个位置不存放有效元素
        // 2. 当  k = 4 ，下标从 0 开始的，假设不移动 head， k 也不 +1 ，第四个元素始终放不进去
        size = k + 1;
        data = new int[size];
        head = 0;
        tail = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        data[tail] = value;
        tail = (tail + 1) % size;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = (head + 1) % size;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return data[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        // 因为数组中任何时刻一定至少有一个位置不存放有效元素，所以 tail - 1 取最近存放的元素
        // 假设 tail = 0 时，tail - 1 就会变成负数，下标会越界，所以 tail - 1 + size) % size
        return data[(tail - 1 + size) % size];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % size == head;
    }
}
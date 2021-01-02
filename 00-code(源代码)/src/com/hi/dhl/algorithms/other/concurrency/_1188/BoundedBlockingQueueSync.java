package com.hi.dhl.algorithms.other.concurrency._1188;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/1/2
 *     desc  :
 * </pre>
 */
class BoundedBlockingQueueSync {
    private int capacity;
    private final Object LOCK = new Object();
    private final List<Integer> queue = new ArrayList<>();

    public BoundedBlockingQueueSync(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (LOCK) {
            while (queue.size() >= capacity) {
                LOCK.wait();
            }
            queue.add(0, element);
            LOCK.notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        int element = 0;
        synchronized (LOCK) {
            while (queue.size() <= 0) {
                LOCK.wait();
            }
            element = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
            LOCK.notifyAll();
        }
        return element;
    }

    public int size() {
        return queue.size();
    }
}
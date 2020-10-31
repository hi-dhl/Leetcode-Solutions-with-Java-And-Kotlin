package com.hi.dhl.algorithms.other.concurrency._1116;

import java.util.function.IntConsumer;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/31
 *     desc  :
 * </pre>
 */
class ZeroEvenOddForWait {
    private int n;
    private final Object LOCK = new Object();
    private boolean zero = true;
    private int value = 0;

    public ZeroEvenOddForWait(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (LOCK) {
                while (!zero) {
                    LOCK.wait();
                }
                printNumber.accept(0);
                zero = false;
                value++;
                LOCK.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            synchronized (LOCK) {
                while (zero || (value & 1) == 1) {
                    LOCK.wait();
                }
                printNumber.accept(i);
                zero = true;
                LOCK.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            synchronized (LOCK) {
                while (zero || (value & 1) != 1) {
                    LOCK.wait();
                }
                printNumber.accept(i);
                zero = true;
                LOCK.notifyAll();
            }
        }
    }
}
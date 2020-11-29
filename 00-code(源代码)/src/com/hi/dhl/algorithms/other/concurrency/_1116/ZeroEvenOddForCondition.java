package com.hi.dhl.algorithms.other.concurrency._1116;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/31
 *     desc  :
 * </pre>
 */

class ZeroEvenOdd {
    private int n;
    private Lock lock = new ReentrantLock();
    private Condition czero = lock.newCondition();
    private Condition ceven = lock.newCondition();
    private Condition codd = lock.newCondition();
    private int value = 0;
    private boolean zero = true;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= n; i++) {
                while (!zero) {
                    czero.await();
                }
                printNumber.accept(0);
                zero = false;
                value++;
                if ((i & 1) == 1) {
                    codd.signalAll();
                } else {
                    ceven.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 2; i <= n; i += 2) {
                while (zero || (value & 1) == 1) {
                    ceven.await();
                }
                printNumber.accept(i);
                zero = true;
                czero.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= n; i += 2) {
                while (zero || (value & 1) != 1) {
                    codd.await();
                }
                printNumber.accept(i);
                zero = true;
                czero.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String... args) {
        ZeroEvenOdd condition = new ZeroEvenOdd(10);
        Thread tha = new Thread(() -> {
            try {
                condition.zero(value -> System.out.print(value));
            } catch (Exception e) {

            }
        });

        Thread thb = new Thread(() -> {
            try {
                condition.even(value -> System.out.print(value));
            } catch (Exception e) {

            }
        });

        Thread thc = new Thread(() -> {
            try {
                condition.odd(value -> System.out.print(value));
            } catch (Exception e) {

            }
        });
        tha.start();
        thb.start();
        thc.start();
    }
}
package com.hi.dhl.algorithms.other.concurrency._1115;

import java.util.concurrent.Semaphore;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/18
 *     desc  : 这是 LeetCode 多线程模块第 1115 号问题： 交替打印FooBar
 * </pre>
 */

class FooBar2 {
    private int n;
    private boolean done;
    private final Object lock = new Object();

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (done) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                done = true;
                lock.notifyAll();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (!done) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                done = false;
                lock.notifyAll();
            }

        }
    }
}
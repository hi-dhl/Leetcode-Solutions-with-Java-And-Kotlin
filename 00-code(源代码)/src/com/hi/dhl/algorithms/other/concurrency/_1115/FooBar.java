package com.hi.dhl.algorithms.other.concurrency._1115;

import java.util.concurrent.Semaphore;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/18
 *     desc  : 这是 LeetCode 多线程模块第 1115 号问题： 交替打印FooBar
 * </pre>
 */

class FooBar {
    private Semaphore sa = new Semaphore(1);
    private Semaphore sb = new Semaphore(0);
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            sa.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            sb.release();
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            sb.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            sa.release();
        }

    }
}
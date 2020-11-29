package com.hi.dhl.algorithms.other.concurrency._1114;

import java.util.concurrent.Semaphore;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/17
 *     desc  : 这是 LeetCode 多线程模块第 1114 号问题： 按序打印
 * </pre>
 */
class Foo {
    private Semaphore sa = new Semaphore(1);
    private Semaphore sb = new Semaphore(0);
    private Semaphore sc = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        try {
            sa.acquire();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
        } finally {
            sb.release();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        try {
            sb.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
        } finally {
            sc.release();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        try {
            sc.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        } finally {
            sa.release();
        }
    }

    // 测试
    public static void main(String... args) {
        Foo foo = new Foo();
        Thread tha = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.first(() -> System.out.print("first"));
                } catch (Exception e) {

                }
            }
        });

        Thread thb = new Thread(() -> {
            try {
                foo.second(() -> System.out.print("second"));
            } catch (Exception e) {

            }
        });

        Thread thc = new Thread(() -> {
            try {
                foo.third(() -> System.out.print("third"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tha.start();
        thb.start();
        thc.start();
    }
}

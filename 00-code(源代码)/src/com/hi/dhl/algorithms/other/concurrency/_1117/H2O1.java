package com.hi.dhl.algorithms.other.concurrency._1117;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/11/1
 *     desc  :
 * </pre>
 */
class H2O1 {
    private Semaphore sh = new Semaphore(2);
    private Semaphore so = new Semaphore(0);

    public H2O1() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        sh.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        so.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        so.acquire(2); // 等待 两个氢线程 到了之后，才能执行 氧线程
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        sh.release(2);
    }

    public static void main(String... agrs) {
        H2O1 h2o = new H2O1();
        Thread th1 = new Thread(() -> {
            try {
                h2o.hydrogen(() -> System.out.print("H"));
            } catch (Exception e) {

            }
        });

        Thread th2 = new Thread(() -> {
            try {
                h2o.hydrogen(() -> System.out.print("H"));
            } catch (Exception e) {

            }
        });

        Thread thb = new Thread(() -> {
            try {
                h2o.oxygen(() -> System.out.print("O"));
            } catch (Exception e) {

            }
        });

        th1.start();
        th2.start();
        thb.start();
    }
}
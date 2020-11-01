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
class H2O {

    private Semaphore sh = new Semaphore(2);
    private Semaphore so = new Semaphore(1);
    private CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            // 两个氢线程 和  一个氧线程 都到达之后，释放 H 和 0的信号量
            sh.release(2);
            so.release(1);
        }
    });

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        sh.acquire();
        releaseHydrogen.run();
        try {
            cb.await();
        } catch (Exception e) {

        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        so.acquire();
        releaseOxygen.run();
        try {
            cb.await();
        } catch (Exception e) {

        }
    }
}
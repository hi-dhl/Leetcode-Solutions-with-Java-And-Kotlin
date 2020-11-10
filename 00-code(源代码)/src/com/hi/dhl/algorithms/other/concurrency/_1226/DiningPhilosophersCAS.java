package com.hi.dhl.algorithms.other.concurrency._1226;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/11/10
 *     desc  :
 * </pre>
 */
class DiningPhilosophersCAS {
    private final AtomicInteger forks = new AtomicInteger(0);

    public DiningPhilosophersCAS() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        while (!forks.compareAndSet(0, 1)) Thread.sleep(1);
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        while (!forks.compareAndSet(1, 0)) Thread.sleep(1);
    }
}
package com.hi.dhl.algorithms.other.concurrency._1226;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/11/10
 *     desc  :
 * </pre>
 */

class DiningPhilosophersLock {
    private final ReentrantLock forkLock = new ReentrantLock();

    public DiningPhilosophersLock() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        forkLock.lock();
        try {
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
        } finally {
            forkLock.unlock();
        }
    }
}
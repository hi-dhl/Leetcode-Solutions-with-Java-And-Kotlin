package com.hi.dhl.algorithms.other.concurrency._1279;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/1/2
 *     desc  :
 * </pre>
 */
class TrafficLightLock {
    private final Lock lock = new ReentrantLock();
    private boolean road1GreenOn = false;

    public TrafficLightLock() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        lock.lock();
        try {
            if (roadId == 1 && road1GreenOn) {
                turnGreen.run();
                road1GreenOn = false;
            } else if (roadId == 2 && !road1GreenOn) {
                turnGreen.run();
                road1GreenOn = true;
            }
            crossCar.run();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
package com.hi.dhl.algorithms.other.concurrency._1279;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/1/2
 *     desc  :
 * </pre>
 */
class TrafficLightCAS {

    private final AtomicInteger atomic = new AtomicInteger();
    private boolean road1GreeOn = false;

    public TrafficLightCAS() {

    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        try {
            while (!atomic.compareAndSet(0, 1)) Thread.sleep(1);

            if (roadId == 1 && road1GreeOn) {
                turnGreen.run();
                road1GreeOn = false;
            } else if (roadId == 2 && !road1GreeOn) {
                turnGreen.run();
                road1GreeOn = true;
            }
            crossCar.run();

            while (!atomic.compareAndSet(1, 0)) Thread.sleep(1);
        } catch (Exception e) {

        }
    }
}
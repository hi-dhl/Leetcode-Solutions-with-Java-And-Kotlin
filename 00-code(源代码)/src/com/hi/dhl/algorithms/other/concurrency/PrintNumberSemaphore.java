package com.hi.dhl.algorithms.other.concurrency;

import java.util.concurrent.Semaphore;

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/1/8
 *     desc  :
 * </pre>
 */

class PrintNumberSemaphore {
    private Semaphore semaphoreOdd = new Semaphore(1);
    private Semaphore semaphoreEven = new Semaphore(0);

    private void printOdd() {
        while (true) {
            try {
                semaphoreOdd.acquire();
                System.out.println(1);
                Thread.sleep(1000);
                semaphoreEven.release();
            } catch (Exception e) {

            }
        }
    }

    private void printEven() {
        while (true) {
            try {
                semaphoreEven.acquire();
                System.out.println(2);
                Thread.sleep(1000);
                semaphoreOdd.release();
            } catch (Exception e) {

            }
        }
    }

    public static void main(String... args) {
        PrintNumberSemaphore lock = new PrintNumberSemaphore();
        new Thread(() -> {
            lock.printOdd();
        }).start();

        new Thread(() -> {
            lock.printEven();
        }).start();
    }
}

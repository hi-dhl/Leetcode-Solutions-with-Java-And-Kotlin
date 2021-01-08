package com.hi.dhl.algorithms.other.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/1/8
 *     desc  :
 * </pre>
 */

class PrintNumberLock {

    private ReentrantLock lock = new ReentrantLock();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();
    private boolean runOdd = true;

    private void printOdd() {
        while (true) {
            lock.lock();
            try {
                while (!runOdd) {
                    odd.await();
                }

                System.out.println(1);
                Thread.sleep(1000);
                runOdd = false;
                even.signalAll();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    private void printEven() {
        while (true) {
            lock.lock();
            try {
                while (runOdd) {
                    even.await();
                }
                System.out.println(2);
                Thread.sleep(1000);
                runOdd = true;
                odd.signalAll();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String... args) {
        PrintNumberLock lock = new PrintNumberLock();
        new Thread(() -> {
            lock.printEven();
        }).start();

        new Thread(() -> {
            lock.printOdd();
        }).start();
    }
}

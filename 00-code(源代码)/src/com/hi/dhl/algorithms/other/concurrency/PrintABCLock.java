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

class PrintABCLock {

    private ReentrantLock lock = new ReentrantLock();
    private Condition ca = lock.newCondition();
    private Condition cb = lock.newCondition();
    private Condition cc = lock.newCondition();
    private boolean runa = true;
    private boolean runb = false;
    private boolean runc = false;

    private void printA() {
        while (true) {
            lock.lock();
            try {
                while (!runa) {
                    ca.await();
                }
                System.out.println("A");
                Thread.sleep(1000);
                runa = false;
                runb = true;
                runc = false;
                cb.signalAll();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    private void printB() {
        while (true) {
            lock.lock();
            try {
                while (!runb) {
                    cb.await();
                }
                System.out.println("B");
                Thread.sleep(1000);
                runa = false;
                runb = false;
                runc = true;
                cc.signalAll();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    private void printC() {
        while (true) {
            lock.lock();
            try {
                while (!runc) {
                    cc.await();
                }
                System.out.println("C");
                Thread.sleep(1000);
                runa = true;
                runb = false;
                runc = false;
                ca.signalAll();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String... args) {
        PrintABCLock lock = new PrintABCLock();
        new Thread(() -> {
            lock.printA();
        }).start();

        new Thread(() -> {
            lock.printB();
        }).start();

        new Thread(() -> {
            lock.printC();
        }).start();
    }
}
package com.hi.dhl.algorithms.other.concurrency;

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/1/8
 *     desc  :
 * </pre>
 */

class PrintABCSync {
    private static final Object LOCK = new Object();
    private boolean runA = true;
    private boolean runB = false;
    private boolean runC = false;

    private void printA() {
        while (true) {
            synchronized (LOCK) {
                try {
                    while (!runA) {
                        LOCK.wait();
                    }
                    System.out.println("A");
                    Thread.sleep(1000);
                    runA = false;
                    runB = true;
                    runC = false;
                    LOCK.notifyAll();
                } catch (Exception e) {

                }
            }
        }
    }

    private void printB() {
        while (true) {
            synchronized (LOCK) {
                try {
                    while (!runB) {
                        LOCK.wait();
                    }
                    System.out.println("B");
                    Thread.sleep(1000);
                    runA = false;
                    runB = false;
                    runC = true;
                    LOCK.notifyAll();
                } catch (Exception e) {

                }
            }
        }
    }

    private void printC() {
        while (true) {
            synchronized (LOCK) {
                try {
                    while (!runC) {
                        LOCK.wait();
                    }

                    System.out.println("C");
                    Thread.sleep(1000);
                    runA = true;
                    runB = false;
                    runC = false;
                    LOCK.notifyAll();
                } catch (Exception e) {

                }
            }
        }
    }

    public static void main(String... args) {
        PrintABCSync lock = new PrintABCSync();
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
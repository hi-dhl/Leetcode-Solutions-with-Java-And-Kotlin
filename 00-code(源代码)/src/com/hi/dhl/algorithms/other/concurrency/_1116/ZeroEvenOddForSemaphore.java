package com.hi.dhl.algorithms.other.concurrency._1116;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/31
 *     desc  :
 * </pre>
 */
class ZeroEvenOddForSemaphore {
    private int n;
    private Semaphore szero = new Semaphore(1);
    private Semaphore seven = new Semaphore(0);
    private Semaphore sodd = new Semaphore(0);

    public ZeroEvenOddForSemaphore(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            szero.acquire();
            printNumber.accept(0);
            if ((i & 1) == 1) {
                sodd.release();
            } else {
                seven.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            seven.acquire();
            printNumber.accept(i);
            szero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            sodd.acquire();
            printNumber.accept(i);
            szero.release();
        }
    }

    public static void main(String... args) {
        ZeroEvenOddForSemaphore semaphore = new ZeroEvenOddForSemaphore(10);
        Thread tha = new Thread(() -> {
            try {
                semaphore.zero(value -> System.out.print(value));
            } catch (Exception e) {

            }
        });

        Thread thb = new Thread(() -> {
            try {
                semaphore.even(value -> System.out.print(value));
            } catch (Exception e) {

            }
        });

        Thread thc = new Thread(() -> {
            try {
                semaphore.odd(value -> System.out.print(value));
            } catch (Exception e) {

            }
        });
        tha.start();
        thb.start();
        thc.start();
    }
}
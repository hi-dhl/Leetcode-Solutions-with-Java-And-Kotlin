package com.hi.dhl.algorithms.other.concurrency._1195;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/11/6
 *     desc  :
 * </pre>
 */
class FizzBuzzSemaphore {
    private int n;
    private Semaphore sNumber = new Semaphore(1);
    private Semaphore sFizzBuzz = new Semaphore(0);
    private Semaphore sBuzz = new Semaphore(0);
    private Semaphore sFizz = new Semaphore(0);
    volatile int value = 0;

    public FizzBuzzSemaphore(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                sFizz.acquire();
                printFizz.run();
                sNumber.release();
            }

        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                sBuzz.acquire();
                printBuzz.run();
                sNumber.release();
            }

        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            sFizzBuzz.acquire();
            printFizzBuzz.run();
            sNumber.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            sNumber.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                sFizzBuzz.release();
            } else if (i % 3 == 0) {
                sFizz.release();
            } else if (i % 5 == 0) {
                sBuzz.release();
            } else {
                printNumber.accept(i);
                sNumber.release();
            }
        }
    }

    public static void main(String... args) {
        FizzBuzzSemaphore fizzBuzz = new FizzBuzzSemaphore(15);
        Thread tha = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.print("fizz "));
            } catch (Exception e) {

            }
        });
        Thread thb = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.print("buzz "));
            } catch (Exception e) {

            }
        });

        Thread thc = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.print("fizzbuzz "));
            } catch (Exception e) {

            }
        });

        Thread thd = new Thread(() -> {
            try {
                fizzBuzz.number(value1 -> System.out.print(value1 + " "));
            } catch (Exception e) {

            }
        });

        tha.start();
        thb.start();
        thc.start();
        thd.start();
    }

}
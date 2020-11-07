package com.hi.dhl.algorithms.other.concurrency._1195;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/11/6
 *     desc  :
 * </pre>
 */

class FizzBuzzLock {
    private int n;
    private Lock lock = new ReentrantLock();
    private Condition conNumber = lock.newCondition();
    private Condition conFizz = lock.newCondition();
    private Condition conBuzz = lock.newCondition();
    private Condition conFizzBuzz = lock.newCondition();

    public FizzBuzzLock(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 3; i <= n; i += 3) {
                if (i % 5 != 0) {
                    conFizz.await();
                    printFizz.run();
                    conNumber.signalAll();
                }

            }
        } finally {
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 5; i <= n; i += 5) {
                if (i % 3 != 0) {
                    conBuzz.await();
                    printBuzz.run();
                    conNumber.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 15; i <= n; i += 15) {
                conFizzBuzz.await();
                printFizzBuzz.run();
                conNumber.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    conFizzBuzz.signalAll();
                    conNumber.await();
                } else if (i % 3 == 0) {
                    conFizz.signalAll();
                    conNumber.await();
                } else if (i % 5 == 0) {
                    conBuzz.signalAll();
                    conNumber.await();
                } else {
                    printNumber.accept(i);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String... args) {
        FizzBuzzLock fizzBuzzLock = new FizzBuzzLock(15);
        Thread thd = new Thread(() -> {
            try {
                fizzBuzzLock.number(value -> System.out.print(value + " "));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread tha = new Thread(() -> {
            try {
                fizzBuzzLock.fizz(() -> System.out.print("fizz "));
            } catch (Exception e) {
            }
        });
        Thread thb = new Thread(() -> {
            try {
                fizzBuzzLock.buzz(() -> System.out.print("buzz "));
            } catch (Exception e) {

            }
        });

        Thread thc = new Thread(() -> {
            try {
                fizzBuzzLock.fizzbuzz(() -> System.out.print("buzz "));
            } catch (Exception e) {

            }
        });
        tha.start();
        thb.start();
        thc.start();
        thd.start();
    }
}
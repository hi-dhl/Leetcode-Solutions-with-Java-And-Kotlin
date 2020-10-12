package com.hi.dhl.algorithms.other.concurrency;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  :
 * </pre>
 */
public class PrintNumber {

    private static final int THREAD_COUNT = 2; // 线程的数量
    private static final Object LOCK = new Object();
    private static int value = 0;

    public static void main(String... args) {
        new PrintThread(0).start();
        new PrintThread(1).start();
    }

    static class PrintThread extends Thread {
        int threadNo; // 为每个线程做一个标记

        PrintThread(int threadNo) {
            this.threadNo = threadNo;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (LOCK) {

                        while ((value % THREAD_COUNT) != threadNo) { // 如果不是当前线程，就一直等待
                            LOCK.wait();
                        }

                        Thread.sleep(1000); // 为了更好的看到打印效果，可有可无
                        System.out.println(Thread.currentThread().getName() + " : " + (value % THREAD_COUNT + 1));
                        value++;

                        // 因为最外层是 while(true) , 为了防止无限制变大
                        if (value == 10) {
                            value = 0;
                        }
                        LOCK.notifyAll(); // 唤醒线程
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
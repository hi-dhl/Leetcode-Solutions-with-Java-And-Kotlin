package com.hi.dhl.algorithms.other.concurrency._1188;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author: dhl
 *     date  : 2021/1/2
 *     desc  :
 * </pre>
 */
class BoundedBlockingQueueLock {
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private final List<Integer> queue = new ArrayList<>();

    public BoundedBlockingQueueLock(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= capacity) {
                full.await();
            }
            queue.add(0, element);
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        int element = 0;
        try {
            while (queue.size() <= 0) {
                empty.await();
            }
            element = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
            full.signalAll();
        } finally {
            lock.unlock();
        }
        return element;
    }

    public int size() {
        return queue.size();
    }

    public static void main(String... args){
        BoundedBlockingQueueLock lock = new BoundedBlockingQueueLock(20);
        for(int i = 1;i<30;i++){
            Thread tha = new Thread(()->{
               try{
                   lock.enqueue(2);
               } catch(Exception e){

               }
            });
            tha.start();

            Thread thb = new Thread(()->{
               try{
                   System.out.println(lock.dequeue());
               } catch(Exception e){

               }
            });
            thb.start();
        }
    }
}
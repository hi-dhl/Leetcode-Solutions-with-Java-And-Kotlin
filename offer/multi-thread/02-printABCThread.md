## 题目描述

来自头条的一道面试题，实现三个线程交替打印 "ABC"

**示例 :**

```
A
B
C
A
B
C
......
```

这道题主要考多线程的基本知识点，实现方式有很多种：

* 使用 Object 的 `wait/notify/notifyAll` 的消息通知机制
* 使用 Lock 的 Condition 的 `await/signal/signalAll` 的消息通知机制
* 通过 Semaphore（信号量） 实现

在上一道题中 「 线程交替打印数字 」 通过 `wait/notify/notifyAll` 来实现了线程交替打印，这道题我们通过另外一种方式   Semaphore（信号量） 来实现 三个线程交替打印 "ABC"


### 知识储备

在 Java 中常用的线程同步就是 Lock 和 Synchronized，还可以通过 JUC 提供了一些并发工具类 `CountDownLatch` 、`CyclicBarrier` 、`Semaphore` 来实现并发，本文主要介绍 `Semaphore` ，而  `Semaphore` 内部类 Sync 通过继承 `AbstractQueuedSynchronizer` 来实现并发的，  `AbstractQueuedSynchronizer` 简称 AQS

```
abstract static class Sync extends AbstractQueuedSynchronizer {
}
```

AQS 内部使用了一个被 volatile 修饰的 int 类型的变量 state 来表示同步状态

```
private volatile int state;
```

`state` 表示许可数，当 `state > 0` 时表示允许多少个线程来访问，当 `state = 0` 时，请求线程将无法获取同步状态，线程将被加入到同步队列并阻塞，它提供了三个方法 `getState()` 、 `setState(int newState)` 、 `compareAndSetState(int expect,int update)`  来对同步状态 state 进行操作。

另外 AQS 使用了 FIFO 同步队列来完成资源获取线程的排队工作，如果当前线程获取同步状态失败，AQS 则会将当前线程以及等待状态等信息构造成一个节点（Node）并将其加入同步队列中，同时会阻塞当前线程，当同步状态释放时，会唤醒它的后继节点（next）将自己设为独占状态

AQS 支持两种同步模式：独占式获取同步状态（同时只有一个线程能执行）、共享式获取同步状态（有多个线程可同时执行），对应的方法如下所示：

* `tryAcquire(int arg)` ：独占式获取同步状态，获取同步状态成功后，其他线程需要等待该线程释放同步状态才能获取同步状态
* `tryRelease(int arg)` ：独占式释放同步状态
* `tryAcquireShared(int arg)` ：共享式获取同步状态，返回值大于等于0则表示获取成功，否则获取失败
* `tryReleaseShared(int arg)` ：共享式释放同步状态

具体是那种模式需要看 AQS 的子类的实现方案，`Semaphore` 内部类 Sync 实现了   `tryAcquireShared(int arg)` 和 `tryReleaseShared(int arg)` 方法，使用的是共享式获取同步状态。

#### Semaphore 的主要方法

**构造方法：**

```
// 默认创建公平锁 
// permits 表示同一时间访问共享资源的线程数
public Semaphore(int permits) {
    sync = new NonfairSync(permits);
}
    
public Semaphore(int permits, boolean fair) {
    sync = fair ? new FairSync(permits) : new NonfairSync(permits);
}
```

Semaphore 有两个构造方法，分别指定了是采用公平锁还是非公平锁，默认是公平锁，其中 permits 的值直接传给了 AQS 父类，也就是设置了 AQS 的 state 属性。

**acquire() 方法**

`acquire()` 方法最终调用的是 `tryAcquireShared`，当 `state` 大于 0 时，当线程调用了 `acquire()` 方法 state 值减 1。

当 `state` 等于 0，在次调用  `acquire()` 方法，线程将会被加入到同步队列并阻塞，直到其他线程调用 `release()` 方法，对 state 值加 1 ，当前线程才可以继续访问。

**release() 方法**

`release()` 方法主要作用就是释放资源，最终调用的是 `tryReleaseShared` 方法，调用 `release()` 方法，对 state 值加 1。

每次调用 `acquire()` 方法的时候，一定要保证 `release()` 方法的执行, 否则会导致资源一直无法释放，建议写在 finally 中，另外需要注意的是 `acquire()` 方法 和 `release()` 一定是成对出现的。

对于这道题来说，基本知识了解到这里就足够了，我们来看一下代码如何实现， Java 和 Kotlin 实现大体上一致，这里主要演示 Java 的写法。

## 代码实现

```

class PrintABCSemaphore {

    private static Semaphore sa = new Semaphore(1);
    private static Semaphore sb = new Semaphore(0);
    private static Semaphore sc = new Semaphore(0);

    private static class PrintAThread extends Thread {
        @Override
        public void run() {

            while (true) {
                try {

                    sa.acquire();
                    System.out.println("A");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sb.release();
                }
            }

        }
    }

    private static class PrintBThread extends Thread {

        @Override
        public void run() {

            while (true) {
                try {

                    sb.acquire();
                    System.out.println("B");
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sc.release();
                }
            }

        }
    }

    private static class PrintCThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    sc.acquire();
                    System.out.println("C");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sa.release();
                }
            }
        }
    }

    public static void main(String... args) {
        new PrintAThread().start();
        new PrintBThread().start();
        new PrintCThread().start();
    }
}

```

### **await/signal/signalAll 实现**

```
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
```

### **wait/notify/notifyAll 实现**

```
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
```


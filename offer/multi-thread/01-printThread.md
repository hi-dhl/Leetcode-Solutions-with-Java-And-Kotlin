## 题目描述

利用两个线程交替打印数字，一个线程输出 1 ，另外一个线程输出 2

**示例 :**

```
1
2
1
2
1
2
......
```

这道题主要考多线程的基本知识点，实现方式有很多种：

* 使用 Object 的 `wait/notify/notifyAll` 的消息通知机制
* 使用 Lock 的 Condition 的 `await/signal/signalAll` 的消息通知机制
* 通过 Semaphore（信号量） 实现

在这里我们主要来演示在多线程中如何使用 `wait/notify/notifyAll` 消息通知机制，这也是面试常问的知识点，我们先来了解 `wait/notify/notifyAll` 相关的 API

### 知识储备

**`wait/notify/notifyAll` 相关的 API**

```
void notify() ：唤醒在此对象监视器上等待的单个线程

void notifyAll() ：唤醒在此对象监视器上等待的所有线程

void wait( ) ：使当前的线程等待，直到其他线程调用此对象的notify( ) 方法或 notifyAll( ) 方法

void wait(long timeout) ：使当前的线程等待，直到其他线程调用此对象的notify() 方法或 notifyAll() 方法，或者超过指定的时间。

void wait(long timeout, int nanos) ：使当前的线程等待，直到其他线程调用此对象的notify( ) 方法或 notifyAll( ) 方法，或者其他线程打断了当前线程，或者超过指定的时间。
```

#### wait/notify/notifyAll 原理

JVM 会给每个对象维护一个入口集 (Entry Set) 和等待集 (Wait Set)。

* 入口集 (Entry Set)：用于存储申请该对象内部锁的线程
* 等待集 (Wait Set)：用于存储对象上的等待线程。

调用 `wait()` 方法会将当前线程暂停，释放当前线程的对象锁，将当前线程存入该方法所属的对象等待集中。

调用对象的 `notify()` 方法，会唤醒该对象等待集中的任意一个线程，被唤醒的线程会继续留在对象的等待集中，直到该线程再次持有对应的内部锁时，`wait()` 方法会把当前线程从对象的等待集中移除，继续执行 `obj.wait()` 的下一条语句，等到退出同步块后，当前线程才会释放锁。

调用对象的 `notifyAll()` 方法，和 `notify()` 方法类似，唤醒该对象等待集中的所有线程，只有一个线程可以获取到锁，其他的线程继续等待。

#### wait/notify/notifyAll 潜在的一些问题

**必须要在同步代码块中执行**

因为在 `wait()` 方法的 native 代码中，会判断线程是否持有当前对象的内部锁，如果没有的话，就会报非法监视器状态异常。

**过早唤醒**

通知线程优先于等待线程执行，造成等待线程会一直处于等待状态，导致资源浪费，直到被别的线程打断，所以需要在等待线程中添加一个 bool 类型的状态标志位，先判断状态是否发生改变，如果通知线程优先执行，更新一下状态，等待线程就不会在继续等待了。

**状态标志位发生变化**

常见的错误写法，使用 if 语句来判断标志位是否发生变化，当有多个等待线程的时候，就会出现问题，假设集合只有一个元素，等待线程 1 被唤醒，继续执行 `obj.wait()` 的下一条语句。


```
if(list.isEmpty()){ 
    obj.wait();
}
int a = list.remove(0) // 移除第一个元素
```

接着等待线程 2 被唤醒，继续执行 `obj.wait()` 的下一条语句。

```
if(list.isEmpty()){
    obj.wait();
}
// 执行这里的时候会抛出异常，因为集合中只有一个元素，等待线程 1 已经移除了
int a = list.remove(0) 
```

所以应该使用 while 语句来判断标志位是否发生变化。

```
while(list.isEmpty()){
    obj.wait();
}
int a = list.remove(0) 
```

**假死状态**

如果是多消费者和多生产者情况，使用  `notify` 方法可能会出现信号丢失，即唤醒的是同类线程，因为 `notify` 方法会唤醒该对象等待集中的任意一个线程，不确定会唤醒的是哪一个线程，所以应该使用 `notifyAll()` 方法。

**欺骗性唤醒**

等待线程可能在没有其他线程执行 `notify()/notifyAll()` 的情况下被唤醒，这种现象叫欺骗性唤醒。

#### wait()和sleep()的区别

* `sleep()` 方法是线程类（Thread）的静态方法，`sleep()` 方法不会释放锁，会一直等待超时结束

* `wait()` 是 Object 类的方法，调用 `wait()` 方法会释放当前线程的对象锁。调用对象的 `wait()` 方法会将当前线程暂停，将当前线程存入该方法所属的对象等待集中，只有调用 `notify/notifyAll` 方法才能唤醒线程


## 代码实现

```
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
```

输出结果如下所示：

```
Thread-0 : 1
Thread-1 : 2
Thread-0 : 1
Thread-1 : 2
Thread-0 : 1
Thread-1 : 2
Thread-0 : 1
Thread-1 : 2
```

### **await/signal/signalAll 实现**

```
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
```


### Semaphore 的主要方法

```
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
```


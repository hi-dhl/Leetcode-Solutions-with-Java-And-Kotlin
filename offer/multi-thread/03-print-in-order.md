题目来源于 LeetCode 多线程 第 1114 号问题：按序打印。题目难度为 Easy。

* [中文地址：https://leetcode-cn.com/problems/print-in-order](https://leetcode-cn.com/problems/print-in-order)

## 题目描述

我们提供了一个类：

```
public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
```

三个不同的线程将会共用一个 Foo 实例。

* 线程 A 将会调用 first() 方法
* 线程 B 将会调用 second() 方法
* 线程 C 将会调用 third() 方法

请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。

**示例 1:**

```
输入: [1,2,3]
输出: "firstsecondthird"
解释: 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
正确的输出是 "firstsecondthird"。
```

**示例 2:**

```
输入: [1,3,2]
输出: "firstsecondthird"
解释: 
输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
正确的输出是 "firstsecondthird"。
```

**提示：**

* 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
* 你看到的输入格式主要是为了确保测试的全面性。

## 思路：通过 Semaphore 来实现


JUC 提供了一些并发工具类 `CountDownLatch` 、`CyclicBarrier` 、`Semaphore` 来实现并发，`Semaphore` 内部类 Sync 通过继承 `AbstractQueuedSynchronizer` 来实现并发的，**关于 AQS 和 Semaphore 在之前的文章 「 头条-线程交替打印 ABC 」 中已经详细分析了，这里我们就来看一下 Semaphore 用到几个 API 方法**。

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

Java 和 Kotlin 实现大体上一致，这里主要演示 Java 的写法。

### Java 实现

```
class Foo {
    private Semaphore sa = new Semaphore(1);
    private Semaphore sb = new Semaphore(0);
    private Semaphore sc = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        try {
            sa.acquire();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
        } finally {
            sb.release();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        try {
            sb.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
        } finally {
            sc.release();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        try {
            sc.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        } finally {
            sa.release();
        }
    }

    // 测试
    public static void main(String... args) {
        Foo foo = new Foo();
        Thread tha = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.first(() -> System.out.print("first"));
                } catch (Exception e) {

                }
            }
        });

        Thread thb = new Thread(() -> {
            try {
                foo.second(() -> System.out.print("second"));
            } catch (Exception e) {

            }
        });

        Thread thc = new Thread(() -> {
            try {
                foo.third(() -> System.out.print("third"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tha.start();
        thb.start();
        thc.start();
    }
}
```


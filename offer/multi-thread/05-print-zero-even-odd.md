题目来源于 LeetCode 多线程 第 1116 号问题：打印零与奇偶数。题目难度为 Middle。

* [中文地址：https://leetcode-cn.com/problems/print-zero-even-odd](https://leetcode-cn.com/problems/print-zero-even-odd)

## 题目描述

假设有这么一个类：

```
class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // 构造函数
  public void zero(printNumber) { ... }  // 仅打印出 0
  public void even(printNumber) { ... }  // 仅打印出 偶数
  public void odd(printNumber) { ... }   // 仅打印出 奇数
}
```

相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：

* 线程 A 将调用 zero()，它只输出 0 。
* 线程 B 将调用 even()，它只输出偶数。
* 线程 C 将调用 odd()，它只输出奇数。

每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。

**示例 1：**

```
输入：n = 2
输出："0102"
说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
```

**示例 2：**

```
输入：n = 5
输出："0102030405"
```

## 思路：

这道题主要考多线程的基本知识点，实现方式有很多种：

* 通过 Semaphore（信号量） 实现，关于 AQS 和 Semaphore 原理以及使用注意事项，在之前的文章已经详细分析过了， 如果不了解可以前去查看 **「 头条-线程交替打印 ABC 」** 

* 使用 Object 的 `wait/notify/notifyAll` 的消息通知机制，之前的文章已经详细分析过了 `wait/notify/notifyAll` 原理以及注意事项，如果不了解可以前去查看 **「 阿里-线程交替打印数字 」**

* 使用 Lock 的 Condition 的 `await/signal/signalAll` 的消息通知机制

Java 和 Kotlin 实现大体上一致，这里主要演示 Java 的写法。

### `await/signal/signalAll` 实现

```
class ZeroEvenOdd {
    private int n;
    private Lock lock = new ReentrantLock();
    private Condition czero = lock.newCondition();
    private Condition ceven = lock.newCondition();
    private Condition codd = lock.newCondition();
    private int value = 0;
    private boolean zero = true;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= n; i++) {
                while (!zero) {
                    czero.await();
                }
                printNumber.accept(0);
                zero = false;
                value++;
                if ((i & 1) == 1) {
                    codd.signalAll();
                } else {
                    ceven.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 2; i <= n; i += 2) {
                while (zero || (value & 1) == 1) {
                    ceven.await();
                }
                printNumber.accept(i);
                zero = true;
                czero.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= n; i += 2) {
                while (zero || (value & 1) != 1) {
                    codd.await();
                }
                printNumber.accept(i);
                zero = true;
                czero.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
```

### wait/notify/notifyAll 实现

```
class ZeroEvenOdd {
    private int n;
    private final Object LOCK = new Object();
    private boolean zero = true;
    private int value = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (LOCK) {
                while (!zero) {
                    LOCK.wait();
                }
                printNumber.accept(0);
                zero = false;
                value++;
                LOCK.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            synchronized (LOCK) {
                while (zero || (value & 1) == 1) {
                    LOCK.wait();
                }
                printNumber.accept(i);
                zero = true;
                LOCK.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            synchronized (LOCK) {
                while (zero || (value & 1) != 1) {
                    LOCK.wait();
                }
                printNumber.accept(i);
                zero = true;
                LOCK.notifyAll();
            }
        }
    }
}
```

### Semaphore（信号量） 实现

```
class ZeroEvenOdd {
    private int n;
    private Semaphore szero = new Semaphore(1);
    private Semaphore seven = new Semaphore(0);
    private Semaphore sodd = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i++){
            szero.acquire();
            printNumber.accept(0);
            if((i & 1) == 1){
                sodd.release();
            }else{
                seven.release();
            }
        }    
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i<=n; i+=2){
            seven.acquire();
            printNumber.accept(i);
            szero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i<=n; i+=2){
            sodd.acquire();
            printNumber.accept(i);
            szero.release();
        }
    }
}
```



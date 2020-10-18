题目来源于 LeetCode 多线程 第 1115 号问题：交替打印FooBar。题目难度为 Middle。

* [中文地址：https://leetcode-cn.com/problems/print-foobar-alternately](https://leetcode-cn.com/problems/print-foobar-alternately)

## 题目描述

我们提供一个类：

```
class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
```

两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。

请设计修改程序，以确保 "foobar" 被输出 n 次。

**实例 1：**

```
输入: n = 1
输出: "foobar"
解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
```

**实例 2：**

```
输入: n = 2
输出: "foobarfoobar"
解释: "foobar" 将被输出两次。
```

## 思路：

这道题主要考多线程的基本知识点，实现方式有很多种：

* 通过 Semaphore（信号量） 实现，关于 AQS 和 Semaphore 原理以及使用注意事项，在之前的文章已经详细分析过了， 如果不了解可以前去查看 **「 头条-线程交替打印 ABC 」** 

* 使用 Object 的 `wait/notify/notifyAll` 的消息通知机制，之前的文章已经详细分析过了 `wait/notify/notifyAll` 原理以及注意事项，如果不了解可以前去查看 **「 阿里-线程交替打印数字 」**

Java 和 Kotlin 实现大体上一致，这里主要演示 Java 的写法。

### Semaphore（信号量） 实现

```
class FooBar {
    private Semaphore sa = new Semaphore(1);
    private Semaphore sb = new Semaphore(0);
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            sa.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            sb.release();
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            sb.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            sa.release();
        }

    }
}
```

### wait/notify/notifyAll 实现

```
class FooBar2 {
    private int n;
    private boolean done;
    private final Object lock = new Object();

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (done) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                done = true;
                lock.notifyAll();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (!done) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                done = false;
                lock.notifyAll();
            }

        }
    }
}
```


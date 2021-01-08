题目来源于 LeetCode 多线程 第 1195 号问题：交替打印字符串。题目难度为 Middle。

* [中文地址：https://leetcode-cn.com/problems/fizz-buzz-multithreaded](https://leetcode-cn.com/problems/fizz-buzz-multithreaded)

## 题目描述

编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：

* 如果这个数字可以被 3 整除，输出 "fizz"。
* 如果这个数字可以被 5 整除，输出 "buzz"。
* 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。

例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。

假设有这么一个类：

```
class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}
```

请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：

* 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
* 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
* 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
* 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。

## 思路：

通过四个线程（A、B、C、D）共同的协作，依次输出： 数字、fizz、buzz、fizzbuzz

* 线程 A 将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
* 线程 B 将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
* 线程 C 将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
* 线程 D 将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。

那么我们只需要保证线程按照如下规则执行即可：

* 线程 D 在线程 A 之前执行
* 线程 A 在线程 B 之前执行
* 线程 B 在线程 C 之前执行

那么如何保证线程按照上面的顺序执行呢，根据示例输出的结果 `1, 2, fizz, 4, buzz` ，首先输出的是数字，我们可以通过控制 **线程D调用 number() 方法** 在这个方法里面通过判断 **是否能被 3、5 整除**，来控制其它三个线程（A、B、C）

* 如果能同时能被 3 和 5 整除，则唤醒线程 C，输出 fizzbuzz 同时唤醒线程D，否则线程 C 阻塞
* 如果能被 3 整除，则唤醒线程 A，输出 fizz 同时唤醒线程D，否则线程 A 阻塞
* 如果能被 5 整除，则唤醒线程 B，输出 buzz 同时唤醒线程D，否则线程 B 阻塞

**这道题主要介绍以下两种实现方式：**

* 通过 Semaphore（信号量） 实现，关于 AQS 和 Semaphore 原理以及使用注意事项，在之前的文章已经详细分析过了， 如果不了解可以前去查看 「 头条-线程交替打印 ABC 」
* 使用 Lock 的 Condition 的 await/signal/signalAll 的消息通知机制


> Java 和 Kotlin 实现大体上一致，这里主要演示 Java 的写法。

<!-- tabs:start -->

### **await/signal/signalAll 实现**

```
class FizzBuzz {
    private int n;
    private Lock lock = new ReentrantLock();
    private Condition conNumber = lock.newCondition();
    private Condition conFizz = lock.newCondition();
    private Condition conBuzz = lock.newCondition();
    private Condition conFizzBuzz = lock.newCondition();

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        try {
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
        lock.lock();
        try {
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
        lock.lock();
        try {
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
        lock.lock();
        try {
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
```

### **Semaphore 实现**

```
class FizzBuzz {
    private int n;
    private Semaphore sNumber = new Semaphore(1);
    private Semaphore sFizzBuzz = new Semaphore(0);
    private Semaphore sBuzz = new Semaphore(0);
    private Semaphore sFizz = new Semaphore(0);
    volatile int value = 0;

    public FizzBuzz(int n) {
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
```

<!-- tabs:end -->


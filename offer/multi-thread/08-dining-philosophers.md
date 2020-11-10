题目来源于 LeetCode 多线程 第 1226 号问题：哲学家进餐。题目难度为 Middle。

* [中文地址：https://leetcode-cn.com/problems/the-dining-philosophers](https://leetcode-cn.com/problems/the-dining-philosophers)

## 题目描述

5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）

所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。

假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。

设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。

![](http://img.hi-dhl.com/16049695340941.jpg)

哲学家从 0 到 4 按 顺时针 编号。请实现函数 void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)：

* philosopher 哲学家的编号。
* pickLeftFork 和 pickRightFork 表示拿起左边或右边的叉子。
* eat 表示吃面。
* putLeftFork 和 putRightFork 表示放下左边或右边的叉子。
* 由于哲学家不是在吃面就是在想着啥时候吃面，所以思考这个方法没有对应的回调。

给你 5 个线程，每个都代表一个哲学家，请你使用类的同一个对象来模拟这个过程。在最后一次调用结束之前，可能会为同一个哲学家多次调用该函数。

示例

```
输入：n = 1
输出：[[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
解释:
n 表示每个哲学家需要进餐的次数。
输出数组描述了叉子的控制和进餐的调用，它的格式如下：
output[i] = [a, b, c] (3个整数)
- a 哲学家编号。
- b 指定叉子：{1 : 左边, 2 : 右边}.
- c 指定行为：{1 : 拿起, 2 : 放下, 3 : 吃面}。
如 [4,2,1] 表示 4 号哲学家拿起了右边的叉子。
```


## 思路：

根据题意：哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食，意味着哲学家如果想要进食需要同时拿起左右叉子。

我们可以构建一个临界区，每次允许一个哲学家进入这个临界区：拿起左右叉子 + 吃意面 + 放下左右叉子，流程走完之后退出临界区。

**这道题有多种实现方式：**

* 使用 Object 的 `wait/notify/notifyAll` 的消息通知机制
* 使用 Lock 的 Condition 的 `await/signal/signalAll` 的消息通知机制
* 通过 Semaphore（信号量） 实现，关于 AQS 和 Semaphore 原理以及使用注意事项，在之前的文章已经详细分析过了， 如果不了解可以前去查看 「 头条-线程交替打印 ABC 」
* 通过 ReentrantLock 实现
* 通过 CAS 实现

在之前的文章里面已经介绍过前三种，这里只演示

* 通过 ReentrantLock 实现
* 通过 CAS 实现

### CAS 实现

CAS 是项乐观锁技术，当多个线程尝试使用 CAS 同时更新同一个变量时，只有其中一个线程能更新变量的值，而其它线程都失败，失败的线程并不会被挂起，而是被告知这次竞争中失败，并可以再次尝试。

> 乐观锁是一种思想。CAS是这种思想的一种实现方式。

CAS 操作包含三个操作数 —— 内存中的值（V）、预期原值（A）和新值(B)。如果内存中的值与预期原值相匹配，会自动更新内存中的值为新值，否则一直在原地循环等待


```
class DiningPhilosophers {
    private final AtomicInteger forks = new AtomicInteger(0);

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        // 取内存的值 0 与 1 进行比较，如果不相等更新内存中的值为 1
        // 其他线程进来，因为资源没有释放，内存中的值 == 期待值 == 1 所以在这里一直循环等待释放资源
        while (!forks.compareAndSet(0, 1)) Thread.sleep(1);
        // 拿起左右叉子
        pickLeftFork.run();
        pickRightFork.run(); 
        // 吃意面 
        eat.run();
        // 放下左右叉子
        putLeftFork.run();
        putRightFork.run();
        
        // 流程走完之后通过 CAS 的方式修改内存中的值为 0，让其他线程可以进入临界区
        while (!forks.compareAndSet(1, 0)) Thread.sleep(1);
    }
}
```


### ReentrantLock 实现

```
class DiningPhilosophers {
    private final ReentrantLock forkLock = new ReentrantLock();

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        forkLock.lock(); // 同时只能允许一个哲学家进入临界区
        try {
            // 拿起左右叉子
            pickLeftFork.run();
            pickRightFork.run(); 
            // 吃意面 
            eat.run();
            // 放下左右叉子
            putLeftFork.run();
            putRightFork.run();
        } finally {
            // 流程走完之后退出临界区
            forkLock.unlock();
        }
    }
}
```


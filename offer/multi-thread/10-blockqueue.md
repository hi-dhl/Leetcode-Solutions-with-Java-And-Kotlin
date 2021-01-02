题目来源于 LeetCode 多线程 第 1188 号问题：设计有限阻塞队列。题目难度为 Middle。

* [中文地址：https://leetcode-cn.com/problems/design-bounded-blocking-queue](https://leetcode-cn.com/problems/design-bounded-blocking-queue)

## 题目描述

实现一个拥有如下方法的线程安全有限阻塞队列：

* `BoundedBlockingQueue(int capacity)` 构造方法初始化队列，其中 `capacity` 代表队列长度上限。
* `void enqueue(int element)` 在队首增加一个 `element` . 如果队列满，调用线程被阻塞直到队列非满。
* `int dequeue()` 返回队尾元素并从队列中将其删除. 如果队列为空，调用线程被阻塞直到队列非空。
* `int size()` 返回当前队列元素个数。

你的实现将会被多线程同时访问进行测试。每一个线程要么是一个只调用 `enqueue` 方法的生产者线程，要么是一个只调用 `dequeue` 方法的消费者线程。 `size` 方法将会在每一个测试用例之后进行调用。

请不要使用内置的有限阻塞队列实现，否则面试将不会通过。

**示例 1**

```
输入:
1
1
["BoundedBlockingQueue","enqueue","dequeue","dequeue","enqueue","enqueue","enqueue","enqueue","dequeue"]
[[2],[1],[],[],[0],[2],[3],[4],[]]

输出:
[1,0,2,2]

解释:
生产者线程数目 = 1
消费者线程数目 = 1

BoundedBlockingQueue queue = new BoundedBlockingQueue(2);   // 使用capacity = 2初始化队列。

queue.enqueue(1);   // 生产者线程将1插入队列。
queue.dequeue();    // 消费者线程调用dequeue并返回1。
queue.dequeue();    // 由于队列为空，消费者线程被阻塞。
queue.enqueue(0);   // 生产者线程将0插入队列。消费者线程被解除阻塞同时将0弹出队列并返回。
queue.enqueue(2);   // 生产者线程将2插入队列。
queue.enqueue(3);   // 生产者线程将3插入队列。
queue.enqueue(4);   // 生产者线程由于队列长度已达到上限2而被阻塞。
queue.dequeue();    // 消费者线程将2从队列弹出并返回。生产者线程解除阻塞同时将4插入队列。
queue.size();       // 队列中还有2个元素。size()方法在每组测试用例最后调用。
```

**示例 2**

```
输入:
3
4
["BoundedBlockingQueue","enqueue","enqueue","enqueue","dequeue","dequeue","dequeue","enqueue"]
[[3],[1],[0],[2],[],[],[],[3]]

输出:
[1,0,2,1]

解释:
生产者线程数目 = 3
消费者线程数目 = 4

BoundedBlockingQueue queue = new BoundedBlockingQueue(3);   // 使用capacity = 3初始化队列。

queue.enqueue(1);   // 生产者线程P1将1插入队列。
queue.enqueue(0);   // 生产者线程P2将0插入队列。
queue.enqueue(2);   // 生产者线程P3将2插入队列。
queue.dequeue();    // 消费者线程C1调用dequeue。
queue.dequeue();    // 消费者线程C2调用dequeue。
queue.dequeue();    // 消费者线程C3调用dequeue。
queue.enqueue(3);   // 其中一个生产者线程将3插入队列。
queue.size();       // 队列中还有1个元素。

由于生产者/消费者线程的数目可能大于1，我们并不知道线程如何被操作系统调度，即使输入看上去隐含了顺序。因此任意一种输出[1,0,2]或[1,2,0]或[0,1,2]或[0,2,1]或[2,0,1]或[2,1,0]都可被接受。
```

## 思路：

这是一道典型的生产者和消费者问题，数据结构可以使用 **数组** 或者 **链表**，我这里使用的**链表**，简化数组拷贝的操作。

* 如果共享数据区域已经满了，则当 `queue.size() >= capacity` 时，阻塞生产者继续生产
* 如果共享区域为空，则当 `queue.size() <= 0` 时，阻塞消费者继续消费

**这道题有多种实现方式：**

* 使用 Object 的 wait/notify/notifyAll 的消息通知机制
* 使用 Lock 的 Condition 的 await/signal/signalAll 的消息通知机制

<!-- tabs:start -->


### **await/signal/signalAll**

```
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
}
```


### **wait/notify/notifyAll**

```
class BoundedBlockingQueueSync {
    private int capacity;
    private final Object LOCK = new Object();
    private final List<Integer> queue = new ArrayList<>();

    public BoundedBlockingQueueSync(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (LOCK) {
            while (queue.size() >= capacity) {
                LOCK.wait();
            }
            queue.add(0, element);
            LOCK.notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        int element = 0;
        synchronized (LOCK) {
            while (queue.size() <= 0) {
                LOCK.wait();
            }
            element = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
            LOCK.notifyAll();
        }
        return element;
    }

    public int size() {
        return queue.size();
    }
}
```

<!-- tabs:end -->


# 0xF01 LeetCode 队列：设计循环队列

题目来源于 `LeetCode` 上 第 `622` 号问题：岛屿数量。题目难度为 `Medium`。

* [英文地址：https://leetcode.com/problems/design-circular-queue/](https://leetcode.com/problems/design-circular-queue/) 
* [中文地址：https://leetcode-cn.com/problems/design-circular-queue/](https://leetcode-cn.com/problems/design-circular-queue/) 

## 题目描述

设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。

循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。

你的实现应该支持如下操作：

* MyCircularQueue(k): 构造器，设置队列长度为 k 。
* Front: 从队首获取元素。如果队列为空，返回 -1 。
* Rear: 获取队尾元素。如果队列为空，返回 -1 。
* enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
* deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
* isEmpty(): 检查循环队列是否为空。
* isFull(): 检查循环队列是否已满。

**示例：**

```
MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
circularQueue.enQueue(1);  // 返回 true
circularQueue.enQueue(2);  // 返回 true
circularQueue.enQueue(3);  // 返回 true
circularQueue.enQueue(4);  // 返回 false，队列已满
circularQueue.Rear();  // 返回 3
circularQueue.isFull();  // 返回 true
circularQueue.deQueue();  // 返回 true
circularQueue.enQueue(4);  // 返回 true
circularQueue.Rear();  // 返回 4
```

**提示：**

* 所有的值都在 0 至 1000 的范围内
* 操作数将在 1 至 1000 的范围内
* 请不要使用内置的队列库

## 思路：

**参数的含义：**

* `data` ：表示固定的数组，用作循环队列
* `head` ：表示的队列的头指针
* `tail` ： 表示队列的尾指针

**判断队列是否已满**

```
(tail + 1) % size == head
```

**判断队列是否为空**

```
head == -1
```

或者

```
head == tail
```

具体实现可以看 Java 和 Kotlin，它们的实现方式各不相同


**复杂度分析：**

* 时间复杂度：O(1) ，数组存储都是按顺序存放的，具有随机访问的特点 
* 空间复杂度：O(N)，N 为数组的长度

### Kotlin 实现

```
class MyCircularQueue(k: Int) {

    var head = -1;
    var tail = -1;
    var data = IntArray(k);
    val size = k;

    fun enQueue(value: Int): Boolean {
        if (isFull()) return false
        if (isEmpty()) head = 0
        tail = (tail + 1) % size
        data[tail] = value
        return true
    }

    fun deQueue(): Boolean {
        if (isEmpty()) return false
        if (head == tail) {
            head = -1
            tail = -1
            return true
        }
        head = (head + 1) % size
        return true
    }

    fun Front(): Int {
        if (isEmpty()) return -1
        return data[head]
    }

    fun Rear(): Int {
        if (isEmpty()) return -1
        return data[tail]
    }

    fun isEmpty(): Boolean {
        return head == -1
    }

    fun isFull(): Boolean {
        return (tail + 1) % size == head
    }

}
```

### Java 实现

思路和 Kotlin 的实现不同，为了避免冲突循环数组中任何时刻一定至少有一个位置不存放有效元素，不过这种方法实现，更方便进行扩容，参考 `ArrayDeque` 源码实现

```
class MyCircularQueue {
    int[] data;
    int head;
    int tail;
    int size;

    public MyCircularQueue(int k) {
        // k + 1 有两个原因
        // 1. 为了避免冲突循环数组中任何时刻一定至少有一个位置不存放有效元素
        // 2. 当  k = 4 ，下标从 0 开始的，假设不移动 head， k 也不 +1 ，第四个元素始终放不进去
        size = k + 1;
        data = new int[size];
        head = 0;
        tail = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        data[tail] = value;
        tail = (tail + 1) % size;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = (head + 1) % size;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return data[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        // 因为数组中任何时刻一定至少有一个位置不存放有效元素，所以 tail - 1 取最近存放的元素
        // 假设 tail = 0 时，tail - 1 就会变成负数，下标会越界，所以 tail - 1 + size) % size
        return data[(tail - 1 + size) % size];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % size == head;
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack 源码相关的文章，正在努力写出更好的文章，如果这篇文章对你有帮助给个 star，文章中有什么没有写明白的地方，或者有什么更好的建议欢迎留言，欢迎一起来学习，在技术的道路上一起前进。

### Android10 源码分析

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### 算法题库的归纳和总结

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### 精选国外的技术文章

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。


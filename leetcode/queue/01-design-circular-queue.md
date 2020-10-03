题目来源于 `LeetCode` 上 第 `622` 号问题：设计循环队列。题目难度为 `Medium`。

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



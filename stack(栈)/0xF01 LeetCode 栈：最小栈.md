# 0xF01 LeetCode 栈：最小栈

题目来源于 `LeetCode` 上 第 `155` 号问题：最小栈。题目难度为 `Easy`。

* [英文地址：https://leetcode.com/problems/min-stack](https://leetcode.com/problems/min-stack) 
* [中文地址：https://leetcode-cn.com/problems/min-stack](https://leetcode-cn.com/problems/min-stack/) 

## 题目描述

设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

* push(x) —— 将元素 x 推入栈中。
* pop() —— 删除栈顶的元素。
* top() —— 获取栈顶元素。
* getMin() —— 检索栈中的最小元素。

**示例：**

```
输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

输出：
[null,null,null,null,-3,null,0,-2]

解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```

**提示：**

* pop、top 和 getMin 操作总是在 非空栈 上调用

## 思路：

**算法流程如下：**

用一个辅助栈 minStack，每次 push 的时候，比较当前元素和栈顶元素，将最小值存入 minStack。

**复杂度分析：**

* 时间复杂度：O(1) ，因为栈的插入、删除与读取操作都是 O(1)
* 空间复杂度：O(N)，N 为总操作次数

### Kotlin 实现

```
class MinStack() {

    /** initialize your data structure here. */
    val stack = ArrayDeque<Int>()
    val minStack = ArrayDeque<Int>()

    fun push(x: Int) {
        stack.push(x)
        if (minStack.peek() == null) {
            minStack.push(x)
        } else {
            minStack.push(Math.min(x, minStack.peek()))
        }
    }

    fun pop() {
        stack.poll()
        minStack.poll()
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }

}
```

### Java 实现

```
class MinStack {
    ArrayDeque<Integer> stack;
    ArrayDeque<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new ArrayDeque<Integer>();
        minStack = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.peek() == null) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    public void pop() {
        stack.poll();
        minStack.poll();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
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


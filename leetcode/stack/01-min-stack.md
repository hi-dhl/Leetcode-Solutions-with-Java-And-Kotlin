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

<!-- tabs:start -->

### **Kotlin 实现**

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

### **Java 实现**

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

<!-- tabs:end -->


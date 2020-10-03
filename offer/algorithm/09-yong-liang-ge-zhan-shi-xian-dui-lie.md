题目来源于 LeetCode 上第 09 号问题：用两个栈实现队列。题目难度为 Easy。和 主站 [509](https://leetcode-cn.com/problems/fibonacci-number/) 号问题相同。

* [中文地址：https://leetcode-cn.com/problems/yong......](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)。

## 题目描述

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )。

**示例 1:**

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

**示例 2:**

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

## 思路：

**先来了解一下栈和队列的基本概念**

**栈 (Stack)：**是一种后进先出(last in first off，LIFO)的数据结构，如下图所示：

![栈](http://cdn.51git.cn/2020-06-14-栈.gif)



**队列：**是一种先进先出的结构。，一端用来加入新元素，另一端用来删除元素。

![队列](http://cdn.51git.cn/2020-06-14-队列.gif)


**算法步骤：**

两个栈分别为 Stack 1 和 Stack 2，Stack 1 用于存储元素，Stack 2 作为辅助栈。

插入元素：将元素存储在 Stack 1 中。
删除元素：如果 Stack 1 和 Stack 2 都为空返回 -1， 不为空，将 Stack 1 中的元素 pop 出来放到 Stack 2 中之后返回 Stack 2 栈顶元素，即可实现队列功能，假设： 一组输入数据 [1，2，3，4],如果放到队里中，队列是一种先进先出的结构，即输出结果为：[1，2，3，4], 我看一下栈是如何实现的：

 * Stack 1 元素 [1，2，3，4] pop 出来放到 Stack 2 中，Stack 2 中的元素为 [4，3，2，1]。
 * 栈是后进先出的结构，然后依次弹出 Stack 2 中的元素，即输出结果为：[1， 2，3，4]。

**复杂度分析：**

* 时间复杂度：插入操作 `appendTail()` 时间复杂度 O(1)，删除操作 `deleteHead()` 时间复杂度 O(N)，
* 空间复杂度：O(N)，需要使用两个栈存储已有的元素

### Koltin 实现

```
class CQueue() {
    val stack1 = Stack<Int>()
    val stack2 = Stack<Int>()

    fun appendTail(value: Int) {
        stack1.push(value);
    }

    fun deleteHead(): Int {
        return when {
            !stack2.isEmpty() -> stack2.pop()
            stack1.isEmpty() -> -1
            else -> {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop())
                }
                return stack2.pop()
            }
        }
    }
}
```

### Java 实现

```
class CQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else if (stack1.isEmpty()) {
            return -1;
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }
}
```



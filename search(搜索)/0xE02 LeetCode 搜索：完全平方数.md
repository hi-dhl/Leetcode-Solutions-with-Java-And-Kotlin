# 0xE02 LeetCode 搜索：完全平方数

题目来源于 `LeetCode` 上 第 `279` 号问题：岛屿数量。题目难度为 `Medium`。

* [英文地址：https://leetcode.com/problems/perfect-squares/](https://leetcode.com/problems/perfect-squares/) 
* [中文地址：https://leetcode-cn.com/problems/perfect-squares/](https://leetcode-cn.com/problems/perfect-squares/) 

## 题目描述

给定正整数 n，找到若干个完全平方数（比如 `1, 4, 9, 16, ...`）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

**示例 1：**

```
输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
```

**示例 2：**

```
输入: n = 13
输出: 2
解释: 13 = 4 + 9.
```

## 思路：广度优先遍历

广度优先搜索（BFS）的一个常见应用是找出从根结点到目标结点的最短路径。

**在 BFS 中使用队列的原因？**

结点的处理顺序与它们添加到队列的顺序是完全相同的顺序，即先进先出（FIFO）。这就是我们在 BFS 中使用队列的原因。

**队列的基本使用**

每种 Queue 方法存在两种形式：

* 如果操作失败，抛出了一个异常
* 其他的返回一个特殊值（null 或者 false，取决于操作）

| 操作类型 | 抛出异常 | 返回特殊值 |
| :-: | :-: | :-: |
| 插入 | add(e) | offer(e) |
| 移除 | remove() | poll() |
| 查找 | element() | peek() |

Queue 的实现可以限制所持有元素的数量，这样的队列称为有界，有些 Queue 实现 java.util.concurrent 是有界的，但实现 java.util 不是：

* add ：继承自 Collection，插入一个元素，除非它会违反队列的容量限制，在这种情况下抛出 IllegalStateException
* offer ：方法仅用于有界队列，不能插入时，返回 fasle

在 remove 与 poll 方法都是从队列中删除第一个元素，如果队列为空：

* remove : removethrows NoSuchElementException
* poll : 返回 null

element 与 peek 方法用于在队列的头部查询元素，如果队列为空：

* element : 抛出 NoSuchElementException
* peek : 返回 null


**算法流程如下：**

将数字 N 转换为多树，按树的层次遍历即可（分解出 N 可能的组合），每一层的节点 = 用上一层的节点 - 完全平方数

1. 新建一个 queue，用来存储访问的每个节点
2. 用 HashSet 过滤掉计算过的组合，避免重复计算
3. 循环判断队列是否为空，如果队列为空即循环终止
4. 获取每层的长度，遍历每一层，计算出下一层的组合，添加到 queue
5. 重复执行 3、4
6. 遍历结束时，层数即是能组成完全平方数的最少个数

### Kotlin 实现

```
class Solution {
    fun numSquares(n: Int): Int {
        val square = mutableListOf<Int>()
        for (i in 1..n) {
            square.add(i * i)
        }

        val set = hashSetOf<Int>()
        val queue = LinkedList<Int>()
        queue.offer(n)
        var step = 0
        while (queue.size > 0) {
            step = step + 1
            val count = queue.size
            for (i in 0 until count) {
                val item = queue.poll()
                loop@ for (j in square) {
                    when {
                        item == j -> return step
                        item < j -> break@loop
                        else -> {
                            val sub = item - j;
                            if (set.add(sub)) {
                                queue.offer(sub)
                            }
                        }
                    }
                }
            }
        }
        return step
    }
}
```

### Java 实现

```
class Solution {
    public int numSquares(int n) {
        List<Integer> square = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            square.add(i * i);
        }

        int step = 0;
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        while (queue.size() > 0) {
            step = step + 1;
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int item = queue.poll();
                for (Integer subItem : square) {
                    if (item == subItem) {
                        return step;
                    } else if (item < subItem) {
                        break;
                    } else {
                        int sub = item - subItem;
                        if (set.add(sub)) {
                            queue.offer(sub);
                        }
                    }
                }
            }
        }
        return step;
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解 [Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin) 和  Android 10 源码分析 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis) 一起来学习，期待与你一起成长


### Android10-Source-Analysis

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### Leetcode-Solutions-with-Java-And-Kotlin

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### Technical-Article-Translation

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。



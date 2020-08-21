# 0xG02 LeetCode 链表：环形链表

题目来源于 `LeetCode` 上 第 `141` 号问题：环形链表。题目难度为 `Easy`。

* [英文地址：https://leetcode.com/problems/linked-list-cycle/](https://leetcode.com/problems/linked-list-cycle/) 
* [中文地址：https://leetcode-cn.com/problems/linked-list-cycle/](https://leetcode-cn.com/problems/linked-list-cycle/) 

## 题目描述

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

**示例 1：**

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

![](http://cdn.51git.cn/2020-08-21-15980119816735.jpg)


**示例 2：**

```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

![](http://cdn.51git.cn/2020-08-21-15980120148210.jpg)


**示例 3：**

```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```

## 思路：快慢指针

**题目分析：**

判断链表中是否有环，也就是说如果链表尾节点指向链表的其他任意节点，那么链表就存在一个环。

**参数的含义：**

* `p` ：表示慢指针
* `q` ：表示快指针

**算法步骤：**

1. 慢指针 `p` 和 快指针 `q` 两个指针初始化均指向头结点
2. 循环遍历慢指针 `p` 和 快指针 `q` ，每次让慢指针 `p` 移动一步，快指针 `q` 移动两步，当一个链表有环时，两个指针会陷入无限循环中，然后变成了追及问题，只要一直移动下去，快指针 `q` 总会追上慢指针 `p` 。
3. 如果 `p == q` 即结束循环

效果如下所示，图片来自 LeetCode 作者 Time-Limit

![7af92ee2d483](http://cdn.51git.cn/2020-08-21-d1ac82780e5189d7d58406504c3b7b56c35165997bfbb4c325677af92ee2d483.gif)



**复杂度分析：**

* 时间复杂度 `O(N)`：N 为链表节点数量
* 空间复杂度 `O(1)`：占用常数大小的空间

### Kotlin 实现

```
class Solution {
    fun hasCycle(head: ListNode?): Boolean {
        return head?.let {
            if (head.next == null) return false
            var p: ListNode? = head
            var q: ListNode? = head
            do {
                if (q == null || q?.next == null) return false
                p = p?.next;
                q = q?.next.next;
            } while (p != q)
            true
        } ?: false
    }
}
```

### Java 实现

```
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode p = head;
        ListNode q = head;
        do {
            if (q == null || q.next == null) return false;
            p = p.next;
            q = q.next.next;
        } while (p != q);
        return true;
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack 源码相关的文章。

### Android10 源码分析

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### 算法题库的归纳和总结

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### 精选国外的技术文章

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。


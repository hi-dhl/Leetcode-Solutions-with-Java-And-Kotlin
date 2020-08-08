# 0xF18 剑指 offer： 删除链表的节点

题目来源于 `LeetCode` 剑指 `offer` 第 `18` 号问题： 删除链表的节点。题目难度为 `Easy`。

* [中文地址：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

## 题目描述

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

**示例 1：**

```
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

**示例 2：**

```
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

**说明：**

* 题目保证链表中节点的值互不相同
* 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点

## 思路：

**如何删除节点：**

假设 current 指向 head 节点，tmpNode 为要删除的节点，算法如下

```
val tmpNode = current.next;
current.next = tmpNode.next
```


**算法流程如下：**

* current 指向 head 节点
* 如果 `head == null` 即返回 head 节点
* 如果头节点是要删除的节点，即 `head.val == val` 返回 `head.next`, 删除头节点
* 循环遍历下一个节点是否为空 `while (current?.next != null) { ... }`
* 判断下一个节点是否是要删除的节点，如果是则调用删除节点算法

```
val tmpNode = current.next;
current.next = tmpNode.next
```
* 循环遍历结束，返回 head 节点

**复杂度分析：**

* 时间复杂度：O(N) ，N 为链表长度，假设要删除的节点在末尾，即时间复杂度为 O(N)
* 空间复杂度：O(1)，current 占用常数大小的空间，可以不用计算，即空间复杂度为 O(1)

### Kotlin 实现

```
class Solution {
    fun deleteNode(head: ListNode?, `val`: Int): ListNode? {
        if (head == null) return head
        if (head.`val` == `val`) return head.next

        var current = head
        while (current?.next != null) {
            val tmpNode = current.next;
            if (tmpNode.`val` == `val`) {
                current.next = tmpNode.next
                break;
            }
            current = tmpNode
        }
        return head
    }
}
```

### Java 实现

```
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        if (head.val == val) return head.next;

        ListNode current = head;
        while (current.next != null) {
            ListNode tmp = current.next;
            if (tmp.val == val) {
                current.next = tmp.next;
                break;
            }
            current = tmp;
        }
        return head;
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



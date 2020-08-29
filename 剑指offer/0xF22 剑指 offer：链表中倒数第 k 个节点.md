# 0xF22 剑指 offer：链表中倒数第 k 个节点

题目来源于 `LeetCode` 剑指 `offer` 第 `22` 号问题： 链表中倒数第 k 个节点。题目难度为 `Easy`。

* [中文地址：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof)

## 题目描述

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

**示例：**

```
给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```

## 思路：快慢指针

**参数说明：**

* slow：慢指针
* fast: 快指针

**算法流程如下：**

* 初始化快慢指针，指向链表的头结点
* 让快指针 fast 沿着链表移动 k 次，此时 fast 指向第 k 个节点
* 循环遍历链表，当 `fast.next == null`  结束循环
    * 移动慢指针 `slow = slow.next` 
    * 移动快指针 `fast = fast.next`
* 当循环结束时，即慢指针 slow 指向的是倒数第 k 个节点，返回 slow

**复杂度分析：**

* 时间复杂度：O(N) ，N 为链表的长度
* 空间复杂度：O(1)，快指针 fast 和 慢指针 slow 占用常数大小的空间

### Kotlin 实现

```
class Solution {
    fun getKthFromEnd(head: ListNode?, k: Int): ListNode? {
        if (head == null || k < 0) return head
        var fast = head;

        (1 until k).forEach {
            fast = fast?.next
        }

        var slow = head;
        while (fast?.next != null) {
            fast = fast?.next;
            slow = slow?.next;
        }

        return slow;
    }
}
```

### Java 实现

```
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k < 0) return head;

        ListNode fast = head;
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }

        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
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



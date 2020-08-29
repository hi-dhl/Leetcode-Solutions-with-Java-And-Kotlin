# 0xF24 剑指 offer：反转链表

题目来源于 `LeetCode` 剑指 `offer` 第 `24` 号问题：反转链表。题目难度为 `Easy`。与主站 [206 号问题](https://leetcode-cn.com/problems/reverse-linked-list) 相同

* [中文地址：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof)

## 题目描述

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

**示例：**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

**限制：**

```
0 <= 节点个数 <= 5000
```

## 思路：递归和非递归

**算法流程如下：**

* 初始化指针 pre 为空指针， `ListNode pre = null`
* 循环移动链表，当 `head == null` 结束循环
    * 每次移动让 `head.next = pre` 实现局部反转
    * 将 head 和 pre 移到下一个节点
* 当循环结束时，即 pre 指向原链表尾部

**复杂度分析：**

* 时间复杂度：O(N) ，N 为链表的长度
* 空间复杂度：O(1) ，指针 pre 占用常数大小的空间

### Java 实现

```
class Solution {

    /**
     * 非递归
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 递归
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
```

### Kotlin 实现

```
class Solution {

    /**
     * 非递归
     */
    fun reverseList(head: ListNode?): ListNode? {
        var pre: ListNode? = null
        var current = head
        while (current != null) {
            val next = current.next
            current.next = pre;
            pre = current;
            current = next
        }
        return pre;
    }

    /**
     * 递归
     */
    fun reverseList2(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head;
        val cur = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
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



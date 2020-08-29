# 0xF25 剑指 offer：合并两个排序的链表

题目来源于 `LeetCode` 剑指 `offer` 第 `25` 号问题：合并两个排序的链表。题目难度为 `Easy`。与主站 [21 号问题](https://leetcode-cn.com/problems/merge-two-sorted-lists) 相同

* [中文地址：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof)

## 题目描述

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

**示例 1：**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

**限制：**

```
0 <= 链表长度 <= 1000
```

## 思路：

**算法流程如下：**

* 初始化伪头节点 cur 和 head，并将 head 指向 cur
* 循环移动两个链表，当其中一个链表为空时结束循环
    * 当 `l1.val < l2.val` 时，`cur.next = l1`，并将 l1 和 cur 移到下一个节点，即 `l1 = l1.next; cur = cur.next`
    * 当 `l1.val > l2.val` 时，`cur.next = l2`，并将 l2 和 cur 移到下一个节点，即 `l2 = l1.next; cur = cur.next`
* 当循环结束时，合并剩余的节点
    * 如果 `l1 != null`, 即 `cur.next = l1`
    * 如果 `l2 != null`, 即 `cur.next = l2`
* 因为最开始建立一个伪头节点，所以最后应该返回伪头节点的下一个节点，即 `head.next`

**复杂度分析：**

* 时间复杂度：O(N) ，N 为链表的长度
* 空间复杂度：O(1) ，伪头节点 cur 和 head 占用常数大小的空间

### Java 实现

```
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }
}
```

### Kotlin 实现

```
class Solution {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var head = ListNode(0)
        var cur = head
        var pl1 = l1;
        var pl2 = l2;
        while (pl1 != null && pl2 != null) {
            if (pl1.`val` < pl2.`val`) {
                cur.next = pl1
                pl1 = pl1.next
            } else {
                cur.next = pl2
                pl2 = pl2.next

            }
            cur = cur.next
        }

        cur.next = if (pl1 == null) pl2 else pl1
        return head.next
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



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



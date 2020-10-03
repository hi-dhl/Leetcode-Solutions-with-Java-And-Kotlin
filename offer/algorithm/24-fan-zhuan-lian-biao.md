题目来源于 `LeetCode` 剑指 `offer` 第 `24` 号问题：反转链表。题目难度为 `Easy`。与主站 [206 号问题](https://leetcode-cn.com/problems/reverse-linked-list) 相同。

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



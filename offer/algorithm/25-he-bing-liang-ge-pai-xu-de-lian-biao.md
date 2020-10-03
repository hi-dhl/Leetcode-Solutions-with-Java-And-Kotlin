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



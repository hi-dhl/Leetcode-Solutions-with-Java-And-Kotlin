题目来源于 LeetCode 剑指 offer 第 06 号问题：从尾到头打印链表。题目难度为 Easy。

* [中文地址：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

## 题目描述

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

**示例:**

```
输入：head = [1,3,2]
输出：[2,3,1]
```

**限制：**

```
0 <= 链表长度 <= 10000
```

## 思路

* 第一次遍历计算链表的长度，创建相同长度的数组 reverse
* 第二次遍历将值 逆序 添加进 reverse 中

**复杂度分析：**

* 时间复杂度：O(n)，第一次遍历计算链表的长度，第二次遍历添加进数组
* 空间复杂度：O(n)，需要额外使用一个数组存储

<!-- tabs:start -->

### **Kotlin 实现**

```
class Solution {

    fun reversePrint(head: ListNode?): IntArray = head?.let {
        var count = 0
        var currentNode = head
        while (currentNode != null) {
            count++
            currentNode = currentNode.next
        }

        val reverse = IntArray(count) { 0 }
        currentNode = head
        while (currentNode != null) {
            reverse[--count] = currentNode.`val`
            currentNode = currentNode.next
        }
        reverse
    } ?: intArrayOf()
}
```

### **Java 实现**

```
class Solution {

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        int count = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }
        int[] reverse = new int[count];
        currentNode = head;
        while (currentNode != null) {
            reverse[--count] = currentNode.val;
            currentNode = currentNode.next;
        }
        return reverse;
    }
}
```


<!-- tabs:end -->


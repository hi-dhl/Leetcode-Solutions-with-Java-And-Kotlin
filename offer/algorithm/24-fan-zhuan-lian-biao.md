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

## 思路：原地逆置链表

**算法流程如下：**

* 初始化指针 pre 为空指针， `ListNode pre = null`
* 循环移动链表，当 `head == null` 结束循环
    * 每次移动让 `head.next = pre` 实现局部反转
    * 将 head 和 pre 移到下一个节点
* 当循环结束时，即 pre 指向原链表尾部

**复杂度分析：**

* 时间复杂度：O(N) ，N 为链表的长度
* 空间复杂度：O(1) ，指针 pre 占用常数大小的空间

<!-- tabs:start -->

### **Java 实现**

```
class Solution {

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

}
```

### **Kotlin 实现**

```
class Solution {

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

}
```


<!-- tabs:end -->

### 思路二： 递归

1. 寻找递归结束条件：当链表只有一个节点，或者如果是空表的话，结束递归
    
    ```
    head == null || head.next  == null
    ``` 
    
2. 调用函数本身，传入下一个节点
    
    ```
    reverseList(head.next);
    ```
    
    我们把 2->3->4->5 递归成了 5->4->3->2, 对于1节点没有去更改，所以1的next节点指向的是2，如下所示
    
    ```
    递归前:
    1 -> 2 -> 3 -> 4 -> 5

    递归后:
    1 -> 2 <- 3 <- 4 <- 5
         |
         v
        null
    ```

3. 最后将节点 2 的 next 指向 1，然后把 1 的 next 指向 null，如下所示

    ```
    null <- 1 <- 2 <- 3 <- 4 <- 5
    ```


<!-- tabs:start -->

### **Java 实现**

```
class Solution {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
```

### **Kotlin 实现**

```
class Solution {

    fun reverseList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head;
        val cur = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
```

<!-- tabs:end -->

### 思路三：头插入法逆值链表

* 新建一个新链表, 新链表的头结点 newHead
* 循环判断head是否为空，为空时结束循环
* 如果不为空，依次取原链表中的每一个节点，作为第一个节点插入到新链表中

```
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode(0);
        while (head!=null){
            ListNode tempNode = new ListNode(head.val);
            tempNode.next = newHead.next;
            newHead.next = tempNode;
            head = head.next;
        }
        return newHead.next;
    }
}
```


package com.hi.dhl.algorithms._206.java;

/**
 * <pre>
 *     author: dhl
 *     desc  : 206. Reverse Linked List
 *
 *     题目描述:
 *
 *     Reverse a singly linked list.
 *     反转一个单链表
 *
 *      Example:
 *        Input: 1->2->3->4->5->NULL
 *        Output: 5->4->3->2->1->NULL
 *
 * </pre>
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 递归:
     * <p>
     * 1. 寻找递归结束条件：当链表只有一个节点，或者如果是空表的话，结束递归
     * <p>
     * head == null || head.next  == null
     * 2. 调用函数本身，传入下一个节点
     * <p>
     * reverseList(head.next);
     * 我们把 2->3->4->5 递归成了 5->4->3->2, 对于1节点没有去更改，所以1的next节点指向的是2，如下所示
     * <p>
     * 递归前:
     * 1 -> 2 -> 3 -> 4 -> 5
     * <p>
     * 递归后:
     * 1 -> 2 <- 3 <- 4 <- 5
     * |
     * v
     * null
     * <p>
     * 3.最后将节点 2 的 next 指向 1，然后把 1 的 next 指向 null，如下所示
     * <p>
     * null <- 1 <- 2 <- 3 <- 4 <- 5
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode nextNode = reverseList1(head.next);

        ListNode tempNode = head.next;
        tempNode.next = head;
        head.next = null;

        return nextNode;
    }

    /**
     * 原地逆置链表
     * <p>
     * 设置三个节点 preNode, head, next
     * <p>
     * 1. 判断head 以及 head.next 是否为空，如果为空，结束循环
     * 2. 如果不为空，设置临时变量next为head的下一个节点
     * 3. head的下一个节点指向preNode，然后preNode移动到head, head移动到next
     * 4. 重复（1）（2）（3）
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode preNode = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = preNode;
            preNode = head;
            head = next;
        }
        return preNode;
    }

    /**
     * 头插入法逆值链表
     * <p>
     * 新建一个新链表, 新链表的头结点 newHead
     * 循环判断head是否为空，为空时结束循环
     * 如果不为空，依次取原链表中的每一个节点，作为第一个节点插入到新链表中
     *
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        ListNode newHead = new ListNode(0);
        while (head != null) {
            ListNode tempNode = new ListNode(head.val);
            tempNode.next = newHead.next;
            newHead.next = tempNode;
            head = head.next;
        }
        return newHead.next;
    }
}

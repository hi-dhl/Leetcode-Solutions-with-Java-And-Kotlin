package com.hi.dhl.algorithms.leetcode._707.java;

import com.hi.dhl.algorithms.model.ListNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/13
 *     desc  : 单链表
 * </pre>
 */
public class MyLinkedList1 {

    ListNode head;
    int size;

    /**
     * 初始化
     */
    public MyLinkedList1() {
        size = 0;
        head = new ListNode(0);
    }

    /**
     * 获取指定位置的元素，找不到返回 -1
     */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        ListNode cur = head;
        // 因为将伪头作为头结点，所以需要 +1 才是当前查找元素所在的结点
        for (int i = 0; i < index + 1; i++) cur = cur.next;
        return cur.val;
    }

    /**
     * 在头部添加元素
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * 在尾部添加元素
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * 在指定位置插入元素
     */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;

        ListNode pre = head;
        // 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        for (int i = 0; i < index; i++) pre = pre.next;

        ListNode newNode = new ListNode(val);
        // 插入元素到前驱节点尾部
        newNode.next = pre.next;
        pre.next = newNode;
        size++;
    }

    /**
     * 删除指定位置的元素
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        ListNode pre = head;
        // 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        for (int i = 0; i < index; i++) pre = pre.next;

        pre.next = pre.next.next;
        size--;
    }
}

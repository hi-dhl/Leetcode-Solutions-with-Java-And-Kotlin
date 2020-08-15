package com.hi.dhl.algorithms._707.java;

import com.hi.dhl.algorithms.model.LinkedNode;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/13
 *     desc  : 双向链表
 * </pre>
 */
class MyLinkedList2 {

    LinkedNode head;
    LinkedNode last;
    int size = 0;

    /**
     * 初始化
     */
    public MyLinkedList2() {
        size = 0;
        head = new LinkedNode(0);
        last = new LinkedNode(0);
        head.next = last;
        last.pre = head;
    }

    /**
     * 获取指定位置的元素，找不到返回 -1
     */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        LinkedNode cur;
        if (index + 1 < size - index) {
            cur = head;
            // 因为将伪头作为头结点，所以需要 +1 才是当前查找元素所在的结点
            for (int i = 0; i < index + 1; i++) cur = cur.next;
        } else {
            cur = last;
            for (int i = 0; i < size - index; i++) cur = cur.pre;
        }

        return cur.val;
    }

    /**
     * 在头部添加元素
     */
    public void addAtHead(int val) {
        LinkedNode pre = head;
        LinkedNode succ = head.next;

        LinkedNode node = new LinkedNode(val);
        node.next = succ;
        node.pre = pre;
        pre.next = node;
        succ.pre = node;
        size++;
    }

    /**
     * 在尾部添加元素
     */
    public void addAtTail(int val) {
        LinkedNode succ = last;
        LinkedNode pre = last.pre;

        LinkedNode node = new LinkedNode(val);
        node.pre = pre;
        node.next = succ;
        succ.pre = node;
        pre.next = node;
        size++;
    }

    /**
     * 在指定位置插入元素
     */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;

        LinkedNode pre;
        LinkedNode succ;
        // 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        if (index < size - index) {
            pre = head;
            for (int i = 0; i < index; i++) pre = pre.next;
            succ = pre.next;
        } else {
            succ = last;
            for (int i = 0; i < size - index; i++) succ = succ.pre;
            pre = succ.pre;
        }

        LinkedNode newNode = new LinkedNode(val);
        newNode.next = succ;
        newNode.pre = pre;
        succ.pre = newNode;
        pre.next = newNode;
        size++;
    }

    /**
     * 删除指定位置的元素
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        LinkedNode pre;
        LinkedNode succ;
        // 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        if (index < size - index) {
            pre = head;
            for (int i = 0; i < index; i++) pre = pre.next;
            succ = pre.next.next;
        } else {
            succ = last;
            // 从尾部查找元素，需要 -1
            for (int i = 0; i < size - index - 1; i++) succ = succ.pre;
            pre = succ.pre.pre;
        }

        pre.next = succ;
        succ.pre = pre;
        size--;
    }
}

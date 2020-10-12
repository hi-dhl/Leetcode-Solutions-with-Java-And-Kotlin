package com.hi.dhl.algorithms.leetcode._707.kotlin

import com.hi.dhl.algorithms.model.ListNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/13
 *     desc  :
 * </pre>
 */

class MyLinkedList1() {

    /**
     * 初始化
     */

    var head: ListNode = ListNode(0)
    var size: Int = 0

    /**
     * 获取指定位置的元素，找不到返回 -1
     */
    fun get(index: Int): Int {
        val k = index
        if (k < 0 || k >= size) return -1
        // 因为将伪头作为头结点，所以需要 +1 才是当前查找元素所在的结点
        var cur: ListNode? = head
        for (i in 0 until (k + 1)) cur = cur?.next

        return cur?.`val` ?: -1
    }

    /**
     * 在头部添加元素
     */
    fun addAtHead(`val`: Int) {
        addAtIndex(0, `val`)
    }

    /**
     * 在尾部添加元素
     */
    fun addAtTail(`val`: Int) {
        addAtIndex(size, `val`)
    }

    /**
     * 在指定位置插入元素
     */
    fun addAtIndex(index: Int, `val`: Int) {
        var k = index
        if (k > size) return
        if (k < 0) k = 0

        // 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        var pre: ListNode? = head
        for (i in 0 until k) pre = pre?.next

        val node = ListNode(`val`)
        node.next = pre?.next
        pre?.next = node
        size++
    }

    /**
     * 删除指定位置的元素
     */
    fun deleteAtIndex(index: Int) {
        var k = index
        if (k < 0 || k >= size) return
        // 因为有伪头结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        var pre: ListNode? = head
        for (i in 0 until k) pre = pre?.next

        pre?.next = pre?.next?.next
        size--
    }

}
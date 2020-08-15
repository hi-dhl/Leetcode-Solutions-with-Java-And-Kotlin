package com.hi.dhl.algorithms._707.kotlin

import com.hi.dhl.algorithms.model.LinkedNode

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/14
 *     desc  :
 * </pre>
 */

class MyLinkedList2() {

    var head: LinkedNode = LinkedNode(0)
    var last: LinkedNode = LinkedNode(0)
    var size: Int = 0

    init {
        head.next = last
        last.pre = head
    }

    /**
     * 获取指定位置的元素，找不到返回 -1
     */
    fun get(index: Int): Int {
        val k = index
        if (k < 0 || k >= size) return -1

        var cur: LinkedNode? = head
        if ((k + 1) < (size - k)) {
            // 因为将伪头作为头结点，所以需要 +1 才是当前查找元素所在的结点
            for (i in 0 until k + 1) cur = cur?.next
        } else {
            cur = last
            for (i in 0 until size - k) cur = cur?.pre
        }

        return cur?.`val` ?: -1
    }

    /**
     * 在头部添加元素
     */
    fun addAtHead(`val`: Int) {
        val pre: LinkedNode? = head
        val succ: LinkedNode? = head.next

        val node: LinkedNode = LinkedNode(`val`)
        node.next = succ
        node.pre = pre
        succ?.pre = node
        pre?.next = node
        size++

    }

    /**
     * 在尾部添加元素
     */
    fun addAtTail(`val`: Int) {
        val pre = last.pre
        val succ = last
        val node = LinkedNode(`val`)
        node.next = succ
        node.pre = pre
        pre?.next = node
        succ?.pre = node
        size++
    }

    /**
     * 在指定位置插入元素
     */
    fun addAtIndex(index: Int, `val`: Int) {
        var k = index
        if (k > size) return
        if (k < 0) k = 0

        var pre: LinkedNode? = head
        var succ: LinkedNode? = last
        // 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        if (k < size - k) {
            for (i in 0 until k) pre = pre?.next
            succ = pre?.next
        } else {
            succ = last;
            for (i in 0 until size - k) succ = succ?.pre
            pre = succ?.pre
        }

        val node = LinkedNode(`val`)
        node.next = succ
        node.pre = pre
        pre?.next = node
        succ?.pre = node
        size++
    }

    /**
     * 删除指定位置的元素
     */
    fun deleteAtIndex(index: Int) {
        val k = index
        if (k < 0 || k >= size) return

        var pre: LinkedNode? = head
        var succ: LinkedNode? = last
        // 因为有伪头、伪尾结点存在，所以 i < index 找到的节点，一定是插入位置的前驱节点
        if (k < size - k) {
            for (i in 0 until k) pre = pre?.next
            succ = pre?.next?.next
        } else {
            succ = last;
            // 从尾部查找元素，需要 -1
            for (i in 0 until size - k - 1) succ = succ?.pre
            pre = succ?.pre?.pre
        }

        pre?.next = succ
        succ?.pre = pre
        size--
    }

}
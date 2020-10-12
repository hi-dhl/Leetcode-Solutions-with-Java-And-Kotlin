package com.hi.dhl.algorithms.leetcode._279.kotlin

import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/7
 *     desc  :
 * </pre>
 */

class Solution {
    fun numSquares(n: Int): Int {
        val square = arrayListOf<Int>()
        val sqrt = Math.sqrt(n.toDouble()).toInt()
        for (i in 1..sqrt) {
            square.add(i * i)
        }

        var step = 0
        val result = hashSetOf<Int>()
        val queue = LinkedList<Int>()
        queue.offer(n)
        while (!queue.isEmpty()) {
            step += 1
            val count = queue.size
            for (i in 0 until count) {
                val target = queue.poll();
                loop@ for (item in square) {
                    when {
                        target == item -> return step
                        item > target -> break@loop;
                        else -> {
                            val sub = target - item
                            if (result.add(sub)) queue.offer(sub)

                        }
                    }

                }
            }
        }
        return step
    }
}
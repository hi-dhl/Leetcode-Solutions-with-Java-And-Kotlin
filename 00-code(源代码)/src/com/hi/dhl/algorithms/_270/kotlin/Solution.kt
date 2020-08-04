package com.hi.dhl.algorithms._270.kotlin

import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/1
 *     desc  :
 * </pre>
 */

class Solution {
    fun numSquares(n: Int): Int {
        val square = mutableListOf<Int>()
        for (i in 1..n) {
            square.add(i * i)
        }

        val set = hashSetOf<Int>()
        val queue = LinkedList<Int>()
        queue.offer(n)
        var step = 0
        while (queue.size > 0) {
            step = step + 1
            val count = queue.size
            for (i in 0 until count) {
                val item = queue.poll()
                loop@ for (j in square) {
                    when {
                        item == j -> return step
                        item < j -> break@loop
                        else -> {
                            val sub = item - j;
                            if (set.add(sub)) {
                                queue.offer(sub)
                            }
                        }
                    }
                }
            }
        }
        return step
    }
}
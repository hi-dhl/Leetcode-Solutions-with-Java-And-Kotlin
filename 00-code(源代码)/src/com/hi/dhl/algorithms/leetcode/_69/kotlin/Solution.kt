package com.hi.dhl.algorithms.leetcode._69.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun mySqrt(x: Int): Int {
        var low = 0L
        var height = (x ushr 2).toLong() + 1
        val target = x.toLong()
        while (low <= height) {
            val mind = (low + height) ushr 1
            val square: Long = mind * mind
            when {
                square == target -> return mind.toInt()
                square < target -> low = mind + 1
                else -> height = mind - 1
            }
        }
        return height.toInt()
    }
}


fun main() {
    System.out.println(16 ushr 1)
}
package com.hi.dhl.algorithms.leetcode._367.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun isPerfectSquare(num: Int): Boolean {
        var low = 0L
        var height = (num ushr 1).toLong() + 1
        var target = num.toLong()
        while (low <= height) {
            val mind: Long = (low + height) ushr 1
            val square = mind * mind
            when {
                square == target -> return true
                square < target -> low = mind + 1
                else -> height = mind - 1
            }
        }
        return false
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.isPerfectSquare(1))
}
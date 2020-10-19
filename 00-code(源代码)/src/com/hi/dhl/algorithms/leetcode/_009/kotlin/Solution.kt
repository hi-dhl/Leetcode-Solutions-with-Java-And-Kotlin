package com.hi.dhl.algorithms.leetcode._009.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */


class Solution {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0 || (x % 10 == 0 && x > 0)) return false
        return x.isPalindrome1()
    }

    fun Int.isPalindrome1(): Boolean {
        var value = this
        var reverse = 0
        do {
            reverse = reverse * 10 + value % 10
            value = value / 10
        } while (value > reverse)

        return value == reverse || value == reverse / 10
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.isPalindrome(10))
}


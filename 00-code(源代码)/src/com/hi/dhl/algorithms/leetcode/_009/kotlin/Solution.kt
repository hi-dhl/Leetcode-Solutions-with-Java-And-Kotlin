package com.hi.dhl.algorithms.leetcode._009.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun isPalindrome(x: Int): Boolean {
        return x.palindrome()
    }

    fun Int.palindrome(): Boolean {
        val x = this;
        if (x < Int.MIN_VALUE || x > Int.MAX_VALUE) return false
        if (x < 0 || (x % 10 == 0 && x != 0)) return false
        var sum = 0
        var temp = x
        do {
            sum = sum * 10 + temp % 10
            temp = temp / 10;
        } while (temp > 0)
        return if (sum == x) true else false
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.isPalindrome(10))
}


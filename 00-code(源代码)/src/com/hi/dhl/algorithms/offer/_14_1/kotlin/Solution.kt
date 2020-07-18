package com.hi.dhl.algorithms.offer._14_1.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun cuttingRope(n: Int): Int {
        val dp = IntArray(if (n < 7) 8 else n + 1)
        dp[1] = 1
        dp[2] = 1
        dp[3] = 2
        dp[4] = 4
        dp[5] = 6
        dp[6] = 9
        dp[7] = 12
        for (i in 8..n) {
            dp[i] = 3 * dp[i - 3]
        }
        return dp[n]
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.cuttingRope(8))
}
package com.hi.dhl.algorithms.offer._14_2.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {
    fun cuttingRope(n: Int): Int {
        val dp = LongArray(if (n < 7) 8 else n + 1)
        dp[2] = 1
        dp[3] = 2
        dp[4] = 4
        dp[5] = 6
        dp[6] = 9
        dp[7] = 12
        for (i in 8..n) {
            dp[i] = (dp[i - 3] * 3) % 1000000007
        }
        return dp[n].toInt()
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.cuttingRope(120))
}
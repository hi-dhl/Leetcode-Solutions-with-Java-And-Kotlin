package com.hi.dhl.algorithms.offer._19.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/8
 *     desc  :
 * </pre>
 */

class Solution {
    fun isMatch(s: String, p: String): Boolean {
        val row = s.length
        val colum = p.length
        val dp = Array(row + 1) { BooleanArray(colum + 1) }
        dp[0][0] = true;
        for (i in 1..colum) {
            if (p[i - 1] == '*' && dp[0][i - 2]) {
                dp[0][i] = true
            }
        }

        for (i in 1..row) {
            for (j in 1..colum) {
                val ms = s[i - 1]
                val mp = p[j - 1]
                if (mp == ms || mp == '.') {
                    dp[i][j] = dp[i - 1][j - 1]
                } else if (mp == '*') {
                    if (j < 2) continue

                    val mpLast = p[j - 2]
                    if (ms == mpLast || mpLast == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1]
                    }

                    dp[i][j] = dp[i][j] || dp[i][j - 2]
                }
            }
        }
        return dp[row][colum]
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.isMatch("bb", ".bab"))
}
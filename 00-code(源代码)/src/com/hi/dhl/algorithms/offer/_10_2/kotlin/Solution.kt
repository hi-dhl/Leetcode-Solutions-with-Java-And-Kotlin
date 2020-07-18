package com.hi.dhl.algorithms.offer._10_2.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {
    fun numWays(n: Int): Int {
        if (n == 1 || n == 0) {
            return 1;
        }

        var a = 1;
        var b = 2;
        var sum = b;
        for (i in 2 until n) {
            sum = (a + b) % 1000000007
            a = b.also { b = sum }
        }
        return sum
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.numWays(100))//782204094
}
package com.hi.dhl.algorithms.leetcode._lcp18.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/7/30
 *     desc  :
 * </pre>
 */

class Solution {
    fun breakfastNumber(staple: IntArray, drinks: IntArray, x: Int): Int {
        val arr = IntArray(x + 1)
        val slen = staple.size
        for (i in 0 until slen) {
            if (staple[i] > x) continue
            arr[staple[i]]++
        }

        for (i in 1..x) {
            arr[i] += arr[i - 1]
        }

        val dlen = drinks.size
        var ans = 0
        for (i in 0 until dlen) {
            if (drinks[i] > x) continue
            ans += arr[x - drinks[i]]
            ans = ans % 1000000007
        }
        return ans
    }
}
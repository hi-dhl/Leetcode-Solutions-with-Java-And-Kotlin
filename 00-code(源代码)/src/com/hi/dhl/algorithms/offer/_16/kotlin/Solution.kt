package com.hi.dhl.algorithms.offer._16.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/26
 *     desc  :
 * </pre>
 */

class Solution {
    fun myPow(x: Double, n: Int): Double {
        if (x == 0.0) return 0.0
        var x1 = x
        var res = 1.0
        var m = n.toLong()
        if (m < 0) {
            x1 = 1 / x1
            m = -1 * m
        }

        while (m > 0) {
            if ((m % 2).toInt() == 1) res = res * x1
            x1 = x1 * x1
            m = m / 2
        }
        return res
    }
}
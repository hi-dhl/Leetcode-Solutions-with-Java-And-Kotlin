package com.hi.dhl.algorithms._007.kotlin


/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {
    fun reverse(x: Int): Int {
        var sum: Long = 0L
        var temp = if (x > 0) 1 else -1
        var element = Math.abs(x)
        while (element > 0) {
            sum = sum * 10 + element % 10
            element = element / 10
            if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) return 0
        }
        sum = temp * sum
        return sum.toInt()
    }

    fun main(args: Array<String>) {
        val num = reverse(-1232313111)
        print(num)
    }
}

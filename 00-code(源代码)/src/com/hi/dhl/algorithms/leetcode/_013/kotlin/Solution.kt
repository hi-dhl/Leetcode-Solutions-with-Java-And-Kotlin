package com.hi.dhl.algorithms.leetcode._013.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun romanToInt(s: String): Int {
        val roams = mapOf<Char, Int>('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
        var sum: Int = 0
        var i: Int = 0;
        while (i < s.length - 1) {
            var num1 = roams.get(s.get(i)) ?: 0
            var num2 = roams.get(s.get(i + 1)) ?: 0
            if (num2 > num1) {
                sum += (num2 - num1);
                i += 2
            } else {
                sum += num1
                i += 1
            }
        }
        if (i < s.length) sum += roams.get(s.get(s.length - 1)) ?: 0
        return sum
    }

    fun main(args: Array<String>) {
        println(romanToInt("LVIII"))
    }
}

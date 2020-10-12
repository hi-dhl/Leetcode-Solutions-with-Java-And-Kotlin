package com.hi.dhl.algorithms.leetcode._344.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/8
 *     desc  :
 * </pre>
 */

class Solution {
    fun reverseString(s: CharArray): Unit {
        var i = 0;
        var j = s.size - 1
        while (i < j) {
            s[i] = s[j].also { s[j] = s[i] }
            i++
            j--
        }
    }
}
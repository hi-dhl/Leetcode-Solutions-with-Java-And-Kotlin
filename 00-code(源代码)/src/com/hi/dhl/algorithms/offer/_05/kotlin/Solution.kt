package com.hi.dhl.algorithms.offer._05.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun replaceSpace(s: String): String {
        val buffer = StringBuffer()
        var i = 0
        val len = s.length
        while (i < len) {
            if (s[i] == ' ') {
                buffer.append("%20")
            } else {
                buffer.append(s[i])
            }
            i++
        }
        return buffer.toString()
    }
}
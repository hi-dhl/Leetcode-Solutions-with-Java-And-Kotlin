package com.hi.dhl.algorithms._014.koltin

import java.util.ArrayList

/**
 * <pre>
 *     author: dhl
 * </pre>
 */

class Solution {

    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.size <= 0) {
            return ""
        }

        val str0 = strs[0]
        val len = str0.length
        val count = strs.size

        for (i in 0 until len) {
            val c = str0[i]
            for (j in 1 until count) {
                if (i == strs[j].length || c != strs[j][i]) {
                    return str0.substring(0, i)
                }
            }
        }
        return str0
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    val list = ArrayList<Array<String>>()
    list.add(arrayOf("falower", "f", "fbight"))
    list.add(arrayOf("", "cba"))
    list.add(arrayOf("a"))
    for ((i, item) in list.withIndex())
        println(solution.longestCommonPrefix(item))

}
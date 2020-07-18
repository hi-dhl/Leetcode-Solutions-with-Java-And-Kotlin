package com.hi.dhl.algorithms._014.koltin

import java.util.ArrayList

/**
 * <pre>
 *     author: dhl
 * </pre>
 */

class Solution {
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.size <= 0) return "";
        if (strs.size == 1) return strs[0];
        var src = strs[0];
        for (i in 1 until strs.size) {
            src = findPrefixCommonStr(src, strs[i])
        }
        return src
    }

    tailrec fun findPrefixCommonStr(src: String, dest: String): String = run {
        if (src.isEmpty() || src.length <= 0) return "";
        if (dest.indexOf(src) == 0) return src;
        var pre = src.substring(0, src.length - 1);
        return findPrefixCommonStr(pre, dest)
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
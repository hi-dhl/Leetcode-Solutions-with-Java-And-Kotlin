package com.hi.dhl.algorithms._003.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/30
 *     desc  :
 * </pre>
 */

class Solution {
    // 方法一：滑动窗口 hashSet
    fun lengthOfLongestSubstring(s: String): Int {
        val len = s.length
        if (len <= 0) return 0
        var r = 0
        var count = 0
        val hashSet = mutableSetOf<Char>()
        for (i in 0 until len) {
            if (i != 0) {
                hashSet.remove(s[i - 1])
            }

            while (r < len && !hashSet.contains(s[r])) {
                hashSet.add(s[r])
                r++
            }
            count = Math.max(count, r - i)
        }
        return count
    }

    // 方法二：滑动窗口 hashMap
    fun lengthOfLongestSubstring2(s: String): Int {
        val len = s.length;
        if (len == 0) {
            return 0
        }

        var left = 0
        var count = 0
        val map = mutableMapOf<Char, Int>()
        for (i in 0 until len) {
            if (map.containsKey(s[i])) {
                left = Math.max(left, map.get(s[i])?.let { it + 1 } ?: left)
            }
            map.put(s[i], i)
            count = Math.max(count, i - left + 1)
        }
        return count
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.lengthOfLongestSubstring2(" "))
}
package com.hi.dhl.algorithms._003.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/30
 *     desc  :
 * </pre>
 */

class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        val len = s.length
        if(len <= 0) return 0
        var r = 0
        var count = 0
        val hashSet = mutableSetOf<Char>()
        for(i in 0 until len){
            if(i!=0){
                hashSet.remove(s[i-1])
            }

            while(r<len && !hashSet.contains(s[r])){
                hashSet.add(s[r])
                r++
            }
            count = Math.max(count,r - i)
        }
        return count
    }
}
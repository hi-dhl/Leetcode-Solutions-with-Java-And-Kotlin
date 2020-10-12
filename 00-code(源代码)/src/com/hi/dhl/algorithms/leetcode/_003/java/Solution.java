package com.hi.dhl.algorithms.leetcode._003.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/30
 *     desc  :
 * </pre>
 */

class Solution {
    /**
     * 方法一：滑动窗口 hashSet
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int r = 0;
        int count = 0;
        int len = s.length();
        Set<Character> hashSet = new HashSet<Character>();
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                hashSet.remove(s.charAt(i - 1));
            }

            while (r < len && !hashSet.contains(s.charAt(r))) {
                hashSet.add(s.charAt(r));
                r++;
            }
            count = Math.max(count, r - i);
        }
        return count;
    }

    /**
     * 方法二：滑动窗口 hashMap
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int left = 0;
        int count = 0;
        int len = s.length();
        Map<Character, Integer> hashMap = new HashMap<Character, Integer>();
        for (int i = 0; i < len; i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                left = Math.max(left, hashMap.get(s.charAt(i)) + 1);
            }
            hashMap.put(s.charAt(i), i);
            count = Math.max(count, i - left + 1);
        }
        return count;
    }
}
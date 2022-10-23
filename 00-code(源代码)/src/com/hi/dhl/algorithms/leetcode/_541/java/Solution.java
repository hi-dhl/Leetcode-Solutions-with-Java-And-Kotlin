package com.hi.dhl.algorithms.leetcode._541.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/10/22
 *     desc  :
 * </pre>
 */
class Solution {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i += 2 * k) {
            int l = i;
            int r = Math.min(i + k - 1, len - 1);
            while (l < r) {
                char tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;
                l++;
                r--;
            }
        }
        return new String(chars);
    }
}
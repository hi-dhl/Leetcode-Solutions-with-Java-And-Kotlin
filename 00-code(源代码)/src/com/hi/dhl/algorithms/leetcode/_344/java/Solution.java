package com.hi.dhl.algorithms.leetcode._344.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/8
 *     desc  :
 * </pre>
 */

class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 0) return;
        int i = 0;
        int j = s.length - 1;
        while (i <= j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}
package com.hi.dhl.algorithms.leetcode._151.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/10/27
 *     desc  :
 * </pre>
 */

class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int j = chars.length - 1;
        int i = j;
        StringBuilder builder = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && chars[i] == ' ') {
                i--;
            }
            j = i;
            while (i >= 0 && chars[i] != ' ') {
                i--;
            }
            builder.append(s.substring(i + 1, j + 1));
            while (i >= 0 && chars[i] == ' ') {
                i--;
            }
            if (i >= 0) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }
}

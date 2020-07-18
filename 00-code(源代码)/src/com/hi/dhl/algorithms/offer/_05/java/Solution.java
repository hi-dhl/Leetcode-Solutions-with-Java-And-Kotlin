package com.hi.dhl.algorithms.offer._05.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {
    public String replaceSpace(String s) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                buffer.append("%20");
            } else {
                buffer.append(s.charAt(i));
            }
        }
        return buffer.toString();
    }
}

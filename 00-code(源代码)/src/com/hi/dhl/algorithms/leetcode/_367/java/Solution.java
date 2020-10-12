package com.hi.dhl.algorithms.leetcode._367.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {
    public boolean isPerfectSquare(int num) {
        long low = 0;
        long height = (num >>> 1) + 1;
        while (low <= height) {
            long mind = (low + height) >>> 1;
            long square = mind * mind;
            if (square == num) {
                return true;
            } else if (square < num) {
                low = mind + 1;
            } else {
                height = mind - 1;
            }
        }
        return false;
    }
}

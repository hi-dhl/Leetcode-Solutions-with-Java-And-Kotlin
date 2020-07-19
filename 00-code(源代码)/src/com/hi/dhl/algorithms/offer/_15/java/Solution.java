package com.hi.dhl.algorithms.offer._15.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/19
 *     desc  :
 *
 *     这道题同 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/
 *
 * </pre>
 */
public class Solution {

    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            result += n & 1;
            n = n >>> 1;
        }
        return result;
    }

}
package com.hi.dhl.algorithms.offer._16.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/26
 *     desc  :
 * </pre>
 */

class Solution {

    // x >> 1 ==> (x = x / 2)
    // x << 1 ==> (x = x * 2)
    // x & 1 ==> (x % 2 == 1)

    public double myPow(double x, int n) {
        if (x == 0) return 0;
        long m = n;
        double res = 1.0;

        if (m < 0) {
            x = 1 / x;
            m *= -1;
        }

        while (m > 0) {
            if ((m & 1) == 1) res = res * x;
            x = x * x;
            m = m >> 1;
        }

        return res;
    }
}
package com.hi.dhl.algorithms.leetcode._lcp18.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/7/30
 *     desc  :
 * </pre>
 */

class Solution {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int[] arr = new int[x + 1];

        // int i = 0;
        int slen = staple.length;
        for (int i = 0; i < slen; i++) {
            if (staple[i] > x) continue;
            arr[staple[i]]++;
        }

        for (int i = 1; i < x + 1; i++) {
            arr[i] += arr[i - 1];
        }

        // int j = 0;
        int dlen = drinks.length;
        int ans = 0;
        for (int i = 0; i < dlen; i++) {
            if (drinks[i] > x) continue;
            ans += arr[x - drinks[i]];
            ans = ans % 1000000007;
        }
        return ans;
    }
}
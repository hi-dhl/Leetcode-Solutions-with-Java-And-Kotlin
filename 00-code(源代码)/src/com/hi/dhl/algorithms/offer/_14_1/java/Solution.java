package com.hi.dhl.algorithms.offer._14_1.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {

    public int cuttingRope(int n) {
        int m = n;
        if (n < 7) {
            n = 7;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        dp[5] = 6;
        dp[6] = 9;
        dp[7] = 12;
        for (int i = 8; i <= n; i++) {
            dp[i] = 3 * dp[i - 3];
        }
        return dp[m];
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.cuttingRope(11));
    }
}

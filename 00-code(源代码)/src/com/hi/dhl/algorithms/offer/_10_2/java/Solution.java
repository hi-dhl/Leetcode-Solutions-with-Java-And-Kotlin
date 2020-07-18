package com.hi.dhl.algorithms.offer._10_2.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {
    public int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int a = 1;
        int b = 2;
        int sum = b;
        for (int i = 2; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.numWays(7));
    }
}
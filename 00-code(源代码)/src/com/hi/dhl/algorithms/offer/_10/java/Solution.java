package com.hi.dhl.algorithms.offer._10.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int[] data = new int[n + 1];
        data[0] = 0;
        data[1] = 1;
        for (int i = 2; i <= n; i++) {
            data[i] = (data[i - 1] + data[i - 2]) % 1000000007;
        }
        return data[n];
    }

    public int fib2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int a = 0;
        int b = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.fib(45));
    }
}

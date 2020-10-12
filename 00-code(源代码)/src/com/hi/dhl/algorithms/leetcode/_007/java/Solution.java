package com.hi.dhl.algorithms.leetcode._007.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {
    public int reverse(int x) {
        long sum = 0;
        int temp = 1;
        if (x < 0) {
            temp = -1;
            x = x * -1;
        }
        while (x > 0) {
            int a = x % 10;
            sum = sum * 10 + a;
            x = x / 10;
            if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) return 0;
        }
        return (int) (temp * sum);
    }

    public static void main(String... args) {
        int num = new Solution().reverse(1534236469);
        System.out.println("num= " + num);
    }
}

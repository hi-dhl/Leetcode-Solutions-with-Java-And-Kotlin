package com.hi.dhl.algorithms.leetcode._009.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x > 0)) return false;

        int reverse = 0;
        do {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        } while (x > reverse);

        return x == reverse || x == reverse / 10;
    }


    public static void main(String... args) {
        Solution palindromeNums = new Solution();
        System.out.println(palindromeNums.isPalindrome(121));
    }
}

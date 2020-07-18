package com.hi.dhl.algorithms._009.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {

    public boolean isPalindrome(int x) {
        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE)
            return false;

        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int sum = 0;
        int temp = x;
        do {
            sum = sum * 10 + temp % 10;
            temp = temp / 10;
        } while (temp > 0);

        if (sum == x) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String... args) {
        Solution palindromeNums = new Solution();
        System.out.println(palindromeNums.isPalindrome(121));
    }
}

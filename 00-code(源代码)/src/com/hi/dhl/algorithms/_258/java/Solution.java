package com.hi.dhl.algorithms._258.java;

/**
 * <pre>
 *     author: dhl
 *     desc  : add-digits
 *     site: https://leetcode.com/problems/add-digits/
 *
 *     Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *     Example:
 *          Input: 38
 *          Output: 2
 *          Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
 *          Since 2 has only one digit, return it.
 * </pre>
 */
public class Solution {

    //方法二
    public int addDigits(int num) {
        if (num == 0) return num;
        if (num > 0 && num <= 9) return num;
        int sum = 0;
        while (num != 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        sum = sum + num % 10;
        return addDigits(sum);
    }

    //方法一
    public int addDigits2(int num) {
        String builder = String.valueOf(num);
        while (builder.length() > 1) {
            int sum = 0;
            for (int i = 0; i < builder.length(); i++) {
                sum += builder.charAt(i) - '0';
            }
            builder = String.valueOf(sum);
        }
        return Integer.parseInt(builder);
    }

    public static void main(String... args) {
        int num = 1122;
        Solution solution = new Solution();
        int sum = solution.addDigits(num);
        System.out.println(sum);
    }
}

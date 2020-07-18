package com.hi.dhl.algorithms._067.java;

/**
 * <pre>
 *     author: dhl
 *     desc  : add-binary
 *     site: https://leetcode.com/problems/add-binary/
 *
 *     Given two binary strings, return their sum (also a binary string).
 *     The input strings are both non-empty and contains only characters 1 or 0.
 *     Example 1:
 *          Input: a = "11", b = "1"
 *          Output: "100"
 *          Example 2:
 *          Input: a = "1010", b = "1011"
 *          Output: "10101"
 * </pre>
 */
public class Solution {
    public String addBinary(String a, String b) {
        int alen = a.length();
        int blen = b.length();
        int max = alen > blen ? alen : blen;
        int sum = 0;
        StringBuilder builder = new StringBuilder(max);
        for (int i = 0; i < max; i++) {
            int acnum = i < alen ? a.charAt(alen - 1 - i) - '0' : 0;
            int bcnum = i < blen ? b.charAt(blen - 1 - i) - '0' : 0;
            int rec = acnum + bcnum + sum;
            sum = rec / 2;
            builder.append(rec % 2);
        }
        return sum == 0 ? builder.reverse().toString() : 1 + builder.reverse().toString();
    }

    public static void main(String... args) {

        String a = "100";
        String b = "110010";

        Solution solution = new Solution();
        System.out.println(solution.addBinary(a, b));
    }
}

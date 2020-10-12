package com.hi.dhl.algorithms.leetcode._013.java;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> roams = new HashMap<>();
        roams.put('I', 1);
        roams.put('V', 5);
        roams.put('X', 10);
        roams.put('L', 50);
        roams.put('C', 100);
        roams.put('D', 500);
        roams.put('M', 1000);
        int sum = 0;
        int i = 0;
        while (i < s.length() - 1) {
            int j = i + 1;
            char char1 = s.charAt(i);
            char char2 = s.charAt(j);
            int num1 = roams.get(char1);
            int num2 = roams.get(char2);
            if (num2 > num1) {
                sum += (num2 - num1);
                i += 2;
            } else {
                sum += num1;
                i++;
            }
        }
        if (i < s.length()) {
            sum += roams.get(s.charAt(s.length() - 1));
        }
        return sum;
    }

    public static void main(String... args) {
        Solution romanToInteger = new Solution();
        System.out.println(romanToInteger.romanToInt("LVIII"));
    }
}

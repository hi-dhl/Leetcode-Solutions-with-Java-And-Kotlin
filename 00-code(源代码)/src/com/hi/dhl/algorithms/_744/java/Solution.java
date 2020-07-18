package com.hi.dhl.algorithms._744.java;

import kotlin.collections.ArraysKt;

import java.util.Arrays;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {

    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        int height = letters.length - 1;
        if (target < letters[0] || target >= letters[height]) return letters[0];
        while (low <= height) {
            int mind = (low + height) >>> 1;
            if (letters[mind] <= target) {
                low = mind + 1;
            } else {
                height = mind - 1;
            }
        }
        return letters[low];
    }

    public static void main(String... args) {
        char[] letters = new char[]{'c', 'f', 'j'};
        Solution solution = new Solution();
        System.out.println('j' - 0);
        System.out.println('g' - 0);
        System.out.println(solution.nextGreatestLetter(letters, 'j'));


    }


}

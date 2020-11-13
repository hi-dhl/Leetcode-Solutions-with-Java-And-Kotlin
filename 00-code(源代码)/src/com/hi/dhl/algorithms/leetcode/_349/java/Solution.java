package com.hi.dhl.algorithms.leetcode._349.java;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/11/13
 *     desc  :
 * </pre>
 */

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        for (int value : nums1) {
            set1.add(value);
        }

        Set<Integer> set2 = new HashSet<Integer>();
        for (int value : nums2) {
            if (set1.contains(value)) {
                set2.add(value);
            }
        }

        int[] result = new int[set2.size()];
        int index = 0;
        for (int value : set2) {
            result[index++] = value;
        }
        return result;
    }
}
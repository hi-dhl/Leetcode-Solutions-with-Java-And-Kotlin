package com.hi.dhl.algorithms.leetcode._349.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/11/13
 *     desc  :
 * </pre>
 */

class Solution {

    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val set1 = HashSet<Int>();
        for (value in nums1) {
            set1.add(value);
        }

        val set2 = HashSet<Int>();
        for (value in nums2) {
            if (set1.contains(value)) {
                set2.add(value);
            }
        }

        val result = IntArray(set2.size);
        var index = 0;
        for (value in set2) {
            result[index++] = value;
        }

        return result;
    }
}

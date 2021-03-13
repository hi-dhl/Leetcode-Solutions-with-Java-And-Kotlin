package com.hi.dhl.algorithms.leetcode._349.java;

import java.util.Arrays;
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

    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index = 0;
        int i = 0;
        int j = 0;
        int alen = nums1.length;
        int blen = nums2.length;
        int[] data = new int[alen + blen];
        while (i < alen && j < blen) {
            int n1 = nums1[i];
            int n2 = nums2[j];
            if (n1 == n2) {
                if (index == 0 || data[index - 1] != n1) {
                    data[index++] = n1;
                }
                i++;
                j++;
            } else if (n1 < n2) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(data, 0, index);
    }
}

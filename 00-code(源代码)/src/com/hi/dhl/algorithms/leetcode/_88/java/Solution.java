package com.hi.dhl.algorithms.leetcode._88.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/7/14
 *     desc  :
 * </pre>
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n - 1;
        m = m - 1;
        n = n - 1;

        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[len--] = nums1[m--];
            } else {
                nums1[len--] = nums2[n--];
            }
        }
        while (m >= 0) {
            nums1[len--] = nums1[m--];
        }
        while (n >= 0) {
            nums1[len--] = nums2[n--];
        }
    }
}
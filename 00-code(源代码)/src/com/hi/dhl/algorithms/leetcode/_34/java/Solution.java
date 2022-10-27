package com.hi.dhl.algorithms.leetcode._34.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/10/25
 *     desc  :
 * </pre>
 */

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null) {
            return new int[]{-1, -1};
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        if (hi >= 0 && nums[hi] != target) {
            return new int[]{-1, -1};
        }

        int right = lo - 1;
        lo = 0;
        hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] >= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        int left = hi + 1;
        if (left <= right) {
            return new int[]{left, right};
        } else {
            return new int[]{-1, -1};
        }
    }
}
package com.hi.dhl.algorithms.leetcode._462.java;

import java.util.Arrays;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/10/23
 *     desc  :
 * </pre>
 */
class Solution {

    // 方法一
    public int minMoves3(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int midNum = nums[len / 2];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.abs(midNum - nums[i]);
        }
        return sum;
    }

    // 方法二
    public int minMoves2(int[] nums) {
        int midNum = findMindNum(nums, 0, nums.length - 1, nums.length / 2);
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(midNum - num);
        }
        return sum;
    }

    int findMindNum(int[] nums, int lo, int hi, int k) {
        if (lo >= hi) {
            return nums[lo];
        }
        int mid = quickSort(nums, lo, hi);
        if (mid == k) {
            return nums[mid];
        } else if (mid < k) {
            return findMindNum(nums, mid + 1, hi, k);
        } else {
            return findMindNum(nums, lo, mid - 1, k);
        }
    }

    int quickSort(int[] nums, int lo, int hi) {
        int left = lo;
        int right = hi;
        int k = nums[lo];
        while (left < right) {
            while (left < right && nums[right] >= k) {
                right--;
            }
            while (left < right && nums[left] <= k) {
                left++;
            }
            swap(nums, left, right);
        }
        swap(nums, lo, left);
        return left;
    }

    void swap(int[] nums, int lo, int hi) {
        int temp = nums[lo];
        nums[lo] = nums[hi];
        nums[hi] = temp;
    }


}
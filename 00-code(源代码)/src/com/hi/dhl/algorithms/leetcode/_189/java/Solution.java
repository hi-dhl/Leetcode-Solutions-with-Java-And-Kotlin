package com.hi.dhl.algorithms.leetcode._189.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/10/30
 *     desc  :
 * </pre>
 */
class Solution {
    // 方法一
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int times = 1;
        // 数组的总交换次数是和数组长度相等的
        // 所以记录一下总的交换次数即可
        for (int i = 0; i < times; i++) {
            int pre = nums[i];
            int cur = i;
            int count = 0; // 记录每次循环的交换次数
            do {
                cur = (cur + k) % len;
                int holder = nums[cur];
                nums[cur] = pre;
                pre = holder;
                count++;
            } while (i != cur); // 是否回到原点
            times = len / count; // 剩余交换次数
        }
    }

    // 方法二
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        swap(nums, 0, len - 1);
        swap(nums, 0, k - 1);
        swap(nums, k, len - 1);
    }

    public void swap(int[] nums, int lo, int hi) {
        while (lo < hi) {
            int holder = nums[lo];
            nums[lo] = nums[hi];
            nums[hi] = holder;
            lo++;
            hi--;
        }
    }
}
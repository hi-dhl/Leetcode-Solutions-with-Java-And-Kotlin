package com.hi.dhl.algorithms.offer._03.java;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {

    /**
     * 原地置换法
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == i) continue;
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int temp = nums[i];
            nums[nums[i]] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }

    /**
     * 哈希算法
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < len; i++) {
            if (!set.add(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.findRepeatNumber2(new int[]{1, 11, 2, 3, 4, 11, 6, 7, 8, 9, 10, 19, 12, 13, 14, 1}));
    }
}
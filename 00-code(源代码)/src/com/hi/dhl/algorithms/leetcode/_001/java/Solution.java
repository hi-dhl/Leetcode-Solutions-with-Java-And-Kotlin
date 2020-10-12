package com.hi.dhl.algorithms.leetcode._001.java;

import java.util.HashMap;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int a = target - nums[i];
            if (map.containsKey(a))
                return new int[]{map.get(a), i};
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String... args) {

    }

}

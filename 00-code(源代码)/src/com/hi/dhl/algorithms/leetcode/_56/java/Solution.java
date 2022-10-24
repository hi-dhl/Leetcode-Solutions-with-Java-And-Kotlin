package com.hi.dhl.algorithms.leetcode._56.java;

import java.util.Arrays;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/10/24
 *     desc  :
 * </pre>
 */

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return new int[0][];
        }
        int len = intervals.length;
        Arrays.sort(intervals, (x, y) -> (x[0] - y[0]));
        int[][] result = new int[len][2];
        int index = 0;
        for (int[] item : intervals) {
            if (index == 0 || item[0] > result[index - 1][1]) {
                result[index++] = item;
            } else {
                result[index - 1][1] = Math.max(item[1], result[index - 1][1]);
            }
        }
        return Arrays.copyOfRange(result, 0, index);
    }
}
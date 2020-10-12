package com.hi.dhl.algorithms.leetcode._240.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }

        int raw = 0;
        int column = matrix[0].length - 1;
        int maxRaw = matrix.length - 1;
        while (raw <= maxRaw && column >= 0) {
            if (matrix[raw][column] == target) {
                return true;
            } else if (matrix[raw][column] > target) {
                column--;
            } else {
                raw++;
            }
        }
        return false;
    }
}
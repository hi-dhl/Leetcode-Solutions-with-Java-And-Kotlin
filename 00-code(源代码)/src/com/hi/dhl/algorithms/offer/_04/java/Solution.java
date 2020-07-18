package com.hi.dhl.algorithms.offer._04.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int row = 0;
        int column = matrix[0].length - 1;
        int maxRow = matrix.length - 1;
        while (row <= maxRow && column >= 0) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }
}
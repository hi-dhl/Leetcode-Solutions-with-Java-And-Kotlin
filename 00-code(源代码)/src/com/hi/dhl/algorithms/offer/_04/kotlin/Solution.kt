package com.hi.dhl.algorithms.offer._04.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix == null || matrix.size <= 0 || matrix[0].size <= 0) {
            return false;
        }

        var raw = 0
        var column = matrix[0].size - 1;
        val maxRaw = matrix.size - 1;
        while (raw <= maxRaw && column >= 0) {
            when {
                matrix[raw][column] == target -> return true
                matrix[raw][column] > target -> column--
                else -> raw++
            }
        }
        return false
    }
}
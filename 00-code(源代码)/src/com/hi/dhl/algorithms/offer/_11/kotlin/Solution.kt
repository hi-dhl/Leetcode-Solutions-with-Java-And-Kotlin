package com.hi.dhl.algorithms.offer._11.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun minArray(numbers: IntArray): Int {
        var left = 0
        var right = numbers.size - 1
        while (left < right) {
            val mid = (left + right) ushr 1
            when {
                numbers[mid] > numbers[right] -> left = mid + 1
                numbers[mid] < numbers[right] -> right = mid
                else -> right = right - 1
            }
        }
        return numbers[left]
    }
}

fun main() {
    val solution = Solution()
    println(solution.minArray(intArrayOf(1, 1)))
}
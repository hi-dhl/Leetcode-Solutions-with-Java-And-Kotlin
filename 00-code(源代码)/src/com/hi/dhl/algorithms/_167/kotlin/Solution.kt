package com.hi.dhl.algorithms._167.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {

    // 方法一
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var height = numbers.size - 1
        var low = 0;
        while (low < height) {
            val sum = numbers[low] + numbers[height]
            when {
                sum == target -> return intArrayOf(low + 1, height + 1)
                sum < target -> low++
                else -> height--
            }
        }
        return intArrayOf()
    }

    // 方法二
    fun twoSum2(numbers: IntArray, target: Int): IntArray {

        numbers.forEachIndexed { i, value ->
            val index = binarySearch(numbers, i + 1, target - value)
            if (index != -1) {
                return intArrayOf(i + 1, index + 1)
            }
        }

        return intArrayOf()
    }

    fun binarySearch(numbers: IntArray, start: Int, target: Int): Int {
        var low = start
        var height = numbers.size - 1
        while (low <= height) {
            val mind = (low + height) ushr 1
            when {
                numbers[mind] == target -> return mind
                numbers[mind] < target -> low = mind + 1
                else -> height = mind - 1
            }
        }
        return -1
    }
}
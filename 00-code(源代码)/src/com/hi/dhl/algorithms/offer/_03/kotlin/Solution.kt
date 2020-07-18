package com.hi.dhl.algorithms.offer._03.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        for ((index, value) in nums.withIndex()) {
            if (value == index) continue
            if (nums[value] == nums[index]) {
                return nums[index]
            }
            val temp = value
            nums[value] = nums[temp]
            nums[temp] = temp
        }
        return -1
    }

    fun findRepeatNumber2(nums: IntArray): Int {
        val set = HashSet<Int>()
        for ((index, value) in nums.withIndex()) {
            if (!set.add(value)) {
                return value;
            }
        }
        return -1
    }

}

fun main() {
    val solution = Solution()
    val arry = intArrayOf(1, 11, 2, 3, 4, 11, 6, 7, 8, 9, 10, 19, 11, 13, 14, 15)
    println(solution.findRepeatNumber2(arry))
}
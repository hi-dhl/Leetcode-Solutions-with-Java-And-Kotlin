package com.hi.dhl.algorithms._001.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        var map = hashMapOf<Int, Int>()
        for ((index, value) in nums.withIndex()) {
            var a = target - value
            if (map.containsKey(a)) {
                return intArrayOf(index, map.get(a)!!)
            }
            map.put(value, index)
        }
        return intArrayOf()
    }

    fun main(args: Array<String>) {

    }

}

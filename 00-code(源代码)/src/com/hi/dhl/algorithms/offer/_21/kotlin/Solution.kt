package com.hi.dhl.algorithms.offer._21.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/28
 *     desc  :
 * </pre>
 */

class Solution {
    /**
     * 左右指针
     */
    fun exchange(nums: IntArray): IntArray {
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            // and == &
            while (left < right && nums[left] and 1 == 1) left++
            while (left < right && nums[right] and 1 != 1) right--

            if (left < right) {
                // xor == ^
                nums[left] = nums[left] xor nums[right]
                nums[right] = nums[left] xor nums[right]
                nums[left] = nums[left] xor nums[right]
            }
        }
        return nums;
    }

    /**
     * 快慢指针
     */
    fun exchange2(nums: IntArray): IntArray {
        var show = 0
        var fast = 0
        val size = nums.size
        while (fast < size) {
            // and == &
            if (nums[fast] and 1 == 1) {
                val tmp = nums[show]
                nums[show] = nums[fast];
                nums[fast] = tmp;
                show++
            }

            fast++
        }
        return nums;
    }
}

package com.hi.dhl.algorithms._067.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {

    fun nextGreatestLetter(letters: CharArray, target: Char): Char {
        var low = 0
        var height = letters.size - 1
        if (target < letters[0] || target >= letters[height]) {
            return letters[0]
        }

        while (low <= height) {
            val mid = (low + height) ushr 1
            when {
                letters[mid] <= target -> low = mid + 1
                else -> height = mid - 1
            }
        }
        return letters[low]
    }

}
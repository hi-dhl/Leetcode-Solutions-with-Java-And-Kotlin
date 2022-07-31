package com.hi.dhl.algorithms.leetcode._1122.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/7/31
 *     desc  :
 * </pre>
 */
class Solution {
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        val result = IntArray(1001)
        val alen = arr1.size
        for (i in 0 until alen) {
            result[arr1[i]]++
        }

        var index = 0
        val blen = arr2.size
        for (i in 0 until blen) {
            val key = arr2[i]
            while (result[key] > 0) {
                arr1[index++] = key
                result[key]--
            }
        }

        val clen = result.size
        for (i in 0 until clen) {
            while (result[i] > 0) {
                arr1[index++] = i
                result[i]--
            }
        }
        return arr1
    }
}
package com.hi.dhl.algorithms.other.company.uber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/8
 *     desc  :
 * </pre>
 */

class Solution {

    fun main(args: Array<String>) {

//        测试用例
//    val array = intArrayOf(1, 2, 3, 4, 5) // 120, 60, 40, 30, 24
//    val array = intArrayOf(3, 2, 1) // 2, 3, 6
//    val array = intArrayOf(3, 0, 1)
        val array = intArrayOf(3, 0, 1, 0)

        productArrays(array)
        for (i in 0 until array.size) {
            print(" ${array[i]} ")
        }
    }

    fun productArrays(array: IntArray) {
        var sum = 1
        var countZeros = 0
        val len = array.size

        for (item in 0 until len) {
            if (array[item] == 0) {
                countZeros++
            } else {
                sum = sum * array[item]
            }
        }

        for (i in 0 until len) {

            when {
                countZeros >= 2 -> array[i] = 0
                countZeros == 1 -> {
                    if (array[i] != 0) {
                        array[i] = 0
                    } else {
                        array[i] = sum
                    }
                }
                else -> array[i] = sum / array[i]
            }

        }
    }

}
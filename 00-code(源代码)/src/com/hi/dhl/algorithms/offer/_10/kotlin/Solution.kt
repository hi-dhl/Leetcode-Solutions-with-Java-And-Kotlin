package com.hi.dhl.algorithms.offer._10.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

class Solution {
    fun fib(n: Int): Int {
        return when {
            n == 0 -> return 0
            n == 1 -> return 1
            else -> {
                val data = mutableListOf<Int>()
                data.add(0)
                data.add(1)
                for (i in 2..n) {
                    data.add((data[i - 1] + data[i - 2]) % 1000000007)
                }
                return data[n]
            }
        }
    }

    companion object {
        val INSTANCE by lazy { Solution() }
    }
}

fun main() {
    println(Solution.INSTANCE.fib(45))
}
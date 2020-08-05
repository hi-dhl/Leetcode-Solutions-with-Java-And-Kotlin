package com.hi.dhl.algorithms.offer._17.kotlin

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/5
 *     desc  :
 * </pre>
 */

class Solution {

    // 方法一
    fun printNumbers2(n: Int): IntArray {
        var max = 1;
        for (i in 1..n) {
            max = max * 10;
        }
        val result = IntArray(max - 1)
        for (i in 1 until max) {
            result[i - 1] = i
        }
        return result;
    }

    // 方法二
    val defNum = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    var index = 0;

    fun printNumbers(n: Int): IntArray {
        val num = CharArray(n)
        var max = 1
        // kotlin 中使用 Math.pow 要求参数都是double类型，所以这里自动生成对应的位数
        for (i in 1..n) {
            max = max * 10;
        }
        val result = IntArray(max - 1)
        dfs(num, result, 0) // 开始递归遍历
        return result;
    }

    fun dfs(num: CharArray, result: IntArray, x: Int) {
        if (x == num.size) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // parstInt 方法去删除高位多余的 0
            val res = parstInt(num);
            // 过滤掉第一个数字 0
            if (res > 0) {
                result[index] = res
                index = index + 1
            }
            return;
        }

        for (c in defNum) {
            num[x] = c
            dfs(num, result, x + 1)
        }
    }

    fun parstInt(num: CharArray): Int {
        var sum = 0
        var isNotZero = false
        for (c in num) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // 过滤掉高位多余的 0
            if (!isNotZero) {
                if (c == '0') {
                    continue
                } else {
                    isNotZero = true
                }
            }
            sum = sum * 10 + (c - '0')
        }
        return sum;
    }
}
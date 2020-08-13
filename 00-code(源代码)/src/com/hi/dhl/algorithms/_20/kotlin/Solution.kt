package com.hi.dhl.algorithms._20.kotlin

import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/10
 *     desc  :
 * </pre>
 */

class Solution {
    fun isValid(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        // 遍历字符串
        for (c in s) {
            when (c) {
                // 遇到左括号，则将其对应的右括号压入栈中
                '(' -> stack.push(')')
                '[' -> stack.push(']')
                '{' -> stack.push('}')
                else -> {
                    // 当前右括号，与栈顶元素不相等，不相等直接返回 false
                    val tmp = stack.poll()
                    if (c != tmp) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.isValid("()"))
}
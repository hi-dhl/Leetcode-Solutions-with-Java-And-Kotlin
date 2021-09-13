package com.hi.dhl.algorithms.leetcode._20.kotlin

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
        // 开始遍历字符串
        for (c in s) {
            when (c) {
                // 遇到左括号，将对应的右括号压入栈中
                '(' -> stack.push(')')
                '[' -> stack.push(']')
                '{' -> stack.push('}')
                else -> {
                    // 遇到右括号，判断当前元素是否和栈顶元素相等，不相等提前返回，结束循环
                    if (stack.isEmpty() || stack.poll() != c) {
                        return false
                    }
                }
            }
        }
        // 通过判断栈是否为空，来检查是否是有效的括号
        return stack.isEmpty()
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.isValid("()"))
}
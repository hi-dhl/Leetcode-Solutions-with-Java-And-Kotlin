package com.hi.dhl.algorithms._20.java;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * <pre>
 *     author: dhl
 *     date  : 2019/5/14
 *     desc  : 20. Valid Parentheses
 *
 *     题目来源于 LeetCode 上第 20号（Valid Parentheses）问题：有效的括号。题目难度为 Easy。
 *     题目地址：https://leetcode.com/problems/valid-parentheses/
 *
 *     题目描述:
 *
 *     Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *     给定一个字符串, 只包括 '('，')'，'{'，'}'，'['，']'，判断字符串是否有效
 *
 *     An input string is valid if:
 *
 *     有效字符串需要满足以下条件：
 *
 *     Open brackets must be closed by the same type of brackets.
 *      左括号必须用相同类型的右括号闭合
 *
 *     Open brackets must be closed in the correct order.
 *     左括号必须以正确的顺序闭合
 *
 *     Note that an empty string is also considered valid.
 *
 *     注意空字符串可被认为是有效字符串。
 *
 *     Example 1:
 *     Input: "()"
 *     Output: true
 *
 *     Example 2:
 *     Input: "()[]{}"
 *     Output: true
 *
 *     Example 3:
 *     Input: "(]"
 *     Output: false
 *
 *     Example 4:
 *     Input: "([)]"
 *     Output: false
 *
 *     Example 5:
 *     Input: "{[]}"
 *     Output: true
 *
 *    题目解析:
 *
 *    1. 遍历字符串
 *    2. 遇到左括号，则将其对应的右括号压入栈中
 *    3. 如果遇到右括号，检查与栈顶元素不相等，不相等直接返回 false
 *    4. 重复执行 步骤 2 和步骤 3
 *    5. 循环结束之后，判断栈是否为空，不为空返回false
 *
 * </pre>
 */

class Solution {
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 遇到左括号，则将其对应的右括号压入栈中
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                // 当前栈为空，直接返回 false
                if (stack.isEmpty()) {
                    return false;
                }
                // 当前右括号，与栈顶元素不相等，不相等直接返回 false
                char tmp = stack.poll();
                if (c != tmp) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

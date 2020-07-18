package com.hi.dhl.algorithms._20.java;

import java.util.Stack;

/**
 * <pre>
 *     author: dhl
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
 *    遍历字符串
 *    遇到左括号，则将其压入栈中
 *    如果遇到右括号：
 *    当前栈为空，直接返回false;
 *    当前右括号对应的左括号，与栈顶元素不相等，直接返回false
 *    循环结束之后，判断栈是否为空，不为空返回false
 *
 * </pre>
 */
public class Solution {
    public boolean isValid(String s) {
        if (s == null) return false;
        Stack<Character> stack = new Stack();
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //遇到左括号，则将其压入栈中
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                //当前栈为空，直接返回false
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                char bracket = '0';
                if (c == ')') {
                    bracket = '(';
                } else if (c == '}') {
                    bracket = '{';
                } else if (c == ']') {
                    bracket = '[';
                }
                //当前右括号对应的左括号，与栈顶元素不相等，直接返回false
                if (top != bracket) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

package com.hi.dhl.algorithms._150.java;

import java.util.Stack;

/**
 * <pre>
 *     author: dhl
 *     desc  : 150. Evaluate Reverse Polish Notation
 *     site: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 *     Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 *     Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 *     Note:
 *
 *     Division between two integers should truncate toward zero.
 *     The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 *     Example 1:
 *
 *     Input: ["2", "1", "+", "3", "*"]
 *     Output: 9
 *     Explanation: ((2 + 1) * 3) = 9
 *     Example 2:
 *
 *     Input: ["4", "13", "5", "/", "+"]
 *     Output: 6
 *     Explanation: (4 + (13 / 5)) = 6
 *     Example 3:
 *
 *     Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 *     Output: 22
 *     Explanation:
 *     ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 *     = ((10 * (6 / (12 * -11))) + 17) + 5
 *     = ((10 * (6 / -132)) + 17) + 5
 *     = ((10 * 0) + 17) + 5
 *     = (0 + 17) + 5
 *     = 17 + 5
 *     = 22
 *
 * </pre>
 */

public class Solution {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                int right = stack.pop();
                int left = stack.pop();
                int result = left + right;
                stack.push(result);
            } else if ("-".equals(tokens[i])) {
                int right = stack.pop();
                int left = stack.pop();
                int result = left - right;
                stack.push(result);
            } else if ("*".equals(tokens[i])) {
                int right = stack.pop();
                int left = stack.pop();
                int result = left * right;
                stack.push(result);
            } else if ("/".equals(tokens[i])) {
                int right = stack.pop();
                int left = stack.pop();
                if (right == 0 || left == 0) {
                    stack.push(0);
                } else {
                    int result = left / right;
                    stack.push(result);
                }

            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    public static void main(String... args) {
        String[] tokens = new String[]{"0", "3", "/"};
        Solution solution = new Solution();
        System.out.println(solution.evalRPN(tokens));
    }
}

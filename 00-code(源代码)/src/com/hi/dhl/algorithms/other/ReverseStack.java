package com.hi.dhl.algorithms.other;

import java.util.Stack;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/23
 *     desc  : 反转栈
 * </pre>
 */
class ReverseStack {

    /**
     * 方法一很简单
     * 使用辅助栈，反转起来很简单，一个接一个push到辅助栈里再push回来就行了
     * <p>
     * 时间复杂度 0(N)，N 为栈的元素个数
     * 空间复杂度 0(N)，N 为栈的元素个数
     * <p>
     * <p>
     * 方法二 递归算法如下：
     * <p>
     * 1. 第一次递归将 栈顶 元素弹出
     * 2. 第二次递归将 弹出的栈顶元素 放入栈底
     * <p>
     * <p>
     * 时间复杂度 0(N)，N 为栈的元素个数
     * 空间复杂度 0(height)，递归的空间复杂度，即递归的深度
     */


    /**
     * 第一次递归将 栈顶 元素弹出
     *
     * @param stack
     */
    void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;

        int top = stack.pop(); // 弹出栈顶元素，暂存在栈中
        reverseStack(stack); // 调用自身，直到所有元素弹出

        addStackBottom(stack, top);// 将栈顶元素 放入栈底
    }


    /**
     * 第二次递归将 弹出的栈顶元素 放入栈底
     *
     * @param stack
     * @param top
     */
    void addStackBottom(Stack<Integer> stack, int top) {
        if (stack.isEmpty()) {
            stack.push(top); // 栈顶元素入栈
            return;
        }

        int item = stack.pop(); // 暂存栈中的元素
        addStackBottom(stack, top);
        stack.push(item); // 将暂存的元素入栈
    }

    public static void main(String... args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        int index = stack.size() - 1;
        while (index >= 0) {
            System.out.println("原栈：" + stack.elementAt(index--));
        }


        ReverseStack reverse = new ReverseStack();
        reverse.reverseStack(stack);

        while (!stack.isEmpty()) {
            System.out.println("反转之后：" + stack.pop());
        }
    }
}

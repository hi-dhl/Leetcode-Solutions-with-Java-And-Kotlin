## 题目描述

一个栈依次压入1,2,3,4,5，那么从栈顶部到栈底部分别为5,4,3,2,1.将这个栈转置后，从栈顶到栈底为1,2,3,4,5.也就是实现栈中元素的逆序，但是只能用递归函数来实现，不能用其他数据结构。

**示例 ：**

```
输入：stack = [1,2,3,4,5], 从栈顶部到栈底部分别为 5,4,3,2,1
输出：从栈顶到栈底分别为 1,2,3,4,5
```

## 思路

**方法一：辅助栈**

使用辅助栈，反转起来很简单，算法步骤如下：

1. 将原栈元素 push 到辅助栈里
2. 之后将辅助栈在 pop 出来即可

复杂度分析：

* 时间复杂度 0(N)，N 为栈的元素个数
* 空间复杂度 0(N)，N 为栈的元素个数

**方法二：递归**

算法步骤如下：

1. 第一次递归将 栈顶 元素弹出
2. 第二次递归将 弹出的栈顶元素 放入栈底

复杂度分析：

* 时间复杂度 0(N)，N 为栈的元素个数
* 空间复杂度：O(height)，递归函数需要栈空间，而栈空间取决于递归的深度

### Java 实现

```
class ReverseStack {

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
```


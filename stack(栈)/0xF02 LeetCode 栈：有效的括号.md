# 0xF02 LeetCode 栈：有效的括号

题目来源于 `LeetCode` 上第 `20` 号问题：有效的括号。题目难度为 `Easy`。

* [英文地址：https://leetcode.com/problems/valid-parentheses](https://leetcode.com/problems/valid-parentheses) 
* [中文地址：https://leetcode-cn.com/problems/valid-parentheses](https://leetcode-cn.com/problems/valid-parentheses) 

## 题目描述
 
给定一个字符串, 只包括 '('，')'，'{'，'}'，'['，']'，判断字符串是否有效

有效字符串需要满足以下条件：

* 左括号必须用相同类型的右括号闭合
* 左括号必须以正确的顺序闭合

注意空字符串可被认为是有效字符串。

```
Example 1:
    Input: "()"
    Output: true

Example 2:
    Input: "()[]{}"
    Output: true

Example 3:
    Input: "(]"
    Output: false

Example 4:
    Input: "([)]"
    Output: false

Example 5:
    Input: "{[]}"
    Output: true
```

### 题目解析

1. 遍历字符串
2. 遇到左括号，则将其对应的右括号压入栈中
3. 如果遇到右括号：
    * 当前栈为空，直接返回false;
    * 当前右括号对应的左括号，与栈顶元素不相等，直接返回false
4. 重复执行 步骤 2 和步骤 3
5. 循环结束之后，判断栈是否为空，不为空返回false

### Kotlin 实现

```
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
```

### Java 实现

```
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
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack 源码相关的文章，在技术的道路上一起前进。

### Android10 源码分析

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### 算法题库的归纳和总结

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### 精选国外的技术文章

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。


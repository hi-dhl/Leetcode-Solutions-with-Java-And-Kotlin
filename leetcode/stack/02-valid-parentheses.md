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

### 算法流程

![](http://img.hi-dhl.com/16315437955394.gif)

1. 如果遇到左括号，将对应的右括号压入栈中
2. 如果遇到右括号
    * 判断当前栈是否为空
    * 如果不为空，判断当前元素是否和栈顶元素相等
    * 如果不相等，发现了不符合的括号，提前返回 `false`，结束循环
3. 重复执行「步骤 1」 和「步骤 2」
4. 循环结束之后，通过判断栈是否为空，来检查是否是有效的括号

**复杂度分析**

假设字符串的长度为 `N` 则：

* 时间复杂度：`O(N)`。正确有效的括号需要遍历了一次字符串，所需要的时间复杂度为 `O(N)`。
* 空间复杂度：`O(N)`。如果输入字符串全是左括号，例如 `(((((((`，栈的大小即为输入字符串的长度，所需要的空间复杂度为 `O(N)`

<!-- tabs:start -->

### **Kotlin 实现**

```
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
```

### **Java 实现**

```
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<Character>();
        // 开始遍历字符串
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
                // 遇到右括号，判断当前元素是否和栈顶元素相等，不相等提前返回，结束循环
                if (stack.isEmpty() || stack.poll() != c) {
                    return false;
                }
            }
        }
        // 通过判断栈是否为空，来检查是否是有效的括号
        return stack.isEmpty();
    }
}
```

<!-- tabs:end -->


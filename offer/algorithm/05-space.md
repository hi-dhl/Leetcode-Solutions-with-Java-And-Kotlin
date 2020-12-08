题目来源于 LeetCode 剑指 offer 第 05 号问题：替换空格。题目难度为 Easy。

* [中文地址：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)

## 题目描述

请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

**示例:**

```
输入：s = "We are happy."
输出："We%20are%20happy."
```

**限制：**

```
0 <= s 的长度 <= 10000
```

## 思路

声明一个 StringBuffer，遍历字符串，如果遇见空格，添加 %20 到 buffer 中, 否则添加当前字符

**复杂度分析：**

* 时间复杂度：O(n)，当一个字符串的长度为 n 时，遍历字符串一遍，时间复杂度为 O(n)
* 空间复杂度：0(n)，需要创建 StringBuffer 有额外的开销，每次遇见空格，就替换为 %20，最坏的情况下，长度是原来的 3 倍

<!-- tabs:start -->

### **Kotlin 实现**

```
class Solution {
    fun replaceSpace(s: String): String {
        val buffer = StringBuffer()
        var i = 0
        val len = s.length
        while (i < len) {
            if (s[i] == ' ') {
                buffer.append("%20")
            } else {
                buffer.append(s[i])
            }
            i++
        }
        return buffer.toString()
    }
}
```

### **Java 实现**

```
class Solution {
    public String replaceSpace(String s) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                buffer.append("%20");
            } else {
                buffer.append(s.charAt(i));
            }
        }
        return buffer.toString();
    }
}
```

<!-- tabs:end -->



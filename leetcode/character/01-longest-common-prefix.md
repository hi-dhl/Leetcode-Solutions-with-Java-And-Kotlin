题目来源于 `LeetCode` 上 第 `14` 号问题：最长公共前缀。题目难度为 `Easy`。

* [英文地址：https://leetcode.com/problems/longest-common-prefix/](https://leetcode.com/problems/longest-common-prefix/) 
* [中文地址：https://leetcode-cn.com/problems/longest-common-prefix/](https://leetcode-cn.com/problems/longest-common-prefix/) 

## 题目描述

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 `""`。

**示例 1：**

```
输入: ["flower","flow","flight"]
输出: "fl"
```

**示例 2：**

```
输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
```

**说明：**

* 所有输入只包含小写字母 a-z 。

## 思路一：

**算法流程如下：**

* 检查当前数组长度是否为 0 ，如果是则返回空字符串 `""`
* 假设第一个字符串为最长公共前缀
* 从前往后遍历所有字符串的每一列，比较第一个字符串相同列上的字符是否相同
* 如果相同则继续对下一列进行比较
* 如果不相同则当前列不再属于公共前缀，返回当前列之前的部分为最长公共前缀

以下图片来自 LeetCode 官方：

![](http://cdn.51git.cn/2020-07-29-15960035241448.jpg)


**复杂度分析：**

* 时间复杂度：0(mn) ，m 是字符串的数量， n 是每个字符串的长度，最坏的情况下，每个字符都比较一遍
* 空间复杂度：O(1)，使用了一些变量，占常数大小的空间

<!-- tabs:start -->

### **Kotlin 实现**

```
class Solution {

    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.size <= 0) {
            return ""
        }

        val str0 = strs[0]
        val len = str0.length
        val count = strs.size

        for (i in 0 until len) {
            val c = str0[i]
            for (j in 1 until count) {
                if (i == strs[j].length || c != strs[j][i]) {
                    return str0.substring(0, i)
                }
            }
        }
        return str0
    }
}
```

### **Java 实现**

```
public class Solution {

    // 方法一
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 0) {
            return "";
        }
        String str0 = strs[0];
        int len = str0.length();
        int count = strs.length;
        for (int i = 0; i < len; i++) {
            char c1 = str0.charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || c1 != strs[j].charAt(i)) {
                    return str0.substring(0, i);
                }
            }
        }
        return str0;
    }
}
```

<!-- tabs:end -->

## 思路二：

**算法流程如下：**

* 检查当前数组长度是否为 0 ，如果是则返回空字符串 `""`
* 假设第一个字符串为最长公共前缀
* 遍历后面字符串和第一个字符串做比较
* 如果不相同则退出循环，返回之前的字符串为最长公共前缀

**复杂度分析：**

* 时间复杂度：0(mn) ，m 是字符串的数量， n 是每个字符串的长度，最坏的情况下，每个字符都比较一遍
* 空间复杂度：O(1)，使用了一些变量，占常数大小的空间

### Java 实现

```
public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 0) {
            return "";
        }
        int count = strs.length;
        String str0 = strs[0];
        for (int i = 0; i < count; i++) {
            int j = 0;
            for (; j < str0.length() && j < strs[i].length(); j++) {
                if (str0.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            str0 = str0.substring(0, j);
        }
        return str0;
    }
}
```


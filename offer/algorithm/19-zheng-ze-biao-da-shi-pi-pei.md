题目来源于 `LeetCode` 剑指 `offer` 第 `19` 号问题： 正则表达式匹配。题目难度为 `Hard`。

* [中文地址：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/](https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/)

## 题目描述

请实现一个函数用来匹配包含 `. ` 和 `*` 的正则表达式。模式中的字符 `.` 表示任意一个字符，而 `*` 表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串 `aaa` 与模式 `a.a` 和 `ab*ac*a` 匹配，但与 `aa.a` 和 `ab*a` 均不匹配。


**示例 1：**

```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```

**示例 2：**

```
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```

**示例 3：**

```
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```

**示例 4：**

```
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
```

**示例 5：**

```
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```

**说明：**

* `s` 可能为空，且只包含从 `a-z` 的小写字母
* `p` 可能为空，且只包含从 `a-z` 的小写字母以及字符 `.` 和 `*`，无连续的 `*`

## 思路：

**注意：**

* 假设输入的字符串 `s = "aab"` 和  `p = "c*a*b"`，其中 `c*` 或者 `a*` 它们是一个整体，例如 `c*` 可以是 0 个 c ，也可以是多个 c。
* 字符 `.*` 可以表示任意字符（0 个或者多个）

**算法流程如下：**

假设主串为 s，模式串为 p，需要关注正则表达式 p 的最后一个字符是谁，它有三种可能：

* `p[j-1] = 正常字符`
* `p[j-1] = '.'`
* `p[j-1] = '*'` 

只需要针对上面三种情况讨论即可

**情况一：p[j-1] = 正常字符**
    
需要看最后一个字符串是否相等:

* 如果相等的话，就要匹配前面的字符串是否相等， 即 `s[i-1] == p[j-1]` 公式为 `dp[i][j] = dp[i-1][j-1]`
* 如果不相等的话，就要看最后一个字符串是 `.` 还是 `*`

**情况二：p[j-1] = '.'** 

字符 `.` 是万能字符，可以直接让它等于 s[i-1]，同情况一处理 `dp[i][j] = dp[i-1][j-1]`

**情况三：p[j-1] = '\*'**

字符 `*` 可以匹配 0 个或多个前面的字符，而是否能取 0 个或者多个字符，需要看前面的 `p[j-2]` 是否等于 `s[i-1]`

*  当 `p[j-2] != s[i-1]`

    如果它们不相等，则最后两个字符废掉了，即 `dp[i][j] = dp[i][j-2]`
    
*  当 `p[j-2] == s[i-1]` 或者 `p[j-2] = '.'`

    字符 `*` 可以匹配 0 个或多个前面的字符，`p[j-2] = '.'` 可以直接让它等于 s[i-1]

    * 取 0 个字符

        例如：`s = aab`, `p = aabb`，虽然 j-2 和 i-1 相等，但是 `dp[i][j-2]` 已经匹配了，直接删除 j-1 和 j-2，即 `dp[i][j] = dp[i][j-2]`
        
    * 取 1 个字符

        例如：`s = aab`, `p = aab`，取1个字符，相当于去掉 p[j-1]，即 `dp[i][j] = dp[i][j-1]`
    
    * 取多个字符

        例如：`s = aabb`, `p = aab`，需要判断 `s = aab` 和 `p = aab` 是否匹配，如果可以匹配，那么 s 后面再加上一个 b 也没关系，因为 `*` 可以变成多个 b，即 `dp[i][j] = dp[i-1][j]`

**总结：**

* 情况一 和 情况二，可以当做一种情况来处理，即

    ```
    val ms = s[i - 1]
    val mp = p[j - 1]
    if (mp == ms || mp == '.') {
        dp[i][j] = dp[i - 1][j - 1]
    }
    ```

* 无论 `p[j-2]` 是否等于 `s[i-1]`，都可以删除掉最后两个字符

    ```
    dp[i][j] = dp[i][j] || dp[i][j - 2]
    ```

* 当 `p[j-2] == s[i-1]` 或者 `p[j-2] = '.'` 可以取 1 个或者多个字符

    ```
    val mpLast = p[j - 2]
    if (ms == mpLast || mpLast == '.') {
        dp[i][j] = dp[i - 1][j] || dp[i][j - 1]
    }
    ```

**初始化**

1. 空串和空正则是匹配的，即 `dp[0][0] = true`
2. 需要初始化第一行，当 `p[i-1]` 为 `*` 可以把 i-2 和 i-1 处的字符删掉，并且只有 [0, i-3] 为 true 才可以让 `dp[0][i] = true`


**复杂度分析：**

M 是字符串 s 的长度， N 是字符串 p 的长度

* 时间复杂度：O(MN) ，需要遍历每个字符进行匹配，即时间复杂度为 O(MN) 
* 空间复杂度：O(MN)，需要建立数组 dp 保存匹配的结果

<!-- tabs:start -->

### **Kotlin 实现**

```
class Solution {
    fun isMatch(s: String, p: String): Boolean {
        val row = s.length
        val colum = p.length
        val dp = Array(row + 1) { BooleanArray(colum + 1) } 
        
        // 处理空字符串的情况
        dp[0][0] = true;
        for (i in 1..colum) {
            if (p[i - 1] == '*' && dp[0][i - 2]) {
                dp[0][i] = true
            }
        }

        for (i in 1..row) {
            for (j in 1..colum) {
                val ms = s[i - 1]
                val mp = p[j - 1]
                // s=abc p=a.c 或者 s=abc p=abc 当第 i 个字符相等，看前 i-1个字符是否相等
                if (mp == ms || mp == '.') {
                    dp[i][j] = dp[i - 1][j - 1]
                } else if (mp == '*') {
                    if (j < 2) continue
                    // s=abc p=abc* 匹配到一个字符，需要去掉 p 中最后一个字符即 dp[i][j - 1]
                    // s=abccc p= abc* 匹配到多个字符，需要去掉 s 中最后一个字符，即 dp[i - 1][j] 
                    val mpLast = p[j - 2]
                    if (ms == mpLast || mpLast == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1]
                    }

                    // s=abc p=abcd* 字符不相等 或者 s=abc p=abcc* 匹配到  0 个字符，需要去掉 p 中最后链两个字符串
                    dp[i][j] = dp[i][j] || dp[i][j - 2]
                }
            }
        }
        return dp[row][colum]
    }
}
```

### **Java 实现**

```
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int row = s.length();
        int colum = p.length();
        boolean[][] dp = new boolean[row + 1][colum + 1];

        // 处理空字符串的情况
        dp[0][0] = true;
        for (int j = 1; j <= colum; j++) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= colum; j++) {
                char ms = s.charAt(i - 1);
                char mp = p.charAt(j - 1);

                // s=abc p=a.c 或者 s=abc p=abc 当第 i 个字符相等，看前 i-1个字符是否相等
                if (ms == mp || mp == '.') {
                    dp[i][j] = dp[i - 1][j - 1];

                } else if (mp == '*') {
                    // 遇到 * 号，则代码 P[m−2]=c 可以重复0次或多次，它们是一个整体 c*

                    if (j < 2) continue;

                    // s=abc p=abc* 匹配到一个字符，需要去掉 p 中最后一个字符即 dp[i][j - 1]
                    // s=abccc p= abc* 匹配到多个字符，需要去掉 s 中最后一个字符，即 dp[i - 1][j] 
                    char mpLast = p.charAt(j - 2);
                    if (mpLast == ms || mpLast == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }

                    // s=abc p=abcd* 字符不相等 或者 s=abc p=abcc* 匹配到  0 个字符，需要去掉 p 中最后链两个字符串
                    dp[i][j] = dp[i][j] || dp[i][j - 2];

                }
            }
        }
        return dp[row][colum];
    }
}
```

<!-- tabs:end -->


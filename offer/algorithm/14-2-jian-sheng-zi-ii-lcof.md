题目来源于 `LeetCode` 剑指 `offer` 第 `14-2` 号问题：剪绳子。题目难度为 `Medium`。和第 [343 号问题（整数拆分）](https://leetcode-cn.com/problems/integer-break/) 相同

* [中文地址：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/](https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/)
* [整数拆分-英文地址：https://leetcode.com/problems/integer-break/](https://leetcode.com/problems/integer-break/) 
* [整数拆分-中文地址：https://leetcode-cn.com/problems/integer-break/](https://leetcode-cn.com/problems/integer-break/) 

## 题目描述

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 `k[0],k[1]...k[m-1]` 。请问 `k[0] * k[1] * ... * k[m-1]` 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例 1：**

```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
```

**示例 2：**

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

**提示：**

* 2 <= n <= 1000

## 思路：

**注意：** 题目说了要做取模运算，凡是这种题，一定要注意精度问题。

一绳子长度为 n，假设这条绳子被分为 2 段或者 3 段。按照经验切分成 3 段比切分成 2 段的乘积要大，但是也有少数情况切分成 2 段比 3 段乘积大。

* 例如 n = 6, 2 段 3x3 比 3 段 2 x 2 x 2 要大

故所以我们列出所有的可能性寻找规律：

| 绳子长度为 n | 计算方式 | 结果 |
| :--- | :--- | :--- |
| 2 | 1 + 1 ------> 1 x 1 | dp[2] = 1 |
| 3 | 1 + 2 ------> 1 x 2 | dp[3] = 2 |
| 4 | 2 + 2 ------> 2 x 2 | dp[4] = 4 |
| 5 | 2 + 3 = 1 + 4 ------> 2 + 3 > 1 + 4 | dp[5] = 6 |
| 6 | 3 + 3 = 2 + 2+ 2 ------> 3 x 3 > 2 x 2 x 2 | dp[6] = 9 |
| 7 | 3 + 4 ------> 3 x dp[7-3] | dp[7] = 12 |
| 8 | 3 + 5 ------> 3 x dp[8-3] | dp[8] = 18 |

当 n >= 7 时可转化为多个短绳（长度 1~6）；其结果满足公式为 dp [n] = (dp[n - 3] * 3) % 1000000007

**复杂度分析：**

* 时间复杂度：`O(N)`，长度为 N 的绳子，需要计算 [1,n] 累计的最大值，故时间复杂度为 `O(N)`
* 空间复杂度：`O(N)`，需要建立长度为 N 的数组，存储每次计算的值，所以空间复杂度为 `O(N)`

### Koltin 实现

```
class Solution {
    fun cuttingRope(n: Int): Int {
        val dp = LongArray(if (n < 7) 8 else n + 1)
        dp[2] = 1
        dp[3] = 2
        dp[4] = 4
        dp[5] = 6
        dp[6] = 9
        dp[7] = 12
        for (i in 8..n) {
            dp[i] = (dp[i - 3] * 3) % 1000000007
        }
        return dp[n].toInt()
    }
}
```

### Java 实现

```
class Solution {
    public int cuttingRope(int n) {
        int m = n;
        if (n < 7) n = 7;

        long[] dp = new long[n + 1];
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        dp[5] = 6;
        dp[6] = 9;
        dp[7] = 12;
        for (int i = 8; i <= n; i++) {
            dp[i] = (dp[i - 3] * 3) % 1000000007;

        }
        return (int) dp[m];
    }
}
```



# 0xF14-1 LeetCode 剑指 offer：剪绳子

题目来源于 `LeetCode` 剑指 `offer` 第 `14-1` 号问题：剪绳子。题目难度为 `Medium`。和第 [343 号问题（整数拆分）](https://leetcode-cn.com/problems/integer-break/) 相同

* [中文地址：https://leetcode-cn.com/problems/jian-sheng-zi-lcof](https://leetcode-cn.com/problems/jian-sheng-zi-lcof)
* [整数拆分-英文地址：https://leetcode.com/problems/integer-break/](https://leetcode.com/problems/integer-break/) 
* [整数拆分-中文地址：https://leetcode-cn.com/problems/integer-break/](https://leetcode-cn.com/problems/integer-break/) 

## 题目描述

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0] * k[1] * ... * k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

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

* 2 <= n <= 58

## 思路：

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

当 n >= 7 ；其结果满足公式为 dp [n] = 3 * dp[n - 3]

**复杂度分析：**

* 时间复杂度：`O(N)`，长度为 N 的绳子，需要计算 [1,n] 累计的最大值，故时间复杂度为 `O(N)`
* 空间复杂度：`O(N)`，需要建立长度为 N 的数组，存储每次计算的值，所以空间复杂度为 `O(N)`

### Koltin 实现

```
class Solution {
    fun cuttingRope(n: Int): Int {
        val dp = IntArray(if (n < 7) 8 else n + 1)
        dp[1] = 1
        dp[2] = 1
        dp[3] = 2
        dp[4] = 4
        dp[5] = 6
        dp[6] = 9
        dp[7] = 12
        for (i in 8..n) {
            dp[i] = 3 * dp[i - 3]
        }
        return dp[n]
    }
}
```

### Java 实现

```
public class Solution {

    public int cuttingRope(int n) {
        int m = n;
        if (n < 7) {
            n = 7;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        dp[5] = 6;
        dp[6] = 9;
        dp[7] = 12;
        for (int i = 8; i <= n; i++) {
            dp[i] = 3 * dp[i - 3];
        }
        return dp[m];
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解 [Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin) 和  Android 10 源码分析 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis) 一起来学习，期待与你一起成长


### Android10-Source-Analysis

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### Leetcode-Solutions-with-Java-And-Kotlin

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### Technical-Article-Translation

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。


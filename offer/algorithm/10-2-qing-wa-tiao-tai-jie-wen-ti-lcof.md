题目来源于 LeetCode 剑指 offer 第 10_2 号问题：青蛙跳台阶问题。题目难度为 Easy。这道题和 [第70号问题：爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/) 一样

* [中文地址：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

## 题目描述

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例1:**

```
输入：n = 2
输出：2
```

**示例2:**

```
输入：n = 7
输出：21
```

**提示：**

    * 0 <= n <= 100

## 思路：

这道题 **斐波那契（Fibonacci**）问题一样，它们在公式稍微一些不同

**斐波那契（Fibonacci**）公式如下所示：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

**青蛙跳台阶** 公式如下所示：

```
f(0)=1 , f(1)=1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

**复杂度分析：**

* 时间复杂度：O(n)，F(N) 需要循环 N 次，因此时间复杂度为 O(n)
* 空间复杂度：O(1)，只是使用了几个标示变量作为额外空间，可以忽略不计，因此空间复杂度 O(1)

### Koltin 实现

```
class Solution {
    fun numWays(n: Int): Int {
        if (n == 1 || n == 0) {
            return 1;
        }

        var a = 1;
        var b = 2;
        var sum = b;
        for (i in 2 until n) {
            sum = (a + b) % 1000000007
            a = b.also { b = sum }
        }
        return sum
    }
}
```

### Java 实现

```
class Solution {
    public int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int a = 1;
        int b = 2;
        int sum = b;
        for (int i = 2; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }
}
```



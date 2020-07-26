# 0xF16 剑指 offer：数值的整数次方

题目来源于 `LeetCode` 剑指 `offer` 第 `16` 号问题：数值的整数次方。题目难度为 `Medium`。和第 [50 号问题](https://leetcode-cn.com/problems/powx-n/) 相同

* [中文地址：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)
* [50 号问题-英文地址：https://leetcode.com/problems/powx-n/](https://leetcode.com/problems/powx-n/) 
* [50 号问题-中文地址：https://leetcode-cn.com/problems/powx-n/](https://leetcode-cn.com/problems/powx-n/) 

## 题目描述

实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。


**示例 1：**

```
输入: 2.00000, 10
输出: 1024.00000
```

**示例 2：**

```
输入: 2.10000, 3
输出: 9.26100
```

**示例 3：**

```
输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
```

**说明：**

* -100.0 < x < 100.0
* n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。

## 思路：

这到题用到了位运算符，我们先来回顾一下：

```
x >> 1 等价于 (x = x / 2)
x << 1 等价于 (x = x * 2)
x & 1  等价于 (x % 2 == 1)
```

以下图片来自 LeetCode 社区

![](http://cdn.51git.cn/2020-07-27-15957785037579.jpg)

**算法流程如下：**

x 表示整数， m 表示平方，res 累计结果

* 当 `x == 0` 时，返回 0 ，避免后续的 `x = 1 / x ` 出错
* 当 `x < 0` 时，需要转为 x >= 0，在这个范围内求解，即 `x = 1 / x; m *= -1`
* 初始化 res 为 1
* 循环计算，当 `m == 0` 时跳出循环
    * 当 `(m & 1) == 1` 时，即 res = res * x
    * 执行 `x = x * x`
    * 将 m 右移 1 位，即 `m = m >> 1`

**注意：**

m 可以取到 -2147483648（整型负数的最小值），执行 `m *= -1;` 会因越界而出错，所以需要将 m 转换成 long 型

**复杂度分析：**

* 时间复杂度：O(log2^n) ，2 分查找时间复杂度为对数级别
* 空间复杂度：O(1)，需要几个变量，占常数大小的空间

### Kotlin 实现

```
class Solution {
    fun myPow(x: Double, n: Int): Double {
        if (x == 0.0) return 0.0
        var x1 = x
        var res = 1.0
        var m = n.toLong()
        if (m < 0) {
            x1 = 1 / x1
            m = -1 * m
        }

        while (m > 0) {
            if ((m % 2).toInt() == 1) res = res * x1
            x1 = x1 * x1
            m = m / 2
        }
        return res
    }
}
```

### Java 实现

```
class Solution {

    // x >> 1 ==> (x = x / 2)
    // x << 1 ==> (x = x * 2)
    // x & 1 ==> (x % 2 == 1)

    public double myPow(double x, int n) {
        if (x == 0) return 0;
        long m = n;
        double res = 1.0;

        if (m < 0) {
            x = 1 / x;
            m *= -1;
        }

        while (m > 0) {
            if ((m & 1) == 1) res = res * x;
            x = x * x;
            m = m >> 1;
        }

        return res;
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



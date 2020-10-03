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



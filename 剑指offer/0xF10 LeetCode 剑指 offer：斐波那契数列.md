# LeetCode 剑指 offer：斐波那契数列

题目来源于 LeetCode 上第 10 号问题：斐波那契数列。题目难度为 Easy。

* [中文地址：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)

## 题目描述

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

**示例 1：**

```
输入：n = 2
输出：1
```

**示例 2：**

```
输入：n = 5
输出：5
```

**提示：**

```
0 <= n <= 100
```

## 思路：

根据 斐波那契数列 公式求解即可，公式如下所示：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2)
```

PS: 需要注意的是，每次计算的结果需要取模

### Kotlin 实现

```
class Solution {
    fun fib(n: Int): Int {
        return when {
            n == 0 -> return 0
            n == 1 -> return 1
            else -> {
                val data = mutableListOf<Int>()
                data.add(0)
                data.add(1)
                for (i in 2..n) {
                    data.add((data[i - 1] + data[i - 2]) % 1000000007)
                }
                return data[n]
            }
        }
    }
}
```

### Java 实现

```
public class Solution {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int[] data = new int[n + 1];
        data[0] = 0;
        data[1] = 1;
        for (int i = 2; i <= n; i++) {
            data[i] = (data[i - 1] + data[i - 2]) % 1000000007;
        }
        return data[n];
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解 [Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin) 和  Android 10 源码分析 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis) 一起来学习，期待与你一起成长



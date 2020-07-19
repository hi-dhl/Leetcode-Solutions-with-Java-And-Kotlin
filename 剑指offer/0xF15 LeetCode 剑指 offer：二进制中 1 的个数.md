# 0xF15 LeetCode 剑指 offer：二进制中 1 的个数

题目来源于 `LeetCode` 剑指 `offer` 第 `15` 号问题：二进制中 1 的个数。题目难度为 `Easy`。和第 [191 号问题（位1的个数）](https://leetcode-cn.com/problems/number-of-1-bits/) 相同

* [中文地址：https://leetcode-cn.com/problems/jian-sheng-zi-lcof](https://leetcode-cn.com/problems/jian-sheng-zi-lcof)
* [191 号问题-英文地址：https://leetcode.com/problems/number-of-1-bits/](https://leetcode.com/problems/number-of-1-bits/) 
* [191 号问题-中文地址：https://leetcode-cn.com/problems/number-of-1-bits/](https://leetcode-cn.com/problems/number-of-1-bits/) 

## 题目描述

编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）

**示例 1：**

```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

**示例 2：**

```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

**示例 3：**

```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

**提示：**

* 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
* 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。

## 思路：位运算：

我们先来回顾一下基本的位运算规则：

**或运算（|）：**

两位同时为 0，结果为 0，否则为 1

```
0 | 0 = 0； 0 | 1 = 1； 1 | 0 = 1； 1 | 1 = 1
```

**与运算符（&）：**

两位同时为 1，结果为 1，否则为 0

```
0 & 0 = 0;  0 & 1 = 0;  1 & 0 = 0;  1 & 1 = 1;
```

**异或运算：**

两个相应位 **值不同** 时，结果为 1，否则为 0.

```
0 ^ 0 = 0；  0 ^ 1 = 1；  1 ^ 0 = 1；  1 ^ 1 = 0；
```

**取反运算符（~）：**

对一个二进制数按位取反，即将 0 变 1，1 变 0。

```
~1 = 0； ~0 = 1；
```

如上所示根据位运算规则，我们可以利用 **与运算符（&）** 来解。

* 1 & 1 = 1
* 1 & 0 = 0

**变量声定义：**

*  result 用来存储累加的结果
*  n 为无符号整数

**我们可以逐位循环判断，算法流程如下：**

* 初始化 result 为 0
* 当 `n == 0` 时即循环结束
* 将 n & 1 的结果，累加到 result 上，故 `result += n & 1`
* n 为无符号整数，所以将 n 无符号右移动 1 位，故 `n = n >>> 1`


**复杂度分析：**

* 时间复杂度：O(log2^n) ，其中 n 代表最高位 1 所在的位数
* 空间复杂度：O(1)，需要一个额外的变量 result 来存储累加结果，result 占了一个常数大小的空间

### Java 实现

```
public class Solution {

    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            result += n & 1;
            n = n >>> 1;
        }
        return result;
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



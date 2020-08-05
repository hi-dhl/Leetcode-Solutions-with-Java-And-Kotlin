# 0xF17 剑指 offer： 打印从1到最大的n位数

题目来源于 `LeetCode` 剑指 `offer` 第 `17` 号问题： 打印从1到最大的n位数。题目难度为 `Easy`。

* [中文地址：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/](https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)

## 题目描述

输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

**示例 1：**

```
输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]
```

**说明：**

* 用返回一个整数列表来代替打印
* n 为正整数

## 思路一：

由于本题要求返回 int 类型数组，相当于默认所有数字都在 int32 整型取值范围内，但是在实际面试过程中，面试官肯定会考大数，也就意味着会越界。

**算法流程如下：**

如果考大数的话，无论 int 还是 long 类型它们的取值范围都是有限的，因此只能使用字符串 String 类型。

result 表示一个整数列表，num 存储生成的字符串，x 表示当前所在位数，假设 n = 2 时，生成 1 ~ 99 的全排列流程如下：

* 从下标 `x = 0` 开始，先固定十位为 0 ~ 9
* `x = x + 1`, 开始递归遍历个位为 0 ~ 9
* 当 `x == n` 时，递归遍历结束

**复杂度分析：**

n 表示需要生成数字的位数

* 时间复杂度：O(10^n) ，递归生成的数字的排列个数为 10^n
* 空间复杂度：O(n)，字符串 num 使用线性排列

### Kotlin 实现

```
class Solution {
    val defNum = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    var index = 0;

    fun printNumbers(n: Int): IntArray {
        val num = CharArray(n)
        var max = 1
        // kotlin 中使用 Math.pow 要求参数都是double类型，所以这里自动生成对应的位数
        for (i in 1..n) {
            max = max * 10;
        }
        val result = IntArray(max - 1)
        dfs(num, result, 0) // 开始递归遍历
        return result;
    }

    fun dfs(num: CharArray, result: IntArray, x: Int) {
        if (x == num.size) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // parstInt 方法删除高位多余的 0
            val res = parstInt(num);
            // 过滤掉第一个数字 0
            if (res > 0) {
                result[index] = res
                index = index + 1
            }
            return;
        }

        for (c in defNum) {
            num[x] = c
            dfs(num, result, x + 1)
        }
    }

    fun parstInt(num: CharArray): Int {
        var sum = 0
        var isNotZero = false
        for (c in num) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // 过滤掉高位多余的 0
            if (!isNotZero) {
                if (c == '0') {
                    continue
                } else {
                    isNotZero = true
                }
            }
            sum = sum * 10 + (c - '0')
        }
        return sum;
    }
}
```

### Java 实现

```
public class Solution {
    char[] defNum = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int index = 0;

    public int[] printNumbers(int n) {
        char[] num = new char[n];
        int[] result = new int[(int) Math.pow(10, n) - 1];
        dfs(num, result, 0);  // 开始递归遍历
        return result;
    }

    public void dfs(char[] num, int[] result, int x) {
        if (x == num.length) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // parstInt 方法删除高位多余的 0
            int res = parseInt(num);
            // 过滤掉第一个数字 0
            if (res > 0) {
                result[index++] = res;
            }
            return;
        }

        for (char c : defNum) {
            num[x] = c;
            dfs(num, result, x + 1);
        }
    }

    public int parseInt(char[] num) {
        int sum = 0;
        boolean isNotZero = false;

        for (char c : num) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // 过滤掉高位多余的 0
            if (!isNotZero) {
                if (c == '0') {
                    continue;
                } else {
                    isNotZero = true;
                }
            }

            sum = sum * 10 + (c - '0');
        }
        return sum;
    }
}
```

## 思路二：

思路二比较简单，题目要求返回 int 类型数组，相当于默认所有数字都在 int32 整型取值范围内。

* 假设 n = 2 时，生成 1 ~ 99，数字范围等价于 [1, 100 -1] , 最大取值相当于 `Math.pow(10, n) - 1` 
* 也可以循环生成最大取值，如下所示

    ```
    var max = 1;
    for (i in 1..n) {
        max = max * 10;
    }
    ```

### Kotlin

```
class Solution {

    fun printNumbers(n: Int): IntArray {
        var max = 1;
        for (i in 1..n) {
            max = max * 10;
        }
        val result = IntArray(max - 1)
        for (i in 1 until max) {
            result[i - 1] = i
        }
        return result;
    }

}
```

### Java

```
public class Solution {

    public int[] printNumbers(int n) {
        int max = 1;
        for (int i = 1; i <= n; i++) {
            max = max * 10;
        }
        int[] result = new int[max - 1];
        for (int i = 1; i < max; i++) {
            result[i - 1] = i;
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



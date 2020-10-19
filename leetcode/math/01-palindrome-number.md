题目来源于 `LeetCode` 上 第 `9` 号问题：回文数。题目难度为 `Easy`。

* [英文地址：https://leetcode.com/problems/palindrome-number](https://leetcode.com/problems/palindrome-number) 
* [中文地址：https://leetcode-cn.com/problems/palindrome-number](https://leetcode-cn.com/problems/palindrome-number) 

## 题目描述

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

**示例 1：**

```
输入: 121
输出: true
```

**示例 2：**

```
输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
```

**示例 2：**

```
输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
```

**进阶：**

* 你能不将整数转为字符串来解决这个问题吗？

## 思路：

**临界情况：**

根据官方给出的示例可以得出以下结论：

* x = -121 时，从右向左读, 为 121-，不是回文数字，所以当 x < 0 时，不是回文数字
* x = 10 时，从右向左读, 为 01，因为最高位不等于 0，不是回文数字，所以当个为数为 0 时，不是回文数字

**变量说明：**

* x：为原始数字
* reverse：为反转后的数字

**算法流程如下：**

当 x = 123321 时，将数字 x 后半部分数字 321 反转为 123 即 reverse = 123，将其与 x 的前半部分数字 123 比较，如果相等那么就是回文数字。

当 x = 12321 时，将数字 x 后半部分数字 321 反转为 123，将反转后的数字 123 除以 10 的结果 reverse = 12 与 x 的前半部分数字 12 比较，如果相等那么就是回文数字。

由此我们可以得出满足以下结论时，即为回文数字

```
x == reverse || x == reverse / 10
```

示例图如下所示：

![](http://cdn.51git.cn/2020-10-20-16031240574658.jpg)


**如何判断反转数字的位数已经达到原始数字位数的一半：**当原始数字 `x <= reverse` 时即达到原始数字位数的一半

**复杂度分析：**

* 时间复杂度：0(log^n) ，n 为数字的长度，只需要遍历 n / 2 次
* 空间复杂度：O(1)，使用了一些变量，占常数大小的空间

### Kotlin 实现

```
class Solution {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0 || (x % 10 == 0 && x > 0)) return false
        return x.isPalindrome1()
    }

    fun Int.isPalindrome1(): Boolean {
        var value = this
        var reverse = 0
        do {
            reverse = reverse * 10 + value % 10
            value = value / 10
        } while (value > reverse)

        return value == reverse || value == reverse / 10
    }
}

```

### Java 实现

```
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x > 0)) return false;

        int reverse = 0;
        do {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        } while (x > reverse);

        return x == reverse || x == reverse / 10;
    }
}
```


# 0xC01 LeetCode二分查找：有效的完全平方数

题目来源于 LeetCode 上第 367 号（Valid Perfect Square）问题：有效的完全平方数。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/valid-perfect-square/](https://leetcode.com/problems/valid-perfect-square/)
* [中文地址：https://leetcode-cn.com/problems/valid-perfect-square/](https://leetcode-cn.com/problems/valid-perfect-square/)

## 题目描述

Given a positive integer num, write a function which returns True if num is a perfect square else False.

**Note:**

Do not use any built-in library function such as sqrt.

**Example 1:**

```
Input: 16
Output: true
```

**Example 2:**

```
Input: 14
Output: false
```

## 思路：二分查找

**什么是完全平方数？[维基百科](https://zh.wikipedia.org/wiki/%E5%B9%B3%E6%96%B9%E6%95%B0)**

数学上，平方数，或称完全平方数，是指可以写成某个整数的平方的数，即其平方根为整数的数。例如，9 = 3 × 3，它是一个平方数。

![](http://cdn.51git.cn/2020-05-04-158857000585332.jpg)

二分法的解题思路大致以下几个步骤：

* 寻找完全平方数 x 的区间范围：[low, height]
* 用二分法在区间 [low, height] 内寻找完全平方数
    * 当 left <= right 时：
    令 mind = (left + right) / 2，square = mind * mind 比较 square 与 x：
        * 如果 square > x，则 height = mind -1。
        * 如果 square < x，则 low = mind + 1。
        * 如果 square == x，即完全平方数为 mind，返回 true。
    * 如果在区间内没有找到，则返回 false。

**如何确定 x 的区间范围：[low, height]？**  

根据上面的概念 **完全平方数** 是某个整数的平方的数，也就是说 **完全平方数 = n *n**，例如，9 = 3 × 3

* 当 x >= 2 时：它的整数平方根一定小于 x / 2 且大于 0，即 0 < a < x / 2
*  当 x =1 时：即 1 / 2 的值为0了，所以为了兼顾 1 的特殊情况，需要将边界设为 x / 2 +1

综合以上两种情况 x 的区间范围：[0, x / 2 + 1]，为了提高效率所以使用了位运算符，即 x/2 等价于 x >>> 1

### Java实现

```
public class Solution {
    public boolean isPerfectSquare(int num) {
        long low = 0;
        long height = (num >>> 1) + 1;
        while (low <= height) {
            long mind = (low + height) >>> 1;
            long square = mind * mind;
            if (square == num) {
                return true;
            } else if (square < num) {
                low = mind + 1;
            } else {
                height = mind - 1;
            }
        }
        return false;
    }
}
```

### Koltin实现

```
class Solution {
    fun isPerfectSquare(num: Int): Boolean {
        var low = 0L
        var height = (num ushr 1).toLong() + 1
        var target = num.toLong()
        while (low <= height) {
            val mind: Long = (low + height) ushr 1
            val square = mind * mind
            when {
                square == target -> return true
                square < target -> low = mind + 1
                else -> height = mind - 1
            }
        }
        return false
    }
}
```

## 结语

致力于分享一系列的Android系统源码、逆向分析、算法相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我，一起来学习，期待与你一起成长


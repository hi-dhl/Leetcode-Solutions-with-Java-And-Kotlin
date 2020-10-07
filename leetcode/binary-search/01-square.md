题目来源于 LeetCode 上第 367 号（Valid Perfect Square）问题：有效的完全平方数。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/valid-perfect-square/](https://leetcode.com/problems/valid-perfect-square/)
* [中文地址：https://leetcode-cn.com/problems/valid-perfect-square/](https://leetcode-cn.com/problems/valid-perfect-square/)

## 题目描述

给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

**说明:** ：不要使用任何内置的库函数，如  sqrt。

**示例 1:**

```
输入：16
输出：True
```

**示例 2:**

```
输入：14
输出：False
```

## 思路：二分查找

**什么是完全平方数？[维基百科](https://zh.wikipedia.org/wiki/%E5%B9%B3%E6%96%B9%E6%95%B0)**

数学上，平方数，或称完全平方数，是指可以写成某个整数的平方的数，即其平方根为整数的数。例如，9 = 3 × 3，它是一个平方数。

![](http://cdn.51git.cn/2020-05-04-158857000585332.jpg)

二分法的解题思路大致以下几个步骤：

* 寻找完全平方数 x 的区间范围：[low, height]
* 用二分法在区间 [low, height] 内寻找完全平方数
    * 当 low <= height 时：
    令 mind = (low + height) / 2，square = mind * mind 比较 square 与 x：
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


# 0xC02【LeetCode-二分查找】X的平方根

题目来源于 LeetCode 上第 69号（Sqrt(x)）问题：X的平方根。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/sqrtx/](https://leetcode.com/problems/sqrtx/)
* [中文地址：https://leetcode-cn.com/problems/sqrtx/](https://leetcode-cn.com/problems/sqrtx/)

## 题目描述
 
Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

**Example 1:**

```
Input: 4
Output: 2
```

**Example 2:**

```
Input: 8
Output: 2
    
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
```

## 思路：二分查找

这道题的思路和 [0xC01【LeetCode-二分查找】有效的完全平方数](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/binarySearch(%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE)/0xC01%E3%80%90LeetCode-%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE%E3%80%91%E6%9C%89%E6%95%88%E7%9A%84%E5%AE%8C%E5%85%A8%E5%B9%B3%E6%96%B9%E6%95%B0.md) 思路相同，大致以下几个步骤

* 寻找平方根 x 的区间范围：[low, height]
* 用二分法在区间 [low, height] 内寻找平方根
    * 当 left <= right 时：
    令 mind = (left + right) / 2，square = mind * mind 比较 square 与 x：
        * 如果 square > x，则 height = mind -1。
        * 如果 square < x，则 low = mind + 1。
        * 如果 square == x，即平方根为 mind，返回 mind。
    * 如果在区间内没有找到，则返回 height。

**如何确定 x 的区间范围：[low, height]？**

* 当 x >= 2 时：它的整数平方根一定小于 x / 2 且大于 0，即 0 < a < x / 2，例如 9 的的平方根是 3，16 的平方根是 4，8 的平方根是 2.82842... 取整数部分即是 2，以此类类推可以将区间范围在次缩小 即 0 < a < x / 4
* 当 x =1 时：即 1 / 4 的值为0了，所以为了兼顾 1 的特殊情况，需要将边界设为 x / 4 +1

综合以上两种情况 x 的区间范围：[0, x / 4 + 1]，为了提高效率所以使用了位运算符，即 x/4 等价于 x >>> 2

### Java实现

```
class Solution {
    public int mySqrt(int x) {
        long low = 0;
        long height = (x >>> 2) + 1;
        long lx = (long) x;
        while (low <= height) {
            long mind = (low + height) >>> 1;
            long square = mind * mind;
            if (square == lx) {
                return (int) mind;
            } else if (square < lx) {
                low = mind + 1;
            } else {
                height = mind - 1;
            }
        }
        return (int)height;
    }
}
```

### Kotlin 实现

```
class Solution {
    fun mySqrt(x: Int): Int {
        var low = 0L
        var height = (x ushr 2).toLong() + 1
        val target = x.toLong()
        while (low <= height) {
            val mind = (low + height) ushr 1
            val square: Long = mind * mind
            when {
                square == target -> return mind.toInt()
                square < target -> low = mind + 1
                else -> height = mind - 1
            }
        }
        return height.toInt()
    }
}
```

## 结语

致力于分享一系列的Android系统源码、逆向分析、算法相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我，一起来学习，期待与你一起成长


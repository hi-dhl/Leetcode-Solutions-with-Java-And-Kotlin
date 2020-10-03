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

二分法的解题思路大致以下几个步骤：

* 寻找平方根 x 的区间范围：[low, height]
* 用二分法在区间 [low, height] 内寻找平方根
    * 当 low <= height 时：
    令 mind = (low + height) / 2，square = mind * mind 比较 square 与 x：
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


# 0xB01【LeetCode-数组】2020 力扣杯：拿硬币

题目来源于 2020 力扣杯！Code Your Future 春季全国编程大赛 01：拿硬币。题目难度为 Easy。

* [中文地址：https://leetcode-cn.com/problems/na-ying-bi/](https://leetcode-cn.com/problems/na-ying-bi/)

## 题目描述

桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。

**示例 1：**

```
输入：[4,2,1]

输出：4

解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。
```

**示例 2：**

```
输入：[2,3,10]

输出：8
```

**限制：**

```
1 <= n <= 4
1 <= coins[i] <= 10
```

## 思路

根据题意每次至少可以拿一枚或者两枚力口币，求拿完所有力扣币的最少次数，根据它的限制条件 **1 <= coins[i] <= 10** 列举可能性找规律

* 当coins[1]时，第一次拿一枚，至少1次
* 当coins[2]时，第一次拿两枚，至少1次
* 当coins[3]时，第一次拿两枚，第二次拿一枚，至少2次
* 当coins[4]时，第一次拿两枚，第二次拿两枚，至少2次
* 当coins[5]时，第一次拿两枚，第二次拿两枚，第三次拿一枚，至少3次
* 当coins[6]时，第一次拿两枚，第二次拿两枚，第三次拿两枚，至少3次
* ......

综合以上情况，可以发现当 i 为偶数时，至少次数 = i/2，当 i 为奇数时，至少次数 = i/2 + i%2，代码如下：

### Java实现

```
class Solution {
    public int minCount(int[] coins) {
        int sum = 0;
        for (int i = 0; i < coins.length; i++) {
            sum += coins[i] / 2;
            if (coins[i] % 2 != 0) {
                sum += 1;
            }
        }
        return sum;
    }
}
```

### Koltin实现

```
class Solution {
    fun minCount(coins: IntArray): Int {
        var sum = 0
        coins.forEach { value ->
            sum += value / 2
            if (value % 2 != 0) sum += 1
        }
        return sum
    }
}
```

## 结语

致力于分享一系列的Android系统源码、逆向分析、算法相关的文章，每篇文章都会反复推敲，如果你同我一样喜欢算法、LeetCode，一起来学习，期待与你一起成长



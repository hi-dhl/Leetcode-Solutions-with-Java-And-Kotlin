题目来源于 LeetCode 上第 744 号（Find Smallest Letter Greater Than Target）问题：寻找比目标字母大的最小字母。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/find-smallest-letter-greater-than-target/](https://leetcode.com/problems/find-smallest-letter-greater-than-target/)

* [中文地址：https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/](https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/)

## 题目描述
 
给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。

在比较时，字母是依序循环出现的。举个例子：

* 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'

**示例：**

```
输入:
letters = ["c", "f", "j"]
target = "a"
输出: "c"

输入:
letters = ["c", "f", "j"]
target = "c"
输出: "f"

输入:
letters = ["c", "f", "j"]
target = "d"
输出: "f"

输入:
letters = ["c", "f", "j"]
target = "g"
输出: "j"

输入:
letters = ["c", "f", "j"]
target = "j"
输出: "c"

输入:
letters = ["c", "f", "j"]
target = "k"
输出: "c"
```

**提示：**

* letters长度范围在[2, 10000]区间内。
* letters 仅由小写字母组成，最少包含两个不同的字母。
* 目标字母target 是一个小写字母。


## 二分查找

从题意分析从有序列表中找比目标字母大的最小字母得知，这题应该使用二分查找，因为二分查找的时间复杂度 O(logn)

但是这题目有个坑，就是题意给的不准确，笔者也提交几次才通过，总结规律如下：

* 当 target < letters[0] 时，即 letters[0] 是比目标字母大的最小字母，返回 letters[0]
* 当 target >= letters[height -1], 即返回 letters[0]

### Java 实现

```
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        int height = letters.length - 1;
        if (target < letters[0] || target >= letters[height]) return letters[0];
        while (low <= height) {
            int mind = (low + height) >>> 1;
            if (letters[mind] <= target) {
                low = mind + 1;
            } else {
                height = mind - 1;
            }
        }
        return letters[low];
    }
}
```

### Koltin 实现

```
class Solution {

    fun nextGreatestLetter(letters: CharArray, target: Char): Char {
        var low = 0
        var height = letters.size - 1
        if (target < letters[0] || target >= letters[height]) {
            return letters[0]
        }

        while (low <= height) {
            val mid = (low + height) ushr 1
            when {
                letters[mid] <= target -> low = mid + 1
                else -> height = mid - 1
            }
        }
        return letters[low]
    }

}
```


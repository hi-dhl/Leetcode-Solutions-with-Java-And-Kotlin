# LeetCode二分查找：寻找比目标字母大的最小字母

题目来源于 LeetCode 上第 744 号（Find Smallest Letter Greater Than Target）问题：寻找比目标字母大的最小字母。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/find-smallest-letter-greater-than-target/](https://leetcode.com/problems/find-smallest-letter-greater-than-target/)

* [中文地址：https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/](https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/)

## 题目描述
 
Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

```
Examples:
    Input:
    letters = ["c", "f", "j"]
    target = "a"
    Output: "c"
    
    Input:
    letters = ["c", "f", "j"]
    target = "c"
    Output: "f"
    
    Input:
    letters = ["c", "f", "j"]
    target = "d"
    Output: "f"
    
    Input:
    letters = ["c", "f", "j"]
    target = "g"
    Output: "j"
    
    Input:
    letters = ["c", "f", "j"]
    target = "j"
    Output: "c"
    
    Input:
    letters = ["c", "f", "j"]
    target = "k"
    Output: "c"

Note:
    1. letters has a length in range [2, 10000].
    2. letters consists of lowercase letters, and contains at least 2 unique letters.
    3. target is a lowercase letter.
```

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

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)，一起来学习，期待与你一起成长
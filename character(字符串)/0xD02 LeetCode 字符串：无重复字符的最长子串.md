# 0xD02 LeetCode 字符串：无重复字符的最长子串

题目来源于 `LeetCode` 上 第 `3` 号问题：最长公共前缀。题目难度为 `Medium`。

* [英文地址：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/) 
* [中文地址：https://leetcode.com/problems/longest-substring-without-repeating-characters/](https://leetcode.com/problems/longest-substring-without-repeating-characters/) 

## 题目描述

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

**示例 1：**

```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2：**

```
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

## 思路一：滑动窗口


**什么是滑动窗口？**

在字符串 s 中使用左右指针，左指针 left，右指针 right，初始化 left = right = 0，不断增加 right 扩大窗口大小，满足一定条件的时候，left 向右移动缩小窗口，表示 **开始枚举下一个字符作为起始位**置。

**算法流程如下：**

以示例一中的字符串 `abcabcbb` 为例，从每一个字符开始的，不包含重复字符的最长子串，列举出所有的可能性，其中括号中表示选中的字符以及最长的字符串：

* 以 `(a)bcabcbb` 开始的最长字符串为 `(abc)abcbb`
* 以 `a(b)cabcbb` 开始的最长字符串为 `a(bca)bcbb`
* 以 `a(b)cabcbb` 开始的最长字符串为 `a(bca)bcbb`
* 以 `abc(a)bcbb` 开始的最长字符串为 `abc(abc)bb`
* 以 `abca(b)cbb` 开始的最长字符串为 `abca(bc)bb`
* 以 `abcab(c)bb` 开始的最长字符串为 `abcab(cb)b`
* 以 `abcabc(b)b` 开始的最长字符串为 `abcabc(b)b`
* 以 `abcabcb(b)` 开始的最长字符串为 `abcabcb(b)`

观察规律，当右指针出现重复字符的时候，更改了左指针的起始位置，继续不断的循环下去，直到所有字符都比较完，满足 **滑动窗口** 的定义，所以我们可以使用 **滑动窗口** 来解决这个问题：

1. 检查当前数组长度是否为 0 ，如果是则返回空字符串 `""`
2. 左指针 left，右指针 right，初始化 left = right = 0
3. 不断增加 right 扩大窗口大小，直到右侧出现了重复字符为止
4. 移除左指针指向的元素，并将左指针向右移动一格，表示 **开始枚举下一个字符作为起始位置**
5. 不断重复 3、4 直到所有字符都比较完

**如何判断重复字符**

可以使用 HashSet 或者 HashMap 来判断是否出现重复字符，HashSet 和 HashMap 采用的时候哈希算法，时间复杂度 0(1)。

**复杂度分析：**

* 时间复杂度：0(n) ，n 是字符串的长度，会将整个字符串遍历一次
* 空间复杂度：O(∣Σ∣)，使用哈希集合来存储出现过的字符，∣Σ∣ 表示字符串中最多可以出现的字符的个数

### Java 实现

```
class Solution {
    /**
     * 方法一：滑动窗口 hashSet
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int r = 0;
        int count = 0;
        int len = s.length();
        Set<Character> hashSet = new HashSet<Character>();
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                hashSet.remove(s.charAt(i - 1));
            }

            while (r < len && !hashSet.contains(s.charAt(r))) {
                hashSet.add(s.charAt(r));
                r++;
            }
            count = Math.max(count, r - i);
        }
        return count;
    }

    /**
     * 方法二：滑动窗口 hashMap
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int left = 0;
        int count = 0;
        int len = s.length();
        Map<Character, Integer> hashMap = new HashMap<Character, Integer>();
        for (int i = 0; i < len; i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                left = Math.max(left, hashMap.get(s.charAt(i)) + 1);
            }
            hashMap.put(s.charAt(i), i);
            count = Math.max(count, i - left + 1);
        }
        return count;
    }
}
```

### Kotlin 实现

```
class Solution {
    // 方法一：滑动窗口 hashSet
    fun lengthOfLongestSubstring(s: String): Int {
        val len = s.length
        if (len <= 0) return 0
        var r = 0
        var count = 0
        val hashSet = mutableSetOf<Char>()
        for (i in 0 until len) {
            if (i != 0) {
                hashSet.remove(s[i - 1])
            }

            while (r < len && !hashSet.contains(s[r])) {
                hashSet.add(s[r])
                r++
            }
            count = Math.max(count, r - i)
        }
        return count
    }

    // 方法二：滑动窗口 hashMap
    fun lengthOfLongestSubstring2(s: String): Int {
        val len = s.length;
        if (len == 0) {
            return 0
        }

        var left = 0
        var count = 0
        val map = mutableMapOf<Char, Int>()
        for (i in 0 until len) {
            if (map.containsKey(s[i])) {
                left = Math.max(left, map.get(s[i])?.let { it + 1 } ?: left)
            }
            map.put(s[i], i)
            count = Math.max(count, i - left + 1)
        }
        return count
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



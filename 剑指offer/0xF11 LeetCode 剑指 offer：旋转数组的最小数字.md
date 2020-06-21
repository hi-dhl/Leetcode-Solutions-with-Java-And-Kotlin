# 0xF11 LeetCode 剑指 offer：旋转数组的最小数字

题目来源于 LeetCode 剑指 offer 第 11 号问题：旋转数组的最小数字。题目难度为 Easy。和 [第154号问题：寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/) 一样

* [中文地址：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

## 题目描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

**示例1:**

```
输入：[3,4,5,1,2]
输出：1
```

**示例2:**

```
输入：[2,2,2,0,1]
输出：0
```

## 思路：二分查找

这道题目给出一个有序数组，之后转换成一个旋转数组作为输入数据，求旋转数组的最小数字。

例如 原数组 [1,2,3,4,5]  转换之后的旋转数组是 [3,4,5,1,2] 也有可能是 [4,5,1,2,3] 等等，旋转数组最小的数字是1。

二分查找不仅能用在有序数组上，而且还能用在减治的问题上，这道题也是减治问题。

用二分查找就首先应该寻找比较点，这是一个旋转数组所以最左边和最右边也就是我们的比较点，那么如何进行选择呢

* 如果用最左边位置 left 和中间位置 mid 的值进行比较是否可以？
    
    例如： 旋转数组 [3, 4, 5, 1, 2] 中的值都比左边大，但是最小的在右边，因此不合适。
   
* 如果用最右边边位置 right 和中间位置 mid 的值进行比较是否可以？ 
    
    例如：旋转数组 [3, 4, 5, 1, 2] 和 [2,3,4,5,1] 或者 [4,5,1,2,3]，最后小的在右边，那么用右边位置和中间位置的元素比较，不断缩小范围去查找
    
    
**复杂度分析：**

* 时间复杂度：O(log_2 N)，使用的二分查找算法，因此时间复杂度为 O(log_2 N)
* 空间复杂度：O(1)，只是使用了几个标示变量作为额外空间，可以忽略不计，因此空间复杂度 O(1)

### Koltin 实现

```
class Solution {
    fun minArray(numbers: IntArray): Int {
        var left = 0
        var right = numbers.size - 1
        while (left < right) {
            val mid = (left + right) ushr 1
            when {
                numbers[mid] > numbers[right] -> left = mid + 1
                numbers[mid] < numbers[right] -> right = mid
                else -> right = right - 1
            }
        }
        return numbers[left]
    }
}
```

### Java 实现

```
class Solution {
    public int minArray(int[] numbers) {

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] > numbers[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return numbers[left];
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


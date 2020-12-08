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

* 时间复杂度：O(log2^N)，使用的二分查找算法，因此时间复杂度为 O(log2^N)
* 空间复杂度：O(1)，只是使用了几个标示变量作为额外空间，可以忽略不计，因此空间复杂度 O(1)

<!-- tabs:start -->

### **Koltin 实现**

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

### **Java 实现**

```
class Solution {
    public int minArray(int[] numbers) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int mid = (left + right) >>> 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right = right - 1;
            }
        }

        return numbers[left];
    }
}
```


<!-- tabs:end -->


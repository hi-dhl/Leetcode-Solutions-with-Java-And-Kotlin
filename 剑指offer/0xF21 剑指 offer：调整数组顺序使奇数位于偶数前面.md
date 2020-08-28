# 0xF21 剑指 offer：调整数组顺序使奇数位于偶数前面

题目来源于 `LeetCode` 剑指 `offer` 第 `21` 号问题： 调整数组顺序使奇数位于偶数前面。题目难度为 `Easy`。

* [中文地址：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof)

## 题目描述

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

**示例 1：**

```
输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
```

**提示：**

* 1 <= nums.length <= 50000
* 1 <= nums[i] <= 10000

## 思路一：双指针 + 位运算

**参数说明：**

* left：左指针
* right：右指针

**算法流程如下：**

* 初始化左右指针指向数组两端
* 遍历数组，当 left >= right 即退出循环
    * 指针 left 从左边开始，遇到奇数 left++，直到遇到偶数停止
    * 指针 right 从右边开始，遇到偶数 right--，直到遇到奇数停止
    * 交换左右指针
* 返回交互后的数组

**常用三种交换算法：**

```
int a = 1;
int b = 2;

// 中间变量
int temp = a;
a = b;
b = temp;

// 加减运算
a = a + b;
b = a - b;
a = a - b;
        
// 位运算
a = a ^ b;
b = a ^ b;
a = a ^ b;
```

**使用位运算判断奇数和偶数：**

```
int a = 2;
a & 1 == 1 // 奇数
a & 1 ！= 1 // 偶数
```

**Kotlin 位运算符**

```
shl(bits) – 左移位，等价于 Java <<
shr(bits) – 右移位，等价于 Java >>
ushr(bits) – 无符号右移位，等价于 Java >>>
and(bits) – 与，等价于 Java &
or(bits) – 或，等价于 Java ||
xor(bits) – 异或，等价于 Java ^
inv() – 反向，等价于 Java ~
```

**复杂度分析：**

* 时间复杂度：O(N) ，为数组 nums 长度 
* 空间复杂度：O(1)，使用常数大小的空间

### Kotlin 实现

```
class Solution {
    /**
     * 左右指针
     */
    fun exchange(nums: IntArray): IntArray {
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            // and == &
            while (left < right && nums[left] and 1 == 1) left++
            while (left < right && nums[right] and 1 != 1) right--

            if (left < right) {
                // xor == ^
                nums[left] = nums[left] xor nums[right]
                nums[right] = nums[left] xor nums[right]
                nums[left] = nums[left] xor nums[right]
            }
        }
        return nums;
    }
}
```

### Java 实现

```
public class Solution {

    /**
     * 左右指针
     */
    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {

            while (left < right && (nums[left] & 1) == 1) left++;
            while (left < right && (nums[right] & 1) != 1) right--;

            if (left < right) {
                nums[left] = nums[left] ^ nums[right];
                nums[right] = nums[left] ^ nums[right];
                nums[left] = nums[left] ^ nums[right];
            }
        }
        return nums;
    }
}
```

## 思路二：快慢指针 + 位运算

**参数说明：**

* show：慢指针
* fast: 快指针
* size: 数组的长度

**算法流程如下：**

* 初始化快慢指针，指向数组开始的位置
* 遍历数组，当 fast > size  即退出循环
    * 快指针 fast 从左边开始，遇到偶数 fast++
    * 如果快指针 fast 遇到奇数，则交换快慢指针，交换后，慢指针 show++
* 返回交互后的数组

**复杂度分析：**

* 时间复杂度：O(N) ，为数组 nums 长度 
* 空间复杂度：O(1)，使用常数大小的空间

### Kotlin 实现

```
class Solution {
    /**
     * 快慢指针
     */
    fun exchange2(nums: IntArray): IntArray {
        var show = 0
        var fast = 0
        val size = nums.size
        while (fast < size) {
            // and == &
            if (nums[fast] and 1 == 1) {
                val tmp = nums[show]
                nums[show] = nums[fast];
                nums[fast] = tmp;
                show++
            }

            fast++
        }
        return nums;
    }
}
```

### Java 实现

```
public class Solution {
    /**
     * 快慢指针
     */
    public int[] exchange2(int[] nums) {

        int low = 0;
        int fast = 0;
        int size = nums.length;
        while (fast < size) {
            if ((nums[fast] & 1) == 1) {
                int tmp = nums[low];
                nums[low] = nums[fast];
                nums[fast] = tmp;
                low++;
            }
            fast++;
        }

        return nums;
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack 源码相关的文章，正在努力写出更好的文章，如果这篇文章对你有帮助给个 star，文章中有什么没有写明白的地方，或者有什么更好的建议欢迎留言，欢迎一起来学习，在技术的道路上一起前进。

### Android10 源码分析

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### 算法题库的归纳和总结

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### 精选国外的技术文章

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。



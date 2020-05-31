# LeetCode数组：两数之和2 - 输入数组有序

题目来源于 LeetCode 上第 167号（Two Sum II - Input array is sorted）问题：两数之和2 - 输入数组有序。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

* [中文地址：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

## 题目描述
 
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

**Note:**

* Your returned answers (both index1 and index2) are not zero-based.
* You may assume that each input would have exactly one solution and you may not use the same element twice.

**示例：**

```
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2
```

## 思路一：左右指针法

初始化左指针low指向数组起始位置，右指针height指向数组结束位置，时间复杂度o(n)

1. 如果numbers[low] 和 numbers[height]的和sum等于target，返回low+,height+1
2. 如果numbers[low] 和 numbers[height]的和sum小于target，则low++
3. 如果numbers[low] 和 numbers[height]的和sum大于target，则height--

### Java实现

```
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int height = numbers.length - 1;
        while (low < height) {
            int sum = numbers[low] + numbers[height];
            if (sum == target) {
                return new int[]{low + 1, height + 1};
            } else if (sum < target) {
                low++;
            } else {
                height--;
            }
        }
        return new int[2];
    }
}
```

### Kotlin实现

```
class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var height = numbers.size - 1
        var low = 0;
        while (low < height) {
            val sum = numbers[low] + numbers[height]
            when {
                sum == target -> return intArrayOf(low + 1, height + 1)
                sum < target -> low++
                else -> height--
            }
        }
        return intArrayOf()
    }
}
```

## 思路二：二分法查找

题目可知找两个数之和 a+b = taget，循环数组每一个元素a, 从剩余的元素里面用二分法查找另外一个元素 b= taget-a 即可，时间复杂度为o(nlogn)

1. 循环每个元素a
2. 从剩余元素用二分法查找元素b = taget-a，返回索引idnex,
3. 如果index ==-1 ，则无结果返回，如果index!=-1，则直接返回a+1,index+1

### Java实现

```
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int a = 0; a < numbers.length; a++) {
            int index = binarySearch(numbers, a + 1, target - numbers[a]);
            if (index != -1) return new int[]{a + 1, index + 1};
        }
        return new int[2];
    }

    public int binarySearch(int[] numbers, int start, int target) {
        int low = start;
        int height = numbers.length - 1;
        while (low <= height) {
            int mind = (low + height) >>> 1;
            if (numbers[mind] == target) return mind;
            if (numbers[mind] < target) low = mind + 1;
            if (numbers[mind] > target) height = mind - 1;
        }
        return -1;
    }
}
    
```

### kotlin 实现

```
class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {

        numbers.forEachIndexed { i, value ->
            val index = binarySearch(numbers, i + 1, target - value)
            if (index != -1) {
                return intArrayOf(i + 1, index + 1)
            }
        }

        return intArrayOf()
    }

    fun binarySearch(numbers: IntArray, start: Int, target: Int): Int {
        var low = start
        var height = numbers.size - 1
        while (low <= height) {
            val mind = (low + height) ushr 1
            when {
                numbers[mind] == target -> return mind
                numbers[mind] < target -> low = mind + 1
                else -> height = mind - 1
            }
        }
        return -1
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)，一起来学习，期待与你一起成长



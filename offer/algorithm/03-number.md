题目来源于 LeetCode 剑指 offer 第 03 号问题：数组中重复的数字。题目难度为 Easy。

* [中文地址：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述

找出数组中重复的数字。

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

**示例 1:**

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```

**限制：**

```
2 <= n <= 100000
```

## 思路一：原地置换法

题目指出 在一个长度为 n 的数组 nums 里的所有数字都在 [0,n-1] 的范围内，可遍历数组并通过交换操作使元素的 索引 与 值，通过索引找到对应的值，算法流程如下：

* 当 nums[i] == i 时，即 值 和 索引，一一对应无需交换
* 当 nums[nums[i]] == nums[i]，即索引 nums[i] 对应的值 和 nums[i] 相等，找到相同的值，返回此值 nums[i]
* 上面条件都不满足，即交换索引为 i 和 nums[i] 的元素值，将数字交换到对应索引位置
* 没有找到返回 -1，即代表数组中无相同值

例如数组 [2,0,1] 交换逻辑如下, 当 i = 0 时：

* temp = num[i], 即 temp = 2
* num[i] = num[temp]，即 num[i] = 1
* num[temp] = temp, 即 num[temp] = 2

将 即 值 和 索引 一一对应

**复杂性分析**

* 时间复杂度 O(N)，N 为数组的长度
* 空间复杂度 O(1), 占用额外常用空间大小


### Java实现

```
public class Solution {

    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == i) continue;
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }
}
```

### Koltin实现

```
class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        for ((index, value) in nums.withIndex()) {
            if (value == index) continue
            if (nums[value] == nums[index]) {
                return nums[index]
            }
            val temp = value
            nums[index] = nums[temp]
            nums[temp] = temp
        }
        return -1
    }
}
```

## 思路二：哈希算法

利用 Haset API 的特性，如果添加重复的元素会返回 false

**复杂性分析**

* 时间复杂度 O(N)，N 为数组的长度
* 空间复杂度 O(N), 不重复的每个元素都可能存入集合，最坏的情况下，每个元素都不重复，所以空间复杂度为 O(N)

### Java 实现

```
public class Solution {

    public int findRepeatNumber2(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < len; i++) {
            if (!set.add(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }
}
```

### Koltin 实现

```
class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        val set = HashSet<Int>()
        for ((index, value) in nums.withIndex()) {
            if (!set.add(value)) {
                return value;
            }
        }
        return -1
    }

}
```


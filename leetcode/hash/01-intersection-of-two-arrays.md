题目来源于 LeetCode 上第 349 号问题：两个数组的交集。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/intersection-of-two-arrays](https://leetcode.com/problems/intersection-of-two-arrays)

* [中文地址：https://leetcode-cn.com/problems/intersection-of-two-arrays](https://leetcode-cn.com/problems/intersection-of-two-arrays)

## 题目描述
 
给定两个数组，编写一个函数来计算它们的交集。

**示例1：**

```
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2]
```

**示例2：**

```
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[9,4]
```

说明：

* 输出结果中的每个元素一定是唯一的。
* 我们可以不考虑输出结果的顺序。

## 思路一：Hash 算法

因为两个数组中都有可能出现重复的元素，所以要保证元素都是唯一的，需要用到哈希集合

**算法流程如下：**

* 新建两个 `HashSet` 集合，分别 `set1` 和 `set2`
* 遍历数组 nums1，将元素添加到 `set1` 中
* 遍历数组 nums2, 如果元素包含在 `set1` 中，将该元素添加在 `set2` 中
* 最后集合 `set2` 就是我们需要的不重复的结果


**复杂度分析：**

* 时间复杂度：0(m + n) ，m 是数组 nums1 的长度，n 是数组 nums2 的长度，遍历两个数组需要的时间复杂度为 0(m + n)
* 空间复杂度：0(m + n)，m 是数组 nums1 的长度，n 是数组 nums2 的长度，需要两个集合存储两个数组中的元素，所以空间复杂度取决于两个集合，即空间复杂度为 0(m + n)

### Java 实现

```
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        for (int value : nums1) {
            set1.add(value);
        }

        Set<Integer> set2 = new HashSet<Integer>();
        for (int value : nums2) {
            if (set1.contains(value)) {
                set2.add(value);
            }
        }

        int[] result = new int[set2.size()];
        int index = 0;
        for (int value : set2) {
            result[index++] = value;
        }
        return result;
    }
}
```

### Kotlin 实现


```
class Solution {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val set1 = HashSet<Int>();
        for (value in nums1) {
            set1.add(value);
        }

        val set2 = HashSet<Int>();
        for (value in nums2) {
            if (set1.contains(value)) {
                set2.add(value);
            }
        }

        val result = IntArray(set2.size);
        var index = 0;
        for (value in set2) {
            result[index++] = value;
        }

        return result;
    }
}
```



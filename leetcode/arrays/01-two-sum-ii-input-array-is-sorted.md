题目来源于 LeetCode 上第 167号（Two Sum II - Input array is sorted）问题：两数之和2 - 输入数组有序。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

* [中文地址：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

## 题目描述
 
给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

**说明:**

* 返回的下标值（index1 和 index2）不是从零开始的。
* 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。

**示例：**

```
输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
```

## 思路一：Hash 算法

* 将数组元素存储在 Map 里
* 循环遍历数组中的元素 `numbers[i]`，计算它与另外一个元素 `value` 之和等于 target，即 `int value = target - numbers[i]`
* 检查 Map 中是否包含 ` value`，如果包含返回两个元素对应的下标

```
public int[] twoSum(int[] numbers, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
        int value = target - numbers[i];
        if (map.containsKey(value)) {
            return new int[]{i + 1, map.get(value) + 1};
        }
        map.put(numbers[i], i);
    }
    return new int[2];
}
```

**复杂度分析：**

* 时间复杂度：0(n) ，n 是数组元素的个数
* 空间复杂度：0(n)，n 是数组元素的个数，需要额外的空间存储数组中的元素

## 思路二：左右指针法

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

## 思路三：二分法查找

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
            if (numbers[mind] == target) {
                return mind;
            } else if (numbers[mind] < target) {
                low = mind + 1;
            } else {
                height = mind - 1;
            }
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


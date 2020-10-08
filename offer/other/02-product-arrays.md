题目来源于 `Uber` 的面试题：计算数组的乘积。

## 题目描述

给定一个整数数组，返回一个新数组，使新数组下标 `i` 处的每个元素，都是原始数组中除第 `i` 处数字外的所有数字的乘积。

**示例 1：**

```
输入：[1, 2, 3, 4, 5]
输出：[120, 60, 40, 30, 24]
```

**示例 2：**

```
输入：[3, 2, 1]
输出：[2, 3, 6]
```

## 思路

**题意分析：**

假设原始数组为： `[3, 2, 1]`，新数组计算规则如下所示：

* array[0] = 2 * 1
* array[1] = 3 * 1
* array[2] = 3 * 2

所以最后新数组为：`[2, 3, 6]`。

**解题思路：**

我们可以用一个变量 `sum` 记录原始数组所有数字的乘积，例如原始数组： `[3, 2, 1]` 所有数字的乘积 `sum = 3 * 2 * 1 = 6`，那么新数组计算规则如下所示：

* array[0] = sum / array[0] = 6 / 3 = 2
* array[1] = sum / array[1] = 6 / 2 = 3
* array[2] = sum / array[2] = 6 / 1 = 6

因此可以得出一个公式：`array[i] = sum / array[i]`

**特殊情况：**

上面的公式：`array[i] = sum / array[i]` 适合数组中不包含 0 的情况，假设数组中包含 0，那么需要分两种情况来讨论：

* 数组中包含 0 的个数等于 1 的情况
* 数组中包含 0 的个数大于等于 2 的情况

**数组中包含 0 的个数等于 1 的情况：**

假设原始数组为：`[3, 0, 1]`，新数组计算规则如下所示：

* array[0] = 0 * 1
* array[1] = 3 * 1
* array[2] = 3 * 0

所以最后新数组为：`[0, 3, 0]`，当数组中包含 0 的个数等于 1 的时候，除了数字为 0 的元素，其他元素都为 0


**数组中包含 0 的个数大于等于 2 的情况：**

假设原始数组为：`[3, 0, 1, 0]`，新数组计算规则如下所示：

* array[0] = 0 * 1 * 0
* array[1] = 3 * 1 * 0
* array[2] = 3 * 0 * 0
* array[3] = 3 * 0 * 1

所以最后新数组为：`[0, 0, 0，0]`，当数组中包含 0 的个数大于等于 2 的时候，新数组中的元素都为 0 

**复杂度分析：**

* 时间复杂度： `0(n + m)`，n 表示第一次遍历数组，m 表示第二次填充数组
* 空间复杂度：`O(1)`，使用了几个变量，占用常数大小的空间

### Kotlin 实现

```
class Solution {

    fun main(args: Array<String>) {

//        测试用例
//    val array = intArrayOf(1, 2, 3, 4, 5) // 120, 60, 40, 30, 24
//    val array = intArrayOf(3, 2, 1) // 2, 3, 6
//    val array = intArrayOf(3, 0, 1)
        val array = intArrayOf(3, 0, 1, 0)

        productArrays(array)
        for (i in 0 until array.size) {
            print(" ${array[i]} ")
        }
    }

    fun productArrays(array: IntArray) {
        var sum = 1
        var countZeros = 0
        val len = array.size

        for (item in 0 until len) {
            if (array[item] == 0) {
                countZeros++
            } else {
                sum = sum * array[item]
            }
        }

        for (i in 0 until len) {

            when {
                countZeros >= 2 -> array[i] = 0
                countZeros == 1 -> {
                    if (array[i] != 0) {
                        array[i] = 0
                    } else {
                        array[i] = sum
                    }
                }
                else -> array[i] = sum / array[i]
            }

        }
    }

}
```


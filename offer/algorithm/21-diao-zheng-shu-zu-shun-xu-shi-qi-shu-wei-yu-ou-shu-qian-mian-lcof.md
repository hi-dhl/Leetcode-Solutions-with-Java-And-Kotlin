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

<!-- tabs:start -->

### **Kotlin 实现**

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

### **Java 实现**

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

<!-- tabs:end -->

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

<!-- tabs:start -->

### **Kotlin 实现**

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

### **Java 实现**

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

<!-- tabs:end -->

### 拓展题目

在上面题目中增加一点难度，题目如下：

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分，且相对位置不变

**示例 1：**

```
输入：nums = [1, 6, 3, 2, 5, 4, 7, 8, 9]
输出：[1, 3, 5, 7, 9, 6, 2, 4, 8] 
```

这道题目类似于插入排序，我们先看一下插入排序算法

```
void insertSort(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
        int temp = nums[i];
        int j = i - 1;
        while (j >= 0 && nums[j] > temp) {
            nums[j + 1] = nums[j];
            j--;
        }
        nums[j + 1] = temp;
    }
}
```

这道题目重点在于「 奇数位于偶数前面，**且相对位置不变** 」，核心思路如下：

* 遍历数组，记录第一次出现偶数的位置，记为 i
* 接着从上一次偶数位置，继续遍历寻找奇数出现的位置，记录当前下标 j 和元素 temp
* 移动数组 [i+1,j] 范围内的元素，统一向右移动 1 位
* 将元素 temp 放到下标为 i 的位置上

```
public int[] exchange(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] % 2 != 0) continue;

        int temp = nums[i];
        int evenIndex = i;

        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] % 2 != 0) {
                temp = nums[j];
                evenIndex = j;
                break;
            }
        }

        for (int k = evenIndex; k > i; k--) {
            nums[k] = nums[k - 1];
        }

        nums[i] = temp; 
    }
    return nums;
}
```


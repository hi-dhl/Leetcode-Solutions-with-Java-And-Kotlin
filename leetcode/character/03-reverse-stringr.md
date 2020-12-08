题目来源于 `LeetCode` 上第 `344` 号问题：反转字符串。题目难度为 `Easy`。

* [英文地址：https://leetcode.com/problems/reverse-string](https://leetcode.com/problems/reverse-string) 
* [中文地址：https://leetcode-cn.com/problems/reverse-string](https://leetcode-cn.com/problems/reverse-string) 

## 题目描述

编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

**示例 1：**

```
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
```

**示例 2：**

```
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
```

## 思路：左右指针

**参数说明：**

* 左指针：`i`
* 右指针：`j`

**算法流程如下：**

输入的字符串是一个字符数组，反转字符串问题，常用的算法：左右指针，流程如下所示：

* 初始化左右指针指向数组两端，`i` 指向数组起始位置，`j` 指向数组结束位置
* 当 `i` <= `j`，遍历数组：
    * 使用交换算法交换 `s[i]` 和 `s[j]`
    * 左指针 向 右移动一位，`i++`
    * 右指针 向 左移动一位，`j--`

**Java 常用三种交换算法：**

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

**Kotlin 交换算法**

```
int a = 1;
int b = 2;
a = b.also { b = a }
println("a = ${a} b = ${b}") // a = 2 b = 1
```

其实这里用到了 `T.also` 函数的两个特点。

* 调用 `T.also` 函数返回的是调用者本身。
* 在使用之前可以进行自我操作。

也就是说 `b.also { b = a }` 会先将 a 的值 (1) 赋值给 b，此时 b 的值为 1，然后将 b 原始的值（2）赋值给 a，此时 a 的值为 2，实现交换两个变量的目的。

**复杂度分析：**

* 时间复杂度：`0(n)` ，n 是数组的长度，至少需要遍历 `n/2` 次，在时间复杂度计算中，可以忽略常数项，故时间复杂度为 `0(n)`
* 空间复杂度：`O(1)`，使用了几个变量，占常数大小的空间

<!-- tabs:start -->

### **Kotlin 实现**

```
class Solution {
    fun reverseString(s: CharArray): Unit {
        var i = 0;
        var j = s.size - 1
        while (i < j) {
            s[i] = s[j].also { s[j] = s[i] }
            i++
            j--
        }
    }
}
```

### **Java 实现**

```
class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 0) return;
        int i = 0;
        int j = s.length - 1;
        while (i <= j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}
```

<!-- tabs:end -->


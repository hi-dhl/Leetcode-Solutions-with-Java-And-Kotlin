题目来源于 `LeetCode` 剑指 `offer` 第 `17` 号问题： 打印从1到最大的n位数。题目难度为 `Easy`。

* [中文地址：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/](https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)

## 题目描述

输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

**示例 1：**

```
输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]
```

**说明：**

* 用返回一个整数列表来代替打印
* n 为正整数

## 思路一：

由于本题要求返回 int 类型数组，相当于默认所有数字都在 int32 整型取值范围内，但是在实际面试过程中，面试官肯定会考大数，也就意味着会越界。

**算法流程如下：**

如果考大数的话，无论 int 还是 long 类型它们的取值范围都是有限的，因此只能使用字符串 String 类型。

result 表示一个整数列表，num 存储生成的字符串，x 表示当前所在位数，假设 n = 2 时，生成 1 ~ 99 的全排列流程如下：

* 从下标 `x = 0` 开始，先固定十位为 0 ~ 9
* `x = x + 1`, 开始递归遍历个位为 0 ~ 9
* 当 `x == n` 时，递归遍历结束

**复杂度分析：**

n 表示需要生成数字的位数

* 时间复杂度：O(10^n) ，递归生成的数字的排列个数为 10^n
* 空间复杂度：O(n)，字符串 num 使用线性排列

<!-- tabs:start -->

### **Kotlin 实现**

```
class Solution {
    val defNum = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    var index = 0;

    fun printNumbers(n: Int): IntArray {
        val num = CharArray(n)
        var max = 1
        // kotlin 中使用 Math.pow 要求参数都是double类型，所以这里自动生成对应的位数
        for (i in 1..n) {
            max = max * 10;
        }
        val result = IntArray(max - 1)
        dfs(num, result, 0) // 开始递归遍历
        return result;
    }

    fun dfs(num: CharArray, result: IntArray, x: Int) {
        if (x == num.size) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // parstInt 方法删除高位多余的 0
            val res = parstInt(num);
            // 过滤掉第一个数字 0
            if (res > 0) {
                result[index] = res
                index = index + 1
            }
            return;
        }

        for (c in defNum) {
            num[x] = c
            dfs(num, result, x + 1)
        }
    }

    fun parstInt(num: CharArray): Int {
        var sum = 0
        var isNotZero = false
        for (c in num) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // 过滤掉高位多余的 0
            if (!isNotZero) {
                if (c == '0') {
                    continue
                } else {
                    isNotZero = true
                }
            }
            sum = sum * 10 + (c - '0')
        }
        return sum;
    }
}
```

### **Java 实现**

```
public class Solution {
    char[] defNum = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int index = 0;

    public int[] printNumbers(int n) {
        char[] num = new char[n];
        int[] result = new int[(int) Math.pow(10, n) - 1];
        dfs(num, result, 0);  // 开始递归遍历
        return result;
    }

    public void dfs(char[] num, int[] result, int x) {
        if (x == num.length) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // parstInt 方法删除高位多余的 0
            int res = parseInt(num);
            // 过滤掉第一个数字 0
            if (res > 0) {
                result[index++] = res;
            }
            return;
        }

        for (char c : defNum) {
            num[x] = c;
            dfs(num, result, x + 1);
        }
    }

    public int parseInt(char[] num) {
        int sum = 0;
        boolean isNotZero = false;

        for (char c : num) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // 过滤掉高位多余的 0
            if (!isNotZero) {
                if (c == '0') {
                    continue;
                } else {
                    isNotZero = true;
                }
            }

            sum = sum * 10 + (c - '0');
        }
        return sum;
    }
}
```

<!-- tabs:end -->

## 思路二：

思路二比较简单，题目要求返回 int 类型数组，相当于默认所有数字都在 int32 整型取值范围内。

* 假设 n = 2 时，生成 1 ~ 99，数字范围等价于 [1, 100 -1] , 最大取值相当于 `Math.pow(10, n) - 1` 
* 也可以循环生成最大取值，如下所示

    ```
    var max = 1;
    for (i in 1..n) {
        max = max * 10;
    }
    ```

<!-- tabs:start -->

### **Kotlin 实现**

```
class Solution {

    fun printNumbers(n: Int): IntArray {
        var max = 1;
        for (i in 1..n) {
            max = max * 10;
        }
        val result = IntArray(max - 1)
        for (i in 1 until max) {
            result[i - 1] = i
        }
        return result;
    }

}
```

### **Java 实现**

```
public class Solution {

    public int[] printNumbers(int n) {
        int max = 1;
        for (int i = 1; i <= n; i++) {
            max = max * 10;
        }
        int[] result = new int[max - 1];
        for (int i = 1; i < max; i++) {
            result[i - 1] = i;
        }
        return result;
    }

}
```

<!-- tabs:end -->



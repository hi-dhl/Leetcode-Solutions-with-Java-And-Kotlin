# 0xF26 剑指 offer：树的子结构

题目来源于 `LeetCode` 剑指 `offer` 第 `26` 号问题：树的子结构。

* [中文地址：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof)

## 题目描述

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

```
     3
    / \
   4   5
  / \
 1   2
```

给定的树 B：

```
   4 
  /
 1
```

返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

**示例 1：**

```
输入：A = [1,2,3], B = [3,1]
输出：false
```

**示例 2：**

```
输入：A = [3,4,5,1,2], B = [4,1]
输出：true
```

**限制：**

```
0 <= 节点个数 <= 10000
```

## 思路：

**如何判断两颗的节点是否相等：**

定义一个递归函数 `isSub(TreeNode A, TreeNode B)` 判断两颗树的节点是否相同

递归函数 `isSub` 的终止条件：

* 如果 B 为空，说明每个节点都相等，说明是 A 的子树，返回 true
* 如果 B 不为空 A 为空 说明 B 不是 A 的子树，返回 false
* 如果两个节点值不相等，说明 B 不是 A 的子树，返回 false

递归函数 isSub 的返回值：判断两颗树的左右子树是否相等，即 `isSub(A.left, B.left) && isSub(A.right, B.right)`

**算法流程如下：**

要判断 B 是否是 A 的子结构，需要从两面考虑：

* B 有可能是 A 的子结构，即从 根节点开始遍历
* B 也有可能是 A 左子树的子结构 或者 右子树的子结构

所以我们需要从上面两个方面判断 B 是否是 A 的子结构，流程如下：

* 从根节点开始判断，通过函数 `isSub` 比较他的每一个子节点即可
* 然后继续判断 B 是不是 A 左子树的子结构和右子树的子结构

**复杂度分析：**

* 时间复杂度 `O(MN)` ，其中 `M,N` 分别为树 A 和树 B 的节点数量
* 空间复杂度 `O(M)` ， 当 `M ≤ N` 时，遍历树 A 与递归判断的总递归深度为 M ；当 `M>N` 时，最差情况为遍历至树 A 叶子节点，此时总递归深度为 M

### Kotlin 实现

```
class Solution {
    fun isSubStructure(A: TreeNode?, B: TreeNode?): Boolean {
        // 只要其中一个为空，返回 false 不是 A 的子树
        if (A == null || B == null) return false

        val sub = isSub(A, B) // 从根节点开始遍历
                || isSubStructure(A.left, B) // 从左子树开始遍历
                || isSubStructure(A.right, B) // 从右子树开始遍历
        return sub
    }

    fun isSub(A: TreeNode?, B: TreeNode?): Boolean {
        // 如果 B 为空，说明每个节点都相等，说明是 A 的子树
        if (B == null) return true

        // 如果 B 不为空 A 为空 说明不是 A 的子树
        // 如果两个节点值不相等，说明也不是 A 的子树
        if (A == null || A.`val` != B.`val`) return false

        return isSub(A.left, B.left) && isSub(A.right, B.right);
    }
}
```

### Java 实现

```
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 只要其中一个为空，返回 false 不是 A 的子树
        if (A == null || B == null) return false;

        boolean sub = isSub(A, B) // 从根节点开始遍历
                || isSubStructure(A.left, B) // 从左子树开始遍历
                || isSubStructure(A.right, B); // 从右子树开始遍历

        return sub;
    }

    public boolean isSub(TreeNode A, TreeNode B) {
        // 如果 B 为空，说明每个节点都相等，说明是 A 的子树
        if (B == null) return true;

        // 如果 B 不为空 A 为空 说明不是 A 的子树
        // 如果两个节点值不相等，说明也不是 A 的子树
        if (A == null || A.val != B.val) return false;

        return isSub(A.left, B.left) && isSub(A.right, B.right);
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



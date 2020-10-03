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



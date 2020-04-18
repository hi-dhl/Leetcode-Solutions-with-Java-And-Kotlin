# 0xA08 LeetCode二叉树：判断二叉树是不是平衡二叉树

> 访问英文地址需要科学上网

题目来源于 LeetCode 上第 110 号（Balanced Binary Tree）问题：判断二叉树是不是平衡二叉树。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/balanced-binary-tree/](https://leetcode.com/problems/balanced-binary-tree/)

* [中文地址：https://leetcode-cn.com/problems/balanced-binary-tree/](https://leetcode-cn.com/problems/balanced-binary-tree/)

## 题目描述

GGiven a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

>a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
>一个二叉树其中每个节点的两个子树的深度差不相差超过1

**Example 1:**

Given the following tree [3,9,20,null,null,15,7]:

```
    3
   / \
  9  20
    /  \
   15   7
```

Return true.

**Example 1:**

Given the following tree [1,2,2,3,3,null,null,4,4]:

```
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
```

Return false.

## 思路:

平衡二叉树: 左右两个子树的深度差的绝对值不超过 1

任意一个节点的深度是由它左右两个子节点的深度决定的, 如果左右两个节点的最大深度分别为L1和R1，任意一个节点的差都满足 **abs(L1 - R1)<=1**，那么就是平衡二叉树

### Java实现

```
public boolean isBalanced(TreeNode root) {
    return maxDepth(root) != -1;
}

public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
        return -1;
    }

    return Math.max(left, right) + 1;
}
```

### Koltin尾递归实现

```
fun isBalanced(root: TreeNode?): Boolean {
    return maxDepth(root) != -1
}

tailrec fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0;
    }

    val left = maxDepth(root.left);
    val right = maxDepth(root.right);
    return if (left == -1 || right == -1 || Math.abs(left - right) > 1) -1 else Math.max(left, right) + 1
}
```

## 结语

致力于分享一系列的Android系统源码、逆向分析、算法相关的文章，每篇文章都会反复推敲，如果你同我一样喜欢算法、LeetCode，一起来学习，期待与你一起成长


# 0xA06 LeetCode二叉树：计算二叉树的最大深度 

> 访问英文地址需要科学上网

题目来源于 LeetCode 上第 104 号（Maximum Depth of Binary Tree）问题：计算二叉树的最大深度。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/maximum-depth-of-binary-tree/](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

* [中文地址：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)

## 题目描述

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Note:** A leaf is a node with no children.

**Example:**

Given binary tree [3,9,20,null,null,15,7],

```
    3
   / \
  9  20
    /  \
   15   7
```

return its depth = 3.

## 思路: 

二叉树的最大深度: 从根节点到叶子节点的最长路径上的节点个数
**注意：叶子节点即没有左右子节点**

任意一个节点的深度是由它左右两个子节点的深度决定的，如果左右两个子节点的深度分别为L1和R1，当前节点的最大深度就是 **max(L1,R1)+1**，公式如下：

maxDepth(root) = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1

### Java实现

```
public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }

    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}
```

### Koltin尾递归实现

```
tailrec fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1
}
```

## 结语

致力于分享一系列的Android系统源码、逆向分析、算法相关的文章，每篇文章都会反复推敲，如果你同我一样喜欢算法、LeetCode，一起来学习，期待与你一起成长



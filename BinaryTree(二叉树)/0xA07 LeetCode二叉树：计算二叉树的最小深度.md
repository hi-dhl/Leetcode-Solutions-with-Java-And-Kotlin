# LeetCode二叉树：计算二叉树的最小深度 

题目来源于 LeetCode 上第 111 号（Minimum Depth of Binary Tree）问题：计算二叉树的最小深度。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/minimum-depth-of-binary-tree/](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

* [中文地址：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

## 题目描述

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

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

return its minimum depth = 2.

## 思路:

二叉树的最小深度: 从根节点到叶子节点的最短路径上的节点个数
**注意：叶子节点即没有左右子节点**

任意一个节点的深度是由它左右两个子节点的深度决定的，如果左右两个子节点的深度分别为L1和R1，当前节点的最小深度就是 **min(L1,R1)+1**，公式如下：

minDepth(root) = Math.min(minDepth(root.left), minDepth(root.right)) + 1

但是需要注意的是不能只判断左右两个子节点的深度的最小值，因为即使某个节点不是叶子节点，但是它的一个子节点是空节点，那么递归就会返回，从而误判这条路径是最短的，例如：

```
    1
   / 
  2  
```

如果只是判断左右两个子节点的深度的最小值，那么返回结果是1，但是实际上这个二叉树的最小深度是2

### Java实现

```
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (left == 0 || right == 0) {
        return left + right + 1;
    } else {
        return Math.min(left, right) + 1;
    }
}
```

### Koltin尾递归实现

```
tailrec fun minDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0;
    }

    val left = minDepth(root.left)
    val right = minDepth(root.right)
    return if (left == 0 || right == 0) left + right + 1 else Math.min(left, right) + 1
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解 [Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin) 和  Android 10 源码分析 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)，一起来学习，期待与你一起成长



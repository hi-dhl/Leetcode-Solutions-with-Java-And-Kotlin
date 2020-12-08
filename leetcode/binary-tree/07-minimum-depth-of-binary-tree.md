题目来源于 LeetCode 上第 111 号（Minimum Depth of Binary Tree）问题：计算二叉树的最小深度。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/minimum-depth-of-binary-tree/](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

* [中文地址：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

## 题目描述

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**

给定二叉树 [3,9,20,null,null,15,7],

```
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最小深度  2.

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

**复杂度分析：**

* 时间复杂度：O(n)，n 为节点的数量
* 空间复杂度：O(height)，height 为树的高度，递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度

<!-- tabs:start -->

### **Java 实现**

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

### **Koltin 实现**

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


<!-- tabs:end -->


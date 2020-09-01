# 0xF27 剑指 offer：二叉树的镜像

题目来源于 `LeetCode` 剑指 `offer` 第 `27` 号问题：二叉树的镜像。和第 [226 号题](https://leetcode-cn.com/problems/invert-binary-tree/) 相同

* [中文地址：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof](https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof)

## 题目描述

请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入：

```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

镜像输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

**示例 1：**

```
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
```

**限制：**

```
0 <= 节点个数 <= 1000
```

## 思路：递归 和 非递归

**算法流程如下：**

后续遍历的特点 左 -> 右 -> 根

根据后续遍历 **递归** 和 **非递归** 的方式，交换左右节点的顺序即可

**复杂度分析：**

* 时间复杂度 `O(N)` ，其中 `N` 为二叉树的节点数量
* 空间复杂度 `O(N)` ， 最差情况下（当二叉树退化为链表）

### Kotlin 实现

```
class Solution {

    /**
     * 根据后续遍历的特点 左 -> 右 -> 根，交换左右节点的顺序即可
     */
    fun mirrorTree(root: TreeNode?): TreeNode? {
        if (root == null) return root

        mirrorTree(root.left)
        mirrorTree(root.right)
        val tmp = root.left
        root.left = root.right
        root.right = tmp
        return root
    }

    /**
     * 利用 后续遍历 非递归的方式，交换左右节点顺序
     */
    fun mirrorTree2(root: TreeNode?): TreeNode? {
        if (root == null) return root

        val stack = Stack<TreeNode>()
        stack.push(root)
        while (!stack.isEmpty()) {
            val node = stack.pop()
            if (node.left != null) stack.push(node.left)
            if (node.right != null) stack.push(node.right)

            val tmp = node.left
            node.left = node.right
            node.right = tmp
        }
        return root
    }
}
```

### Java 实现

```
class Solution {

    /**
     * 根据后续遍历的特点 左 -> 右 -> 根，交换左右节点的顺序即可
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;

        mirrorTree(root.left);
        mirrorTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }


    /**
     * 利用 后续遍历 非递归的方式，交换左右节点顺序
     */
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) return root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
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



# LeetCode二叉树：从前序与中序遍历序列构造二叉树

题目来源于 LeetCode 上第 105号（Construct Binary Tree from Preorder and Inorder Traversal）问题：从前序与中序遍历序列构造二叉树。题目难度为 Medium。

* [英文地址：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

* [中文地址：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

## 题目描述

Given preorder and inorder traversal of a tree, construct the binary tree.

**Note:**
You may assume that duplicates do not exist in the tree.

For example, given

```
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
```

Return the following binary tree:

```
    3
   / \
  9  20
    /  \
   15   7
```

## 思路：

如果还不了解前序遍历和中序遍历算法可以点击下方链接前往

[前序遍历](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA01%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%20%E5%89%8D%E5%BA%8F%E9%81%8D%E5%8E%86.md)
[中序遍历](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin/blob/master/BinaryTree(%E4%BA%8C%E5%8F%89%E6%A0%91)/0xA02%20LeetCode%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E4%B8%AD%E5%BA%8F%E9%81%8D%E5%8E%86.md)

前序遍历和中序遍历过程如下：

* 前序遍历：根结点 ---> 左节点 ---> 右节点
* 中序遍历：左节点---> 根结点 ---> 右节点

根据前序遍历的规则，可知 preorder[0] 一定是整棵二叉树的根节点，如果根节点所在的下标为 index，根据中序遍历的规则，可知中序序列 inorder：

* 区间 inorder[0, index - 1] 属于根节点的左子树
* 区间 inorder[index + 1, n - 1] 属于根节点的右子树

根据前序遍历的规则一定是先遍历完左子树，然后才会遍历右子树，所以前序序列 preorder：

* 区间 preorder[1, index] 在根节点的 preorder[0] 的左边
* 区间 preorder[index+1, n] 在根节点的 preorder[0] 的右边

所以算法思路如下：

* 根节点是Preorder数组中的第一个元素，即 preorder[0]
* 在Inorder数组中查找根的位置 index
* 在Inorder数组中，根元素左边的元素是左子树，即区间 inorder[0, index - 1]
* 在Inorder数组中，根元素右边的元素是右子树，即区间 inorder[index + 1, n - 1]
* 在preorder数组中，区间 preorder[1, index] 在根节点的preorder[0] 的左边
* 在preorder数组中，preorder[index+1, n] 在根节点的preorder[0] 的右边
* 在由左子树中的元素组成的子数组上递归调用
* 在由右子树中的元素组成的子数组上递归地调用

### Java实现

```
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length <= 0 || inorder.length <= 0) {
            return null;
        }

        int key = preorder[0];
        TreeNode root = new TreeNode(key);
        if (preorder.length == 1) {
            return root;
        }

        int index = getRootIndex(inorder, key);
        if (index > 0 || index < preorder.length) {
            int[] pre = Arrays.copyOfRange(preorder, 1, index + 1);
            int[] inor = Arrays.copyOfRange(inorder, 0, index);
            root.left = buildTree(pre, inor);
            
            pre = Arrays.copyOfRange(preorder, index + 1, preorder.length);
            inor = Arrays.copyOfRange(inorder, index + 1, inorder.length);
            root.right = buildTree(pre, inor);
        }

        return root;
    }

    private final int getRootIndex(int[] inorder, int key) {
        for (int i = 0; i < preorder.length; i++) {
            if (preorder[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
```

### Kotlin尾递归实现

```
class Solution {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {

        return tailrecOrder(preorder, inorder)
    }

    tailrec fun tailrecOrder(preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preorder.size <= 0 || inorder.size <= 0) {
            return null;
        }
        val key = preorder[0]
        val root = TreeNode(key)
        if (preorder.size == 1) {
            return root
        }

        val index = getRootIndex(inorder, key)
        if (index > 0 || index < preorder.size) {
            var pre = Arrays.copyOfRange(preorder, 1, index + 1)
            var inor = Arrays.copyOfRange(inorder, 0, index)
            root.left = tailrecOrder(pre, inor)

            pre = Arrays.copyOfRange(preorder, index + 1, preorder.size)
            inor = Arrays.copyOfRange(inorder, index + 1, inorder.size)
            root.right = tailrecOrder(pre, inor)
        }

        return root
    }

    inline fun getRootIndex(inorder: IntArray, key: Int): Int {
        inorder.forEachIndexed { index, item ->
            if (item == key) {
                return index
            }
        }
        return -1
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)，一起来学习，期待与你一起成长





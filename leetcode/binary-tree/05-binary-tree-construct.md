题目来源于 LeetCode 上第 105号（Construct Binary Tree from Preorder and Inorder Traversal）问题：从前序与中序遍历序列构造二叉树。题目难度为 Medium。这道题目和 [剑指offer： 07 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/) 一样

* [英文地址：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
* [中文地址：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
* [剑指offer 07 重建二叉树：https://leetcode-cn.com/problems/zhong-jian......](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

## 题目描述

根据一棵树的前序遍历与中序遍历构造二叉树。

**注意:**

你可以假设树中没有重复的元素。

例如，给出

```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```

返回如下的二叉树：

```
    3
   / \
  9  20
    /  \
   15   7
```

## 思路：

如果还不了解前序遍历和中序遍历算法可以点击下方链接前往

* [二叉树：前序遍历](/binary-tree/01-binary-tree-preorder.md)
* [二叉树：中序遍历](/binary-tree/02-binary-tree-inorder.md)

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

**复杂度分析：**

* 时间复杂度：O(n), 遍历每一个节点，重建二叉树
* 空间复杂度：O(height)，height 为树的高度，递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度

### Kotlin 实现

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
        if (index > 0 && index < preorder.size) {
            // 计算左子树，所以根节点除外，从下标1开始
            var pre = Arrays.copyOfRange(preorder, 1, index + 1)
            var inor = Arrays.copyOfRange(inorder, 0, index)
            root.left = tailrecOrder(pre, inor)

            pre = Arrays.copyOfRange(preorder, index + 1, preorder.size)
            inor = Arrays.copyOfRange(inorder, index + 1, inorder.size)
            root.right = tailrecOrder(pre, inor)
        }

        return root
    }

    fun getRootIndex(inorder: IntArray, key: Int): Int {
        inorder.forEachIndexed { index, item ->
            if (item == key) {
                return index
            }
        }
        return -1
    }
}
```

### Java 实现

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
        if (index > 0 && index < preorder.length) {
            // 计算左子树，所以根节点除外，从下标1开始
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



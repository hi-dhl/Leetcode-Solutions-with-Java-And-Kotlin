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


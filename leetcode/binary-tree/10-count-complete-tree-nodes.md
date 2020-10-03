题目来源于 LeetCode 上第 222 号（ Count Complete Tree Nodes）问题：求二叉树中的节点个数。题目难度为 Medium。

* [英文地址：https://leetcode.com/problems/count-complete-tree-nodes/](https://leetcode.com/problems/count-complete-tree-nodes/)

* [中文地址：https://leetcode-cn.com/problems/count-complete-tree-nodes/](https://leetcode-cn.com/problems/count-complete-tree-nodes/)

## 题目描述

Given a complete binary tree, count the number of nodes.

**Note:**

[Definition of a complete binary tree from Wikipedia:](https://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees)
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

**Example:**

```
Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
```

## 思路一：DFS

假设每个节点都标记为1，空节点标记为0，将每个节点的和累加起来即可

### Java实现

```
class Solution {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

}
```

### Koltin实现

```
class Solution {

    private tailrec fun countNodes(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        return countNodes(root.left) + countNodes(root.right) + 1
    }
    
}
```

## 思路二：BFS

假设每个节点都标记为1，空节点标记为0，根据前序、中序、后序、层次遍历访问每个节点，遇到不为空的节点+1，层次遍历算法如下，前序、中序、后序遍历原理相同

### Java实现

```
class Solution {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return count;
    }

}
```

### Koltin实现

```
class Solution {
    fun countNodes(root: TreeNode?): Int {
        var count = 0
        root?.let { it ->
            val queue = LinkedList<TreeNode>()
            queue.offer(root)
            while (!queue.isEmpty()) {
                count++
                val node = queue.poll()
                if (node.left != null) {
                    queue.offer(node.left)
                }
                if (node.right != null) {
                    queue.offer(node.right)
                }
            }

        }
        return count
    }
}
```


## 扩展题目

### 1. 求二叉树中第K层结点的个数

构建一棵二叉树（不一定是二叉查找树），求出该二叉树中第K层中的结点个数（根结点为第0层）

**代码如下**

```
class Solution {

    public int countLevelNodes(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }

        if (k == 1) {
            return 1;
        }

        return countLevelNodes(root.left, k - 1) + countLevelNodes(root.right, k - 1);
    }

}
```

### 2. 求二叉树第K层的叶子节点个数

**代码如下：**

```
class Solution {

    public int countLevelNodes(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }

        if (k == 1) {
            if (root.left == null && root.right == null) {
                return 1;
            } else {
                return 0;
            }
        }

        return countLevelNodes(root.left, k - 1) + countLevelNodes(root.right, k - 1);
    }
}
```



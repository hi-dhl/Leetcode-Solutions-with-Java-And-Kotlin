题目来源于 LeetCode 上第 100号（same-tree）问题：判断两棵二叉树是否结构相同。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/same-tree/](https://leetcode.com/problems/same-tree/)

* [中文地址：https://leetcode-cn.com/problems/same-tree/](https://leetcode-cn.com/problems/same-tree/)

## 题目描述

给定两个二叉树，编写一个函数来检验它们是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

**示例 1:**

```
输入:       1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

输出: true
```

**示例 2:**

```
输入:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]

输出: false
```

**示例 3:**

```
输入:       1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

输出: false
```

## 思路一：非递归

两棵二叉树结构相同，需要满足以下条件：

* 二叉树结构相同
* 二叉树对应节点值相同

根据层次遍历算法，访问每一个节点，检查每个节点是否相同，核心算法如下：

```
if (p == null && q == null) {
    continue;
}

if (p == null || q == null) {
    return false;
}

if (p.val != q.val) {
    return false;
}
```

**复杂度分析：**

* 时间复杂度：O(min(m,n))，m 和 n 分别为两颗二叉树中的节点个数，对两个二叉树同时进行深度优先搜索，只要有一颗二叉树的节点为空，即停止循环，因此被访问到的节点数不会超过较小的二叉树的节点数
* 空间复杂度：O(min(m,n))，m 和 n 分别为两颗二叉树中的节点个数，循环从队列中取数据，当有一颗二叉树的节点为空，即停止循环。

### Java 实现

```
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        Queue<TreeNode> pWrapQueue = new LinkedList<>();
        Queue<TreeNode> qWrapQueue = new LinkedList();
        pWrapQueue.offer(p);
        qWrapQueue.offer(q);
        while (!pWrapQueue.isEmpty() && !qWrapQueue.isEmpty()) {
            TreeNode pNode = pWrapQueue.poll();
            TreeNode qNode = qWrapQueue.poll();

            if (pNode == null && qNode == null) {
                continue;
            }

            if (pNode == null || qNode == null) {
                return false;
            }

            if (pNode.val != qNode.val) {
                return false;
            }

            pWrapQueue.offer(pNode.left);
            qWrapQueue.offer(qNode.left);
            pWrapQueue.offer(pNode.right);
            qWrapQueue.offer(qNode.right);

        }
        return pWrapQueue.size() == qWrapQueue.size();
    }
}
```

### Koltin 实现

```
class Solution {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) {
            return true
        }

        if (p == null || q == null) {
            return false
        }

        val pWrapQueue = LinkedList<TreeNode>()
        val qWrapQueue = LinkedList<TreeNode>()
        pWrapQueue.offer(p)
        qWrapQueue.offer(q)
        while (!pWrapQueue.isEmpty() && !qWrapQueue.isEmpty()) {
            val pNode = pWrapQueue.poll()
            val qNode = qWrapQueue.poll()
            if (pNode == null && qNode == null) {
                continue;
            }

            if (pNode == null || qNode == null) {
                return false;
            }

            if (pNode.`val` != qNode.`val`) {
                return false;
            }

            pWrapQueue.offer(pNode.left)
            pWrapQueue.offer(pNode.right)
            qWrapQueue.offer(qNode.left)
            qWrapQueue.offer(qNode.right)
        }

        return pWrapQueue.size == qWrapQueue.size
    }
}
```

## 思路二：递归

**复杂度分析：**

* 时间复杂度：O(min(m,n))，m 和 n 分别为两颗二叉树中的节点个数，对两个二叉树同时进行深度优先搜索，只要有一颗二叉树的节点为空，即停止循环，因此被访问到的节点数不会超过较小的二叉树的节点数
* 空间复杂度：O(min(m,n))，m 和 n 分别为两颗二叉树中的节点个数，递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度，树的高度不会超过较小的二叉树的最大高度

### Java 实现

```
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}
```

### Koltin 实现

```
class Solution {
    tailrec fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) {
            return true
        }

        if (p == null || q == null) {
            return false
        }

        if (p.`val` == q.`val`) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }

        return false
    }
}
```



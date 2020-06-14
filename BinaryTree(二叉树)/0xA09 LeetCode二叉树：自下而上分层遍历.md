# LeetCode二叉树：自下而上分层遍历

题目来源于 LeetCode 上第 107 号（Binary Tree Level Order Traversal II）问题：自下而上分层遍历。题目难度为 Easy。

* [英文地址：https://leetcode.com/problems/binary-tree-level-order-traversal-ii/](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/)

* [中文地址：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/)

## 题目描述

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

**Example:**

Given binary tree [3,9,20,null,null,15,7],

```
    3
   / \
  9  20
    /  \
   15   7
```

return its bottom-up level order traversal as:

```
[
  [15,7],
  [9,20],
  [3]
]
```

## 思路一：BFS

BFS: 广度优先搜索算法（Breadth-First-Search），是一种图形搜索算法，从根节点开始，沿着树(图)的宽度遍历树(图)的节点，如果所有节点均被访问，则算法中止

二叉树的自下而上分层遍历，和层次遍历相同，只不过在添加数据的时候，逆序添加，具体步骤如下：

1. 新建一个 queue，用来存储访问的每个节点
2. 新建一个 wrapList 集合，保存每层的数据
3. 循环判断队列是否为空，如果队列为空即循环终止
4. 获取每一层的数据的长度，将数据保存到 subList 中
5. 遍历每层数据时，将下一层的节点保存到 queue 中
6. 每层遍历结束，将当前层 subList 数据 逆序添加到 wrapList，如：wrapList.add(0, subList);
7. 再次执行3~6，直到 queue 为空即结束循环

**注意：**

每种 Queue 方法存在两种形式：

* 如果操作失败，抛出了一个异常
* 其他的返回一个特殊值（null 或者 false，取决于操作）

| 操作类型 | 抛出异常 | 返回特殊值 |
| :-: | :-: | :-: |
| 插入 | add(e) | offer(e) |
| 移除 | remove() | poll() |
| 查找 | element() | peek() |

Queue 的实现可以限制所持有元素的数量，这样的队列称为有界，有些 Queue 实现 java.util.concurrent 是有界的，但实现 java.util 不是：

* add ：继承自 Collection，插入一个元素，除非它会违反队列的容量限制，在这种情况下抛出 IllegalStateException
* offer ：方法仅用于有界队列，不能插入时，返回 fasle

在 remove 与 poll 方法都是从队列中删除第一个元素，如果队列为空：

* remove : removethrows NoSuchElementException
* poll : 返回 null

element 与 peek 方法用于在队列的头部查询元素，如果队列为空：

* element : 抛出 NoSuchElementException
* peek : 返回 null

### Java实现

```
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null) {
            return wrapList;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new LinkedList();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
            wrapList.add(0, subList);
        }
        return wrapList;
    }
}
```

### Koltin实现

```
class Solution {
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        val wrapList = mutableListOf<MutableList<Int>>()
        root?.let {
            val queue = ArrayDeque<TreeNode>()
            queue.offer(root)
            while (!queue.isEmpty()) {
                val subList = mutableListOf<Int>()
                var size = queue.size
                while (size > 0) {
                    val node = queue.poll()
                    subList.add(node.`val`)
                    if (node.left != null) queue.offer(node.left)
                    if (node.right != null) queue.offer(node.right)
                    size--
                }
                wrapList.add(0, subList)
            }
        }
        return wrapList
    }
}
```

## 思路二：DFS

DFS: 深度优先搜索算法（Depth-First-Search），是搜索算法的一种, 它沿着树的深度遍历树的节点，尽可能深的搜索树的分支。

### Java实现

```
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList();
        dfs(wrapList, root, 0);
        return wrapList;
    }

    public void dfs(List<List<Integer>> subList, TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level >= subList.size()) {
            subList.add(0, new LinkedList<Integer>());
        }

        subList.get(subList.size() - 1 - level).add(root.val);
        dfs(subList, root.left, level + 1);
        dfs(subList, root.right, level + 1);
    }
}
```

### Koltin尾递归实现

```
class Solution {
    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        val wrapList = mutableListOf<MutableList<Int>>()
        dfs(wrapList, root, 0)
        return wrapList
    }

    tailrec fun dfs(subList: MutableList<MutableList<Int>>, root: TreeNode?, level: Int) {
        if (root == null) {
            return
        }

        if (level >= subList.size) {
            subList.add(0, mutableListOf<Int>())
        }

        subList[subList.size - 1 - level].add(root.`val`)
        dfs(subList, root.left, level + 1)
        dfs(subList, root.right, level + 1)
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解 [Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin) 和  Android 10 源码分析 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)，一起来学习，期待与你一起成长



# 0xA04 LeetCode二叉树：层次遍历

> 访问英文地址需要科学上网

题目来源于 LeetCode 上第 102号（Binary Tree Level Order Traversal）问题：二叉树的层次遍历。题目难度为 Medium。

[英文地址: https://leetcode.com/problems/binary-tree-level-order-traversal/](https://leetcode.com/problems/binary-tree-level-order-traversal/)
[中文地址: https://leetcode-cn.com/problems/binary-tree-level-order-traversal/](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

#### 题目描述
 
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

**Example:**

```
Given binary tree [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
```

**二叉树的层次遍历过程：逐层遍历**
![](http://cdn.51git.cn/2020-04-18-二叉树.png)

**结果为：4 2 6 1 3 5 7**

## 思路一：BFS

BFS: 广度优先搜索算法（Breadth-First-Search），是一种图形搜索算法，从根节点开始，沿着树(图)的宽度遍历树(图)的节点，如果所有节点均被访问，则算法中止

二叉树的层次遍历即逐层遍历，由上图所示按照访问的顺序依次输出，得出需要用到队列

1. 新建一个 queue，用来存储访问的每个节点
2. 新建一个 wrapList 集合，保存每层的数据
3. 循环判断队列是否为空，如果队列为空即循环终止
4. 获取每一层的数据的长度，依次遍历，将数据保存到 currLevelList 中
5. 遍历每层数据时，将下一层的节点保存到 queue 中
6. 每层遍历结束，将当前层 currLevelList 数据保存到 wrapList
6. 再次执行3~6，直到 queue 为空即结束循环

**注意：**

每种 Queue 方法存在两种形式：

* 如果操作失败，抛出了一个异常
* 其他的返回一个特殊值（null 或者 false，取决于操作）

| 操作类型 | 抛出异常 | 返回特殊值 |
| :-: | :-: | :-: |
| 插入 | add(e) | offer(e) |
| 移除 | 弹出 | poll() |
| 查找 | 弹出 | peek() |

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 存储每层的数据
        List<List<Integer>> wrapList = new LinkedList();
        if (root == null) return wrapList;
        // 存储访问过的每层节点
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size(); // 获取每层的数据的长度
            List<Integer> currLevelList = new LinkedList();
            // 遍历每次每层的数据
            for (int i = 0; i < len; i++) {
                root = queue.poll();
                // 按照每层遍历的顺序，存储每层的节点
                currLevelList.add(root.val);
                // 将下一层的节点入队
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);
            }
            wrapList.add(currLevelList);
        }

        return wrapList;
    }
}
```

### Kotlin实现

```
class Solution {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val wrapList = mutableListOf<MutableList<Int>>()
        root?.let {
            val queue = ArrayDeque<TreeNode>()
            queue.offer(root)
            while (queue.isNotEmpty()) {
                val currLevelList = mutableListOf<Int>()
                var len = queue.size
                while (len > 0) {
                    queue.poll()?.let { curNode ->
                        currLevelList.add(curNode.`val`)
                        if (curNode.left != null) queue.offer(curNode.left)
                        if (curNode.right != null) queue.offer(curNode.right)
                    }
                    len--
                }
                wrapList.add(currLevelList)
            }
        }
        return wrapList
    }
}
```


## 思路二：DFS

DFS: 深度优先搜索算法（Depth-First-Search），是搜索算法的一种, 它沿着树的深度遍历树的节点，尽可能深的搜索树的分支。

### Java 实现

```
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList();
        dfs(wrapList, root, 0);
        return wrapList;
    }

    public void dfs(List<List<Integer>> wrapList, TreeNode root, int level) {
        if (root == null) return;
        if (level >= wrapList.size()) {
            wrapList.add(new LinkedList<Integer>());
        }
        List<Integer> currLevelList = wrapList.get(level);
        currLevelList.add(root.val);
        dfs(wrapList, root.left, level + 1);
        dfs(wrapList, root.right, level + 1);
    }
}
```

### Kotlin 尾递归

```
class Solution {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val wrapList = mutableListOf<MutableList<Int>>()
        dfs(wrapList, root, 0)
        return wrapList
    }

    tailrec fun dfs(wrapList: MutableList<MutableList<Int>>, root: TreeNode?, level: Int) {
        if (root == null) return

        if (level >= wrapList.size) {
            wrapList.add(mutableListOf<Int>())
        }

        wrapList[level].add(root.`val`)
        dfs(wrapList, root.left, level + 1)
        dfs(wrapList, root.right, level + 1)
    }
}
```

## 结语

致力于分享一系列的Android系统源码、逆向分析、算法相关的文章，每篇文章都会反复推敲，如果你同我一样喜欢算法、LeetCode，一起来学习，期待与你一起成长



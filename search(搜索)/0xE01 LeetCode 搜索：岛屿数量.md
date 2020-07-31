# 0xE01 LeetCode 搜索：岛屿数量

题目来源于 `LeetCode` 上 第 `200` 号问题：岛屿数量。题目难度为 `Medium`。

* [英文地址：https://leetcode.com/problems/number-of-islands/](https://leetcode.com/problems/number-of-islands/) 
* [中文地址：https://leetcode-cn.com/problems/number-of-islands/](https://leetcode-cn.com/problems/number-of-islands/) 

## 题目描述

给你一个由 `'1'`（陆地）和 `'0'`（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

**示例 1：**

```
输入:
[
['1','1','1','1','0'],
['1','1','0','1','0'],
['1','1','0','0','0'],
['0','0','0','0','0']
]
输出: 1
```

**示例 2：**

```
输入:
[
['1','1','0','0','0'],
['1','1','0','0','0'],
['0','0','1','0','0'],
['0','0','0','1','1']
]
输出: 3
解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
```

## 思路一：广度优先遍历

广度优先搜索（BFS）的一个常见应用是找出从根结点到目标结点的最短路径。

**在 BFS 中使用队列的原因？**

结点的处理顺序与它们添加到队列的顺序是完全相同的顺序，即先进先出（FIFO）。这就是我们在 BFS 中使用队列的原因。

**队列的基本使用**

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


**算法流程如下：**

广度优先搜索就是扫描整个二维网格，遇到 `1` 将其加入队列中，然后将其标记为 `0`, 直到队列为空，搜索结束。

**复杂度分析：**

* 时间复杂度：0(MN) ，M 是行数，N 是列数
* 空间复杂度：O(MN)，最坏的情况深度达到第 M 行 N 列

### Kotlin 实现

```

class Solution {

    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.size == 0) {
            return 0;
        }

        var step = 0;
        val queue = LinkedList<IntArray>()
        val row = grid.size
        val colum = grid[0].size
        for (i in 0 until row) {
            for (j in 0 until colum) {
                if (grid[i][j] == '1') {
                    step = step + 1
                    queue.offer(intArrayOf(i, j))
                    while (!queue.isEmpty()) {
                        val (x1, y1) = queue.poll()
                        if (x1 < 0 || x1 >= row || y1 < 0 || y1 >= colum || grid[x1][y1] == '0') {
                            continue;
                        }

                        grid[x1][y1] = '0'
                        queue.offer(intArrayOf(x1 + 1, y1))
                        queue.offer(intArrayOf(x1 - 1, y1))
                        queue.offer(intArrayOf(x1, y1 + 1))
                        queue.offer(intArrayOf(x1, y1 - 1))
                    }
                }
            }
        }
        return step
    }
}
```

### Java 实现

```
class Solution {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row = grid.length;
        int colum = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int step = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                if (grid[i][j] == '1') {
                    queue.offer(new int[]{i, j});
                    step++;
                    while (!queue.isEmpty()) {
                        int[] tmp = queue.poll();
                        int x1 = tmp[0];
                        int y1 = tmp[1];
                        if (x1 < 0 || x1 >= row || y1 < 0 || y1 >= colum || grid[x1][y1] == '0') {
                            continue;
                        }
                        grid[x1][y1] = '0';
                        queue.offer(new int[]{x1 + 1, y1});
                        queue.offer(new int[]{x1 - 1, y1});
                        queue.offer(new int[]{x1, y1 + 1});
                        queue.offer(new int[]{x1, y1 - 1});
                    }
                }

            }
        }
        return step;
    }
}
```

## 思路二：深度优先搜索

**DFS:** 深度优先搜索算法（Depth-First-Search），是搜索算法的一种, 它沿着树的深度遍历树的节点，尽可能深的搜索树的分支。

**递归的两个特点：**

1. 问题和子问题都会调用函数自身，所以要找寻问题与子问题的递归关系，采用自上而下的思考方式。
2. 有个终止条件（临界点）结束当前递归。

**参数说明：**

* `grid` 表示一个二维网格
* 用 `i` 表示行的下标，`j` 表示列的下标
* `x1` 表示当前所在的格子的横坐标
* `y1` 表示当前所在的格子的纵坐标

**终止条件：**

* 是否在单元格内（`x >= row` 或者 `y >= colum`）。
* 如果当前格子已经访问过了，满足于 `grid[x1][y1] == '0'`

**算法过程：**

* 遍历整个网格
* 从坐标 `[0, 0]` 开始进行深度优先搜索，在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0
* 岛屿的数量就是进行深度优先搜索的次数

### Kotlin

```
class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.size == 0) {
            return 0;
        }

        var step = 0;
        val row = grid.size
        val colum = grid[0].size
        for (i in 0 until row) {
            for (j in 0 until colum) {
                if (grid[i][j] == '1') {
                    step = step + 1
                    dfs(i, j, row, colum, grid)
                }
            }
        }
        return step
    }

    fun dfs(x1: Int, y1: Int, row: Int, colum: Int, grid: Array<CharArray>) {
        if (x1 < 0 || x1 >= row || y1 < 0 || y1 >= colum || grid[x1][y1] == '0') {
            return;
        }

        grid[x1][y1] = '0'
        dfs(x1 + 1, y1, row, colum, grid)
        dfs(x1 - 1, y1, row, colum, grid)
        dfs(x1, y1 + 1, row, colum, grid)
        dfs(x1, y1 - 1, row, colum, grid)
    }
}
```

### Java

```
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int colum = grid[0].length;
        int step = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                if (grid[i][j] == '1') {
                    step++;
                    dfs(i, j, row, colum, grid);
                }

            }
        }
        return step;
    }

    void dfs(int x1, int y1, int row, int colum, char[][] grid) {
        if (x1 < 0 || x1 >= row || y1 < 0 || y1 >= colum || grid[x1][y1] == '0') {
            return;
        }
        grid[x1][y1] = '0';
        dfs(x1 + 1, y1, row, colum, grid);
        dfs(x1 - 1, y1, row, colum, grid);
        dfs(x1, y1 + 1, row, colum, grid);
        dfs(x1, y1 - 1, row, colum, grid);
    }
}
```

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack  源码相关的文章，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解 [Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin) 和  Android 10 源码分析 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis) 一起来学习，期待与你一起成长


### Android10-Source-Analysis

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### Leetcode-Solutions-with-Java-And-Kotlin

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### Technical-Article-Translation

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。



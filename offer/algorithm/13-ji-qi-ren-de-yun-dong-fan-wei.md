题目来源于 `LeetCode` 剑指 `offer` 第 `13` 号问题：机器人的运动范围。题目难度为 `Medium`。

* [中文地址：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

## 题目描述

地上有一个m行n列的方格，从坐标 `[0,0]` 到坐标 `[m-1,n-1]` 。一个机器人从坐标 `[0, 0]` 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

**示例 1：**

```
输入：m = 2, n = 3, k = 1
输出：3
```

**示例 2：**

```
输入：m = 3, n = 1, k = 0
输出：1
```

**提示：**

* 1 <= n,m <= 100
* 0 <= k <= 20

## 思路一：BFS 广度优先搜索（队列）

BFS: 通常利用队列实现广度优先遍历。

**参数说明：**

`queue` 表示一个队列，存储访问过的单元格，`robot` 表示一个二维数组记录访问过的单元格，用 `x` 表示行的下标，`y` 表示列的下标。

**过滤条件：**

* 是否在单元格内（`x>m-1` 或者 `y>n-1`）。
* 是否已经访问过了。
* 数位之和是否超过了 `k`。

**算法过程：**

* 按照题目所说，机器人从 `[0, 0]` 开始移动，每次移动一格，表示 `[0, 0]` 是原始坐标，它只会向右或者向下移动。
* 初始化: 将起点坐标 `[0, 0]` 放进 queue 中。
* 循环遍历：
    * 先使用 **过滤条件：** 过滤掉无用的坐标。
    * 数组 robot 记录机器人访问过的单元格，如果访问过将单元格标记为 -1。
    * 朝着右、下两个方向访问，并将其坐标添加进 `queue`。
    * 并用一个辅助变量 res 计算机器人可到达的单元格。

**复杂度分析：**

`M,N` 分别为矩阵行列大小。

* 时间复杂度：`O(MN)`，矩阵中有 `M` 行 `N` 列，最差的情况下遍历完所有的单元格，所以时间复杂度为 `O(MN)`。
* 空间复杂度：`O(MN)`，需要建立额外的数组记录当前格子是否已经访问过了，最差的情况下访问了所有的单元格，故占用额外空间 `O(MN)`


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

### Koltin 实现

```
class Solution {
    fun movingCount(m: Int, n: Int, k: Int): Int {
        val robot = Array(m, { IntArray(n) })
        val queue = LinkedList<IntArray>()
        var res = 0
        queue.offer(intArrayOf(0,0))
        while(!queue.isEmpty()){
            val(x,y) = queue.poll()
            if(x>m-1 || y>n-1 || robot[x][y] == -1 || count(x) + count(y) > k){
                continue;
            }
            robot[x][y] = -1
            res +=1
            queue.offer(intArrayOf(x+1,y))
            queue.offer(intArrayOf(x,y+1))
        }
        return res
    }

    fun count(x: Int): Int {
        var sx = x
        var count = 0
        while (sx > 0) {
            count += sx % 10
            sx = sx / 10
        }
        return count
    }
}
```

### Java 实现

```
class Solution {
    public int bfs(int m, int n, int k) {
        int[][] robot = new int[m][n];
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] item = queue.poll();
            int x = item[0];
            int y = item[1];
            if (x > m - 1 || y > n - 1 || count(x) + count(y) > k || robot[x][y] == -1) {
                continue;
            }
            robot[x][y] = -1;
            res++;
            queue.offer(new int[]{x + 1, y});
            queue.offer(new int[]{x, y + 1});
        }
        return res;
    }

    int count(int x) {
        int count = 0;
        while (x > 0) {
            count += x % 10;
            x = x / 10;
        }
        return count;
    }
}
```

## 思路二：DFS 深度优先搜索(递归)

**DFS:** 深度优先搜索算法（Depth-First-Search），是搜索算法的一种, 它沿着树的深度遍历树的节点，尽可能深的搜索树的分支。

**递归的两个特点：**

1. 问题和子问题都会调用函数自身，所以要找寻问题与子问题的递归关系，采用自上而下的思考方式。
2. 有个终止条件（临界点）结束当前递归。

**参数说明：**

`robot` 表示一个二维数组记录访问过的单元格，用 `x` 表示行的下标，`y` 表示列的下标。

**终止条件：**

* 是否在单元格内（`x>m-1` 或者 `y>n-1`）。
* 是否已经访问过了。
* 数位之和是否超过了 `k`。

**算法过程：**

* 按照题目所说，机器人从 `[0, 0]` 开始移动，每次移动一格，表示 `[0, 0]` 是原始坐标，它只会向右或者向下移动。
* 数组 robot 记录机器人访问过的单元格，如果访问过将单元格标记为 -1。
* 朝着右、下两个方向访问，未访问过并且在单元格范围内，计算它们之和。
    
**复杂度分析：**

`M,N` 分别为矩阵行列大小。

* 时间复杂度：`O(MN)`，矩阵中有 `M` 行 `N` 列，最差的情况下遍历完所有的单元格，所以时间复杂度为 `O(MN)`。
* 空间复杂度：`O(MN)`，需要建立额外的数组记录当前单元格是否已经访问过了，最差的情况下访问了所有的单元格，故占用额外空间 `O(MN)`

### Koltin 实现

```
class Solution {
    fun movingCount(m: Int, n: Int, k: Int): Int {
        val robot = Array(m, { IntArray(n) })
        return dfs(robot, 0, 0, m, n, k)
    }

    fun dfs(robot: Array<IntArray>, x: Int, y: Int, m: Int, n: Int, k: Int): Int {
        if (x > m - 1 || y > n - 1 || robot[x][y] == -1 || count(x) + count(y) > k) {
            return 0
        }
        robot[x][y] = -1
        // 起点为0，0，每次只能移动一格，即向右 x +1 或者向下 y +1
        return dfs(robot, x + 1, y, m, n, k) + dfs(robot, x, y + 1, m, n, k) + 1
    }
    
    fun count(x: Int): Int {
        var sx = x
        var count = 0
        while (sx > 0) {
            count += sx % 10
            sx = sx / 10
        }
        return count
    }
}
```

### Java 实现

```
class Solution {
    public int movingCount(int m, int n, int k) {
        int[][] robot = new int[m][n];
        return dfs(robot, 0, 0, k, m, n);
    }


    int dfs(int[][] robot, int x, int y, int k, int m, int n) {
        if (x > m - 1 || y > n - 1 || robot[x][y] == -1 || count(x) + count(y) > k) {
            return 0;
        }

        robot[x][y] = -1;
        // 起点为0，0，每次只能移动一格，即向右 x +1 或者向下 y +1
        return dfs(robot, x + 1, y, k, m, n) + dfs(robot, x, y + 1, k, m, n) + 1;
    }

    int count(int x) {
        int count = 0;
        while (x > 0) {
            count += x % 10;
            x = x / 10;
        }
        return count;
    }
}
```



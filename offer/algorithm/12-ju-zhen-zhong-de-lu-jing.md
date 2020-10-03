题目来源于 `LeetCode` 剑指 `offer` 第 `12` 号问题：矩阵中的路径。题目难度为 `Medium`。和 [第79号问题：单词搜索](https://leetcode-cn.com/problems/word-search/) 一样

* [中文地址：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

## 题目描述

请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的 `3×4` 的矩阵中包含一条字符串 `“bfce”` 的路径（路径中的字母用加粗标出）。

```
[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]
```

但矩阵中不包含字符串 `“abfb”` 的路径，因为字符串的第一个字符 `b` 占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

**示例1:**

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
```

**示例2:**

```
输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false
```

**提示：**

```
1 <= board.length <= 200
1 <= board[i].length <= 200
```

## 思路：DFS 深度优先遍历(递归)

**DFS:** 深度优先搜索算法（Depth-First-Search），是搜索算法的一种, 它沿着树的深度遍历树的节点，尽可能深的搜索树的分支。

**递归的两个特点：**

1. 问题和子问题都会调用函数自身，所以要找寻问题与子问题的递归关系，采用自上而下的思考方式。
2. 有个终止条件（临界点）结束当前递归。

**参数说明：**

`board` 是一个二维数组，所以用 `i` 表示行的下标，`j` 表示列的下标，`word` 是一个字符串转为数组用 `words` 表示，`k` 表示数组 `words` 的下标。

**算法过程：**

* 按照题目所说，搜索过程中可以朝着上、下、左、右四个方向去遍历。
* 每个方向如果访问过了，将当前访问过的元素存为一个临时变量 `tmp`，并将访问过的元素修改为 `'/'`，表示已经访问过了不在重复访问。
* 如果匹配失败了，还原数组中修改过后的元素，从另外一个方向继续访问。
* 如果匹配成功了，也需要还原数组中修改过后的元素，回溯过程中，再次验证是否正确。
    
**复杂度分析：**

`M,N` 分别为矩阵行列大小，`K` 为字符串 `word` 长度。

* 时间复杂度：`O(3^k MN)`，因为矩阵中有 `M` 行 `N` 列，所以时间复杂度为 `O(MN)`，搜索过程中可以朝着上、下、左、右四个方向去遍历，舍弃回溯那个方向，实际上只朝着三个方向去遍历，所以时间复杂度为 `O(3^k)`。
* 空间复杂度：`O(K)`，递归调用占用栈的空间，递归调用结束后，栈空间会被释放，只是使用了几个标示变量作为额外空间，这部分可以忽略不计，每次搜索中深度不会超过 `K`，因为占用栈的空间大小不会超过 `O(K)`。

### Koltin 实现

```
class Solution {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (board.size <= 0 || board[0].size <= 0) {
            return false
        }
        val row = board.size
        val colum = board[0].size
        val words = word.toCharArray()
        for (i in 0 until row) {
            for (j in 0 until colum) {
                if (dfs(board, words, i, j, 0, row, colum)) {
                    return true
                }
            }
        }
        return false
    }

    fun dfs(board: Array<CharArray>, words: CharArray, i: Int, j: Int, k: Int, row: Int, colum: Int): Boolean {
        if (i > row - 1 || i < 0 || j > colum - 1 || j < 0 || board[i][j] != words[k]) {
            return false
        }

        if (k == words.size - 1) {
            return true
        }
        val tmp = board[i][j]
        board[i][j] = '/'
        val result = dfs(board, words, i + 1, j, k + 1, row, colum) || dfs(board, words, i - 1, j, k + 1, row, colum) || dfs(board, words, i, j + 1, k + 1, row, colum) || dfs(board, words, i, j - 1, k + 1, row, colum)
        board[i][j] = tmp
        return result
    }
}
```

### Java 实现

```

class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length <= 0 || board[0].length <= 0) {
            return false;
        }
        char[] words = word.toCharArray();
        int row = board.length;
        int colum = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                if (dfs(board, words, i, j, 0, row, colum)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] borad, char[] words, int i, int j, int k, int row, int colum) {
        if (i > row - 1 || i < 0 || j > colum - 1 || j < 0 || borad[i][j] != words[k]) {
            return false;
        }

        if (k == words.length - 1) {
            return true;
        }

        char tmp = borad[i][j];
        borad[i][j] = '/';
        boolean result = dfs(borad, words, i + 1, j, k + 1, row, colum) || dfs(borad, words, i - 1, j, k + 1, row, colum) || dfs(borad, words, i, j + 1, k + 1, row, colum) || dfs(borad, words, i, j - 1, k + 1, row, colum);
        borad[i][j] = tmp;
        return result;
    }
}
```



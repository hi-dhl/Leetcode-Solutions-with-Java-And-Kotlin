package com.hidhl.leetcode._200.kotlin

import java.util.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/31
 *     desc  :
 * </pre>
 */

class Solution {

    /**
     * 广度优先遍历
     */
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


    /**
     * 深度优先遍历
     */
    fun numIslands2(grid: Array<CharArray>): Int {
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
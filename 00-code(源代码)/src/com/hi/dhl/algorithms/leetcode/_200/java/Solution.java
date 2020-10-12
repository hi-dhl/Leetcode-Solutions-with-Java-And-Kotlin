package com.hi.dhl.algorithms.leetcode._200.java;

import java.util.LinkedList;
import java.util.Queue;

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
     *
     * @param grid
     * @return
     */
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


    /**
     * 深度优先遍历
     *
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
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
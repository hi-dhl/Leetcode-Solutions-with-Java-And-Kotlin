package com.hi.dhl.algorithms.offer._13.java;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
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


    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.bfs(7, 2, 3));
//        solution.count(0);
    }
}
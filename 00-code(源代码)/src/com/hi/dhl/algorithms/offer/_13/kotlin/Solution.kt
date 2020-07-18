package com.hi.dhl.algorithms.offer._13.kotlin

import java.util.*

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

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
        return dfs(robot, x + 1, y, m, n, k) + dfs(robot, x, y + 1, m, n, k) + 1
    }

    fun bfs(m: Int, n: Int, k: Int): Int {
        val robot = Array(m, { IntArray(n) })
        val queue = LinkedList<IntArray>()
        var res = 0
        queue.offer(intArrayOf(0, 0))
        while (!queue.isEmpty()) {
            val (x, y) = queue.poll()
            if (x > m - 1 || y > n - 1 || robot[x][y] == -1 || count(x) + count(y) > k) {
                continue;
            }
            robot[x][y] = -1
            res += 1
            queue.offer(intArrayOf(x + 1, y))
            queue.offer(intArrayOf(x, y + 1))
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

fun main() {
    val solution = Solution()
    println(solution.bfs(38, 15, 9))
}
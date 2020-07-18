package com.hi.dhl.algorithms.offer._12.kotlin

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

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
        val result =
            dfs(board, words, i + 1, j, k + 1, row, colum) || dfs(board, words, i - 1, j, k + 1, row, colum) || dfs(
                board,
                words,
                i,
                j + 1,
                k + 1,
                row,
                colum
            ) || dfs(board, words, i, j - 1, k + 1, row, colum)
        board[i][j] = tmp
        return result
    }
}
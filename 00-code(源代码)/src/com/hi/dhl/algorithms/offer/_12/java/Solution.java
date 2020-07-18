package com.hi.dhl.algorithms.offer._12.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */

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
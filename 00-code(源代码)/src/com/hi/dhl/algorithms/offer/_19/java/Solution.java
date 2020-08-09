package com.hi.dhl.algorithms.offer._19.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/7
 *     desc  :
 * </pre>
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int row = s.length();
        int colum = p.length();
        boolean[][] dp = new boolean[row + 1][colum + 1];
        dp[0][0] = true;
        for (int j = 1; j <= colum; j++) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= colum; j++) {
                char ms = s.charAt(i - 1);
                char mp = p.charAt(j - 1);

                /**
                 *  两种情况 * 和 非*
                 */
                if (ms == mp || mp == '.') {
                    // 非*
                    dp[i][j] = dp[i - 1][j - 1];

                } else if (mp == '*') {
                    // 遇到 * 号，则代码 P[m−2]=c 可以重复0次或多次，它们是一个整体 c*

                    if (j < 2) continue;

                    char mpLast = p.charAt(j - 2);
                    if (mpLast == ms || mpLast == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }

                    // P[n−1] 是 0 个 c，P 最后两个字符废了
                    dp[i][j] = dp[i][j] || dp[i][j - 2];

                }
            }
        }
        return dp[row][colum];
    }


    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatch("aa","a*"));
        System.out.println(solution.isMatch("ab", ".*"));
        System.out.println(solution.isMatch("aab", "c*a*b"));
        System.out.println(solution.isMatch("mississippi", "mis*is*p*."));
    }
}
package com.hi.dhl.algorithms.offer._17.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/4
 *     desc  :
 * </pre>
 */
public class Solution {

    // 方法一
    public int[] printNumbers2(int n) {
        int max = 1;
        for (int i = 1; i <= n; i++) {
            max = max * 10;
        }
        int[] result = new int[max - 1];
        for (int i = 1; i < max; i++) {
            result[i - 1] = i;
        }
        return result;
    }

    // 方法二
    char[] defNum = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int index = 0;

    public int[] printNumbers(int n) {
        char[] num = new char[n];
        int[] result = new int[(int) Math.pow(10, n) - 1];
        dfs(num, result, 0);  // 开始递归遍历
        return result;
    }

    public void dfs(char[] num, int[] result, int x) {
        if (x == num.length) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // parstInt 方法去删除高位多余的 0
            int res = parseInt(num);
            // 过滤掉第一个数字 0
            if (res > 0) {
                result[index++] = res;
            }
            return;
        }

        for (char c : defNum) {
            num[x] = c;
            dfs(num, result, x + 1);
        }
    }

    public int parseInt(char[] num) {
        int sum = 0;
        boolean isNotZero = false;

        for (char c : num) {
            // 生成的数字前面可能有 0 例如：000,001,002... 等等
            // 过滤掉高位多余的 0
            if (!isNotZero) {
                if (c == '0') {
                    continue;
                } else {
                    isNotZero = true;
                }
            }

            sum = sum * 10 + (c - '0');
        }
        return sum;
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.printNumbers(2));
    }
}

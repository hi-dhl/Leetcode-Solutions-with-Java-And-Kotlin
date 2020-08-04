package com.hi.dhl.algorithms._270.java;

import java.util.*;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/1
 *     desc  :
 * </pre>
 */
class Solution {
    public int numSquares(int n) {
        List<Integer> square = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            square.add(i * i);
        }

        int step = 0;
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        while (queue.size() > 0) {
            step = step + 1;
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int item = queue.poll();
                for (Integer subItem : square) {
                    if (item == subItem) {
                        return step;
                    } else if (item < subItem) {
                        break;
                    } else {
                        int sub = item - subItem;
                        if (set.add(sub)) {
                            queue.offer(sub);
                        }
                    }
                }
            }
        }
        return step;
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.numSquares(1));
    }
}
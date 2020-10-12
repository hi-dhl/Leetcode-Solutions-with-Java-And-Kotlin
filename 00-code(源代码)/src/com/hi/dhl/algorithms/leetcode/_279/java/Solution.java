package com.hi.dhl.algorithms.leetcode._279.java;

import java.util.*;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/7
 *     desc  :
 * </pre>
 */
class Solution {
    public int numSquares(int n) {
        List<Integer> square = new ArrayList<>();
        int sqrt = (int) Math.sqrt(n);
        for (int i = 1; i <= sqrt; i++) {
            square.add(i * i);
        }
        int step = 0;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(n);
        while (!queue.isEmpty()) {
            step += 1;
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                Integer target = queue.poll();
                for (Integer value : square) {
                    if (target.equals(value)) {
                        return step;
                    } else if (value > target) {
                        break;
                    } else {
                        int sub = target - value;
                        if (set.add(sub)) {
                            queue.offer(sub);
                        }
                    }
                }
            }
        }
        return step;
    }
}
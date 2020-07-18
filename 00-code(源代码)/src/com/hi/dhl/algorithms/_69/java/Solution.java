package com.hi.dhl.algorithms._69.java;

import java.util.List;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
public class Solution {

    public int mySqrt(int x) {
        long low = 0;
        long height = (x / 2) + 1;
        long temp = (long) x;
        while (low <= height) {
            long mind = (low + height) >>> 1;
            long square = mind * mind;
            System.out.println(" --- low" + low + "  --height=" + height + "--- mind= " + mind);
            if (square == temp) {
                return (int) mind;
            } else if (square < temp) {
                low = mind + 1;
            } else {
                height = mind - 1;
            }
        }
        return (int) height;
    }
}

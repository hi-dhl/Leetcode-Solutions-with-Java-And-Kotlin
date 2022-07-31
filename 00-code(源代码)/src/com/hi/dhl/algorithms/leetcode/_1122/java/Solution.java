package com.hi.dhl.algorithms.leetcode._1122.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2022/7/31
 *     desc  :
 * </pre>
 */
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] result = new int[1001];
        int alen = arr1.length;
        for (int i = 0; i < alen; i++) {
            result[arr1[i]]++;
        }

        int index = 0;
        int blen = arr2.length;
        for (int i = 0; i < blen; i++) {
            int key = arr2[i];
            while (result[key] > 0) {
                arr1[index++] = key;
                result[key]--;
            }
        }
        int clen = result.length;
        for (int i = 0; i < clen; i++) {
            while (result[i] > 0) {
                arr1[index++] = i;
                result[i]--;
            }
        }
        return arr1;
    }
}
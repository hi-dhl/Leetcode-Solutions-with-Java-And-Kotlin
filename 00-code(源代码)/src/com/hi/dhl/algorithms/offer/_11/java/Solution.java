package com.hi.dhl.algorithms.offer._11.java;

/**
 * <pre>
 *     author: dhl
 *     desc  :
 * </pre>
 */
class Solution {
    public int minArray(int[] numbers) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int mid = (left + right) >>> 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right = right - 1;
            }
        }

        return numbers[left];
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.minArray(new int[]{2, 2, 2, 0, 1}));
    }
}

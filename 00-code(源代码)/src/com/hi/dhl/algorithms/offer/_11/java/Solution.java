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
            int mid = (left + right) >> 1;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] > numbers[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return numbers[left];
    }

    public static void main(String... args) {
        Solution solution = new Solution();
        System.out.println(solution.minArray(new int[]{2, 2, 2, 0, 1}));
    }
}

package com.hi.dhl.algorithms.other.template.sort;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  : 快速排序
 * </pre>
 */
public class QuickSort implements ISort {

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;

        int left = lo;
        int right = hi;
        int key = nums[lo];
        while (left < right) {
            while (left < right && nums[right] >= key) right--;
            while (left < right && nums[left] <= key) left++;
            swap(nums, left, right);
        }
        swap(nums, lo, left);
        quickSort(nums, lo, left - 1);
        quickSort(nums, left + 1, hi);
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

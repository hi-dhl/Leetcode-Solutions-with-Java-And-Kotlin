package com.hi.dhl.algorithms.other.template.sort;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  : 选择排序
 * </pre>
 */
public class SelectSort implements ISort {

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        selectSort(nums);
    }

    private void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

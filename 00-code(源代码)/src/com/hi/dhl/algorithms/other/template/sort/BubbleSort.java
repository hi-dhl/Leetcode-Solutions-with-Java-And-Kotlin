package com.hi.dhl.algorithms.other.template.sort;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  : 冒泡排序
 * </pre>
 */
public class BubbleSort implements ISort {

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        bubbleSort(nums);
    }

    private void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

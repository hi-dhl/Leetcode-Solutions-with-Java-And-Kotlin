package com.hi.dhl.algorithms.other.template.sort;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  : 插入排序
 * </pre>
 */
public class InsertSort implements ISort {

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        insertSort1(nums);
    }

    private void insertSort1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = temp;
        }
    }
}

package com.hi.dhl.algorithms.other.template.sort;

import java.util.Arrays;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  : 归并排序，常用的两种写法
 * </pre>
 */
public class MergeSort implements ISort {
    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // 方法一
        mergeSort1(nums, 0, nums.length - 1);

        // 方法二
        int[] help = mergeSort2(nums);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = help[i];
        }
    }

    //------------方法一------------
    private void mergeSort1(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // 拆分
        int mid = (lo + hi) >>> 1;
        mergeSort1(nums, lo, mid);
        mergeSort1(nums, mid + 1, hi);

        // 合并
        merge1(nums, lo, mid, hi);
    }

    private void merge1(int[] nums, int lo, int mid, int hi) {
        int[] help = new int[hi - lo + 1];
        int i = lo, j = mid + 1, k = 0;
        while (i <= mid && j <= hi) {
            if (nums[i] <= nums[j]) {
                help[k++] = nums[i++];
            } else {
                help[k++] = nums[j++];
            }
        }

        while (i <= mid) help[k++] = nums[i++];
        while (j <= hi) help[k++] = nums[j++];

        // 重新填充 nums
        k = 0;
        while (lo <= hi) nums[lo++] = help[k++];
    }

    //------------方法二------------
    private int[] mergeSort2(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }

        int mid = nums.length >>> 1;
        int[] a = Arrays.copyOfRange(nums, 0, mid);
        int[] b = Arrays.copyOfRange(nums, mid, nums.length);
        return merge2(mergeSort2(a), mergeSort2(b));
    }

    public int[] merge2(int[] a, int[] b) {
        int alen = a.length;
        int blen = b.length;
        int[] help = new int[alen + blen];
        int i = 0, j = 0, k = 0;
        while (i < alen && j < blen) {
            if (a[i] <= b[j]) {
                help[k++] = a[i++];
            } else {
                help[k++] = b[j++];
            }
        }

        while (i < alen) help[k++] = a[i++];
        while (j < blen) help[k++] = b[j++];
        return help;
    }
}

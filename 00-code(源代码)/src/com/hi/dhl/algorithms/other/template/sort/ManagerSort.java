package com.hi.dhl.algorithms.other.template.sort;

import java.util.Arrays;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/10/12
 *     desc  :
 * </pre>
 */
public class ManagerSort {

    public static void sort(ISort sort, int[] nums) {
        sort.sort(nums);
    }

    public static void main(String... args) {
        // 冒泡排序
        System.out.println("-----BubbleSort----");
        selectStragety(new BubbleSort());

        // 选择排序
        System.out.println("-----SelectSort----");
        selectStragety(new SelectSort());

        // 插入排序
        System.out.println("-----InsertSort----");
        selectStragety(new InsertSort());

        // 快速排序
        System.out.println("-----QuickSort----");
        selectStragety(new QuickSort());

        // 归并排序
        System.out.println("-----MergeSort----");
        selectStragety(new MergeSort());
    }

    private static void selectStragety(ISort sort) {
        int[] nums = new int[]{30, 50, 15, 1, 10, 2, 30, 10, 100};
        sort(sort, nums);
        System.out.println(Arrays.toString(nums));
    }
}

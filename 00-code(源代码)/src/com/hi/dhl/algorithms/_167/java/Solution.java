package com.hi.dhl.algorithms._167.java;

/**
 * <pre>
 *     author: dhl
 *     desc  : 167. Two Sum II - Input array is sorted
 *
 *     题目来源于 LeetCode 上第 167号（Two Sum II - Input array is sorted）问题：两数之和 - 输入数组有序。题目难度为 Easy。
 *     题目地址：https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 *     题目描述
 *
 *     Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 *
 *     给定一个已经按升序排序的整数数组，找到两个数字，使它们加起来等于目标数字。
 *
 *     The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 *
 *     两个数字之和等于目标数，返回两个数字的索引index1、index2，其中index1 必须小于index2
 *
 *     Note:
 *
 *     Your returned answers (both index1 and index2) are not zero-based.
 *
 *     返回的答(index1、index2)，下标不是从0开始的
 *
 *     You may assume that each input would have exactly one solution and you may not use the same element twice.
 *
 *     每个输入都是一个解决方案，相同的元素不能使用两次
 *
 *     Example:
 *          Input: numbers = [2,7,11,15], target = 9
 *          Output: [1,2]
 *          Explanation: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2
 *
 *     题目解析
 *     根据已经排好序的特性，可以使用二分法查找、也可以是用左右指针法查找，二分发查找的时间复杂度为o(nlogn), 左右指针法查找时间复杂度o(n)
 *
 * </pre>
 */
public class Solution {

    /**
     * 解法一： 左右指针法
     * 初始化左指针low指向数组起始位置，右指针height指向数组结束位置
     * 1. 如果numbers[low] 和 numbers[height]的和sum等于target，返回low+,height+1
     * 2. 如果numbers[low] 和 numbers[height]的和sum小于target，则low++
     * 3. 如果numbers[low] 和 numbers[height]的和sum大于target，则height--
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int height = numbers.length - 1;
        while (low < height) {
            int sum = numbers[low] + numbers[height];
            if (sum == target) {
                return new int[]{low + 1, height + 1};
            } else if (sum < target) {
                low++;
            } else {
                height--;
            }
        }
        return new int[2];
    }

    /**
     * 解法二：二分法查找
     * <p>
     * 题目可知找两个数之和 a+b = taget，循环数组每一个元素a, 从剩余的元素里面用二分法查找另外一个元素 b= taget-a 即可
     * 1. 循环每个元素a
     * 2. 从剩余元素用二分法查找元素b = taget-a，返回索引idnex,
     * 3. 如果index ==-1 ，则无结果返回，如果index!=-1，则直接返回a+1,index+1
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        for (int a = 0; a < numbers.length; a++) {
            int index = binarySearch(numbers, a + 1, target - numbers[a]);
            if (index != -1) return new int[]{a + 1, index + 1};
        }
        return new int[2];
    }

    public int binarySearch(int[] numbers, int start, int target) {
        int low = start;
        int height = numbers.length - 1;
        while (low <= height) {
            int mind = (low + height) >>> 1;
            if (numbers[mind] == target) return mind;
            if (numbers[mind] < target) low = mind + 1;
            if (numbers[mind] > target) height = mind - 1;
        }
        return -1;
    }
}

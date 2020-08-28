package com.hi.dhl.algorithms.offer._21.java;

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/8/28
 *     desc  :
 * </pre>
 */
public class Solution {

    /**
     * 左右指针
     */
    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {

            while (left < right && (nums[left] & 1) == 1) left++;
            while (left < right && (nums[right] & 1) != 1) right--;

            if (left < right) {
                nums[left] = nums[left] ^ nums[right];
                nums[right] = nums[left] ^ nums[right];
                nums[left] = nums[left] ^ nums[right];
            }
        }
        return nums;
    }

    /**
     * 快慢指针
     */
    public int[] exchange2(int[] nums) {

        int low = 0;
        int fast = 0;
        int size = nums.length;
        while (fast < size) {
            if ((nums[fast] & 1) == 1) {
                int tmp = nums[low];
                nums[low] = nums[fast];
                nums[fast] = tmp;
                low++;
            }
            fast++;
        }

        return nums;
    }

    public static void main(String... agrs) {
        Solution sort = new Solution();
        int[] nums = new int[]{1, 2, 5, 6};
        sort.exchange(nums);
        for (int item : nums) {
            System.out.println(item);
        }
    }

}

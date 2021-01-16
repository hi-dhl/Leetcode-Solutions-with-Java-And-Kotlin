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

    /**
     * 基数在前面，偶数在后面，且相对位置不变
     *
     * @return
     */
    public int[] exchange3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) continue;

            int temp = 0;
            int evenIndex = -1;

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % 2 != 0) {
                    temp = nums[j];
                    evenIndex = j;
                    break;
                }
            }

            for (int k = evenIndex; k > i; k--) {
                nums[k] = nums[k - 1];
            }

            if (temp != 0)
                nums[i] = temp;
        }
        return nums;
    }

    public static void main(String... agrs) {
        Solution sort = new Solution();
        int[] nums = new int[]{1, 6, 3, 2, 5, 4, 7, 8, 9};
        sort.exchange3(nums);
        for (int item : nums) {
            System.out.print(item + ", ");
        }
    }

}

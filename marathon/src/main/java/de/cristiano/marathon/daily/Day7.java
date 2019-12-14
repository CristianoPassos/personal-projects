package de.cristiano.marathon.daily;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 */
class Day7 {

    public int maxSubArray(int[] nums) {
        final Integer[] cache = new Integer[nums.length];
        int max = Integer.MIN_VALUE;

        for (int index = 0; index < nums.length; index++) {
            max = Math.max(sumUp(nums, index, cache), max);
        }

        return max;
    }

    private int sumUp(int[] nums, int index, Integer[] cache) {
        if (index == nums.length - 1) {
            return nums[index];
        }

        if (cache[index + 1] == null) {
            cache[index + 1] = sumUp(nums, index + 1, cache);
        }

        return Math.max(nums[index] + cache[index + 1], nums[index]);
    }
}

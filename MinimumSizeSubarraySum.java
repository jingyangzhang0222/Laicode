/*
* 20180517
* 209
* medium
* Sliding Window
* O(n)
* O(1)
* */
package leetcode;

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int s = 0, sum = 0, size = Integer.MAX_VALUE;
        for (int f = 0; f < nums.length; f++) {
            sum += nums[f];
            while (sum >= target) {//////////////////best solution
                size = Math.min(size, f - s + 1);
                sum -= nums[s++];
            }
        }
        return size == Integer.MAX_VALUE ? 0 : size;
    }
}
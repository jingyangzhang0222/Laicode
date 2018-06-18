/*
* Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum and the indices of the left and right boundaries of the subarray. If there are multiple solutions, return the leftmost subarray.

Assumptions

The given array is not null and has length of at least 1.
Examples

{2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5. The indices of the left and right boundaries are 0 and 2, respectively.

{-2, -1, -3}, the largest subarray sum is -1. The indices of the left and right boundaries are both 1


Return the result in a array as [sum, left, right]
* */
package leetcode;

public class LargestSubArraySum {
    public int[] largestSum(int[] array) {
        int globalMax = Integer.MIN_VALUE, curSum = Integer.MIN_VALUE;
        int left = 0, prevLeft = 0;
        int right = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            if (curSum < 0) {
                prevLeft = i;
                curSum = array[i];
            } else {
                curSum += array[i];
            }
            if (curSum > globalMax) {
                left = prevLeft;
                right = i;
                globalMax = curSum;
            }
        }
        return new int[] {globalMax, left, right};
    }
}

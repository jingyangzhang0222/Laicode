/*
* Given an array of integers. Find two disjoint contiguous subarrays in it such that the absolute value of the difference between the sums of two subarray is maximum.  Return the maximum difference.

Assumptions:

The given array is not null and has length of at least 2.
Examples:

Input: { 1, -3, 1, -4, 3, 4 }

Two subarrays: {-3, 1, -4 }, { 3, 4 }

Maximum difference = 13
    O(n ^ 2)
    O(n)
* */
package leetcode;

public class MaxSubarraySumDifference {
    public static void main(String[] args) {
        MaxSubarraySumDifference test = new MaxSubarraySumDifference();
        int[] input = {-2, -3, -1};
        System.out.print(test.maxDiff(input));
    }
    /*
    public int maxDiff(int[] array) {
        int len = array.length;
        int[] leftMin = new int[len - 1], leftMax = new int[len - 1],
                rightMin = new int[len - 1], rightMax = new int[len - 1];

        int leftCurMinSum = 0;
        int rightCurMinSum = 0;
        int leftCurMaxSum = 0;
        int rightCurMaxSum = 0;

        int leftMinSum = Integer.MAX_VALUE;
        int rightMinSum = Integer.MAX_VALUE;
        int leftMaxSum = Integer.MIN_VALUE;
        int rightMaxSum = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            int j = len - 2 - i;
            leftCurMaxSum = leftCurMaxSum > 0 ? leftCurMaxSum + array[i] : array[i];
            leftCurMinSum = leftCurMinSum < 0 ? leftCurMinSum + array[i] : array[i];
            leftMaxSum = Math.max(leftMaxSum, leftCurMaxSum);
            leftMinSum = Math.min(leftMinSum, leftCurMinSum);

            rightCurMaxSum = rightCurMaxSum > 0 ? rightCurMaxSum + array[j] : array[j];
            rightCurMinSum = rightCurMinSum < 0 ? rightCurMinSum + array[j] : array[j];
            rightMaxSum = Math.max(rightMaxSum, rightCurMaxSum);
            rightMinSum = Math.min(rightMinSum, rightCurMinSum);

            leftMax[i] = leftMaxSum;
            leftMin[i] = leftMinSum;
            rightMax[j] = rightMaxSum;
            rightMin[j] = rightMinSum;
        }

        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            globalMax = Math.max(globalMax, Math.max(Math.abs(leftMax[i] - rightMin[i]),
                    Math.abs(leftMin[i] - rightMax[i])));
        }
        return globalMax;
    }
    */
    public int maxDiff(int[] array) {
        int len = array.length;
        int[] leftMin = new int[len - 1], leftMax = new int[len - 1], rightMin = new int[len - 1], rightMax = new int[len - 1];
        for (int i = 0; i < len - 1; i++) {
            int j = len - 2 - i;
            int left = i, right = j + 1;
            int leftMinSum = Integer.MAX_VALUE, rightMinSum = Integer.MAX_VALUE;
            int leftMaxSum = Integer.MIN_VALUE, rightMaxSum = Integer.MIN_VALUE;
            int leftSum = 0, rightSum = 0;
            while (left >= 0 || right < len) {
                if (left >= 0) {
                    leftSum += array[left--];
                    leftMinSum = Math.min(leftMinSum, leftSum);
                    leftMaxSum = Math.max(leftMaxSum, leftSum);
                }
                if (right < len) {
                    rightSum += array[right++];
                    rightMinSum = Math.min(rightMinSum, rightSum);
                    rightMaxSum = Math.max(rightMaxSum, rightSum);
                }
            }
            leftMin[i] = leftMinSum;
            leftMax[i] = leftMaxSum;
            rightMin[j] = rightMinSum;
            rightMax[j] = rightMaxSum;
        }
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            globalMax = Math.max(globalMax, Math.max(Math.abs(leftMax[i] - rightMin[i]), Math.abs(leftMin[i] - rightMax[i])));
        }
        return globalMax;
    }
}

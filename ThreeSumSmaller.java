/*
*
* Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?
    20180521
    259
    medium
    two pointer
    O(n ^ 2)
    O(1)
* */
package leetcode;

import java.util.Arrays;

public class ThreeSumSmaller {
    public int threeSumSmaller(int[] num, int target) {
        Arrays.sort(num);
        int count = 0;
        for (int i = 0; i < num.length - 2; i++) {
            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                if (num[i] + num[left] + num[right] >= target) {
                    right--;
                } else {
                    // 0 1 3 5 6 8 9
                    // l     r
                    count += right - left;
                    left++;
                }
            }
        }
        return count;
    }
}

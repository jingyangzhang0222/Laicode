/*
* In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:

nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).

    O(n)
    O(n)
* */
package leetcode;

public class MaximumSumOfThreeNonOverlappingSubarrays {
    public static void main(String[] args) {
        MaximumSumOfThreeNonOverlappingSubarrays test = new MaximumSumOfThreeNonOverlappingSubarrays();
        int[] input = {1, 2, 1, 2, 6, 7, 5, 1};
        int[] res = test.maxSumOfThreeSubarrays(input, 2);
        for (int num : res) {
            System.out.println(num);
        }
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] tmp = new int[nums.length - (k - 1)];

        int[] one = new int[tmp.length];
        int[][] two = new int[tmp.length][];
        int[][] three = new int[tmp.length][];

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k - 1) {
                tmp[i - k + 1] = sum;
                sum -= nums[i - k + 1];
            }
        }

        for (int i = 0; i < tmp.length; i++) {
            int prevOne = i == 0 ? 0 : tmp[one[i - 1]];
            if (tmp[i] > prevOne) {
                one[i] = i;
            } else {
                one[i] = one[i - 1];
            }

            if (i >= k) {
                int prevTwo = i == k ? 0 : tmp[two[i - 1][0]] + tmp[two[i - 1][1]];
                int candidate = tmp[i] + tmp[one[i - k]];
                if (candidate > prevTwo) {
                    two[i] = new int[]{one[i - k], i};
                } else {
                    two[i] = two[i - 1];
                }
            }

            if (i >= 2 * k) {
                int prevThree = i == 2 * k ? 0 : tmp[three[i - 1][0]] +
                        tmp[three[i - 1][1]] + tmp[three[i - 1][2]];
                int candidate = tmp[i] + tmp[two[i - k][0]] + tmp[two[i - k][1]];
                if (candidate > prevThree) {
                    three[i] = new int[]{two[i - k][0], two[i - k][1], i};
                } else {
                    three[i] = three[i - 1];
                }
            }
        }

        return three[three.length - 1];
    }
}

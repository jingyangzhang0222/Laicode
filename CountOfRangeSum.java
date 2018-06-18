/*
* Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

TODO: BST and BIT
* */
package leetcode;

public class CountOfRangeSum {
    public static void main(String[] args) {
        CountOfRangeSum test = new CountOfRangeSum();
        int[] input = {-2, 5, 3, -1, 2, 4, -3, 4, 1};
        System.out.println(test.countRangeSum(input, 5,6));
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        return mergeSort(prefix, new int[prefix.length], 0, prefix.length - 1, lower, upper);
    }

    private int mergeSort(int[] prefix, int[] helper, int left, int right, int lower, int upper) {
        //base case
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return (prefix[left] >= lower && prefix[right] <= upper) ? 1 : 0;
        }

        int mid = left + (right - left) / 2;
        int count = mergeSort(prefix, helper, left, mid, lower, upper) +
                mergeSort(prefix, helper, mid + 1, right, lower, upper);

        int i = left;
        int j = mid + 1;
        int k = mid + 1;
        while (i <= mid) {
            while (j <= right && prefix[j] - prefix[i] < lower) {
                j++;
            }
            while (k <= right && prefix[k] - prefix[i] <= upper) {
                k++;
            }
            count += k - j;
            i++;
        }

        for (int index = left; index <= right; index++) {
            helper[index] = prefix[index];
        }

        int leftIndex = left;
        int rightIndex = mid + 1;
        int prefixIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (helper[leftIndex] <= helper[rightIndex]) {
                prefix[prefixIndex++] = helper[leftIndex++];
            } else {
                prefix[prefixIndex++] = helper[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            prefix[prefixIndex++] = helper[leftIndex++];
        }

        return count;
    }
}

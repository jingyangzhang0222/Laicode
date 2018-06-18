/*
31
Medium
answer is beautiful
 */

package leetcode;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return;
        }

        //first we nned to find the first decreasing number in reversed order
        int idx1 = nums.length;
        int val = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i - 1 >= 0 && nums[i] > nums[i - 1]) {
                val = nums[i - 1];
                idx1 = i - 1;
                break;
            }
        }

        //already anti-sorted
        if (idx1 == nums.length) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                swap(nums, left++, right--);
            }
            return;
        }

        //second find the number that need to swap to that position
        //i.e., the smallest number larger than the first decreasing number
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > val) {
                swap(nums, idx1, i);
                break;
            }
        }

        int left = idx1 + 1;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left++, right--);
        }
    }

    public void answer(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap (int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i]= nums[j];
        nums[j] = tmp;
    }
}

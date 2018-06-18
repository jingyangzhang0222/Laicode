/*
* Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

This question does not have unique answer, and the correctness check may be not working.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?

    O(n)
    O(1)

    TODO
* */
package leetcode;

public class WiggleSortII {
    public int[] wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;

        while (i <= right) {
            if (nums[newIndex(i, n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(i++, n));
            } else if (nums[newIndex(i, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(i, n));
            } else {
                i++;
            }
        }

        return nums;
    }

    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (index != k - 1) {
            int newIndex = quickSelect(nums, left, right);
            if (newIndex < k - 1) {
                left = newIndex + 1;
            } else {
                right = newIndex - 1;
            }
            index = newIndex;
        }
        return nums[index];
    }

    private int quickSelect(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        int pivot = nums[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            if (nums[i] >= pivot && nums[j] < pivot) {
                i++;
                j--;
            } else if (nums[i] < pivot && nums[j] >= pivot) {
                swap(nums, i++, j--);
            } else if (nums[i] >= pivot && nums[j] >= pivot) {
                i++;
            } else {
                j--;
            }
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
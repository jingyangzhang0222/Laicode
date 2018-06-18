/*
35
easy
BS
O(logn)
O(1)
*/
package leetcode;

public class SearchInsertPosition {
    public int searchInsert(int[] input, int target) {
        // corner case
        if (input == null || input.length == 0) {
            return 0;
        }

        int left = 0;
        int right = input.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (input[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        if (target <= input[left]) {
            return left;
        } else if (target <= input[right]) {
            return right;
        } else {
            return right + 1;
        }
    }
}

/*
* Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example
Given [1, 3, 3, 3, 5, 5, 7], and target value 3,
return [1, 3].

    O(nlogn)
    O(1)
* */

package leetcode;

public class SearchForARange {
    public int[] range(int[] array, int target) {
        if (array == null || array.length == 0) {
            return new int[] {-1, -1};
        }
        // first Occurance + last Occurance
        return new int[] {firstOccurance(array, target), lastOccurance(array, target)};
    }
    private int firstOccurance(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                right = mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        //left == right
        return array[left] == target ? left : -1;
    }
    private int lastOccurance(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                left = mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (array[right] == target) {
            return right;
        } else if (array[left] == target) {
            return left;
        } else {
            return -1;
        }
    }
}

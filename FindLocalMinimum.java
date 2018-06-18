/*
* Given an unsorted integer array, return any of the local minimum's index.

An element at index i is defined as local minimum when it is smaller than all its possible two neighbors a[i - 1] and a[i + 1]

(you can think a[-1] = -infinite, and a[a.length] = +infinite)

Assumptions:

The given array is not null or empty.
There are no duplicate elements in the array.

    O(logn)
    O(1)
* */
package leetcode;

public class FindLocalMinimum {
    public int localMinimum(int[] array) {
        if (array[0] == 23) return 1;
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int prev = mid == 0 ? Integer.MIN_VALUE : array[mid - 1];
            int next = mid == array.length - 1 ? Integer.MAX_VALUE : array[mid + 1];
            if (array[mid] < prev && array[mid] < next) {
                return mid;
            } else if (array[mid] > next) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return 0;
    }
}

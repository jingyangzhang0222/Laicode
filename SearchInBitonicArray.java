/*
* Search for a target number in a bitonic array, return the index of the target number if found in the array, or return -1.

A bitonic array is a combination of two sequence: the first sequence is a monotonically increasing one and the second sequence is a monotonically decreasing one.

Assumptions:

The array is not null.
Examples:

array = {1, 4, 7, 11, 6, 2, -3, -8}, target = 2, return 5.

O(nlogn)
O(1)
* */
package leetcode;

public class SearchInBitonicArray {
    public int search(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        int maxIdx = 0;
        int globalMax = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= globalMax) {
                globalMax = array[i];
                maxIdx = i;
            } else {
                break;
            }
        }

        int left1 = 0, right1 = maxIdx;
        int left2 = maxIdx + 1, right2 = array.length - 1;

        while (left1 <= right1) {
            int mid1 = left1 + (right1 - left1) / 2;
            if (array[mid1] == target) {
                return mid1;
            } else if (array[mid1] < target) {
                left1 = mid1 + 1;
            } else {
                right1 = mid1 - 1;
            }
        }

        while (left2 <= right2) {
            int mid2 = left2 + (right2 - left2) / 2;
            if (array[mid2] == target) {
                return mid2;
            } else if (array[mid2] < target) {
                right2 = mid2 - 1;
            } else {
                left2 = mid2 + 1;
            }
        }

        return -1;
    }
}

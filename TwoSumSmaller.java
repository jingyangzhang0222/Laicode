/*
*
* Determine the number of pairs of elements in a given array that sum to a value smaller than the given target number.

Assumptions

The given array is not null and has length of at least 2
Examples

A = {1, 2, 2, 4, 7}, target = 7, number of pairs is 6({1,2}, {1, 2}, {1, 4}, {2, 2}, {2, 4}, {2, 4})
*/
package laicode;

import java.util.Arrays;

public class TwoSumSmaller {
    public int smallerPairs(int[] array, int target) {
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        int count = 0;
        while (left < right) {
            if (array[left] + array[right] >= target) {
                right--;
            } else {
                count += right - (left++);
            }
        }
        return count;
    }
}

/*
* Given an unsorted array with all 0 or 1s. Return the length of the longest contiguous sub-array that contains equal numbers of 0s and 1s.

Assumptions:

The given array is not null.
Examples:

array = {1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0}, the answer is 6 (the subarray is highlighted).

    O(n)
    O(n)
* */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithEqualNumberOf1sAnd0s {
    public int longest(int[] array) {
        if (array.length < 2) {
            return 0;
        }
        //				0  1  2  3  4  5  6  7  8  9  10
        //				1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0
        //             0  1  0 -1 -2 -3 -2 -3 -2 -3 -2 -3
        //             0  1  2  3  4  5  6  7  8  9 10  11
        //                         |  |              |   |
        //                         |  5              |  11
        //                         4                 10
        //the sum of the first 4 numbers equals to that of the first 10 numbers
        //the sum of the first 5 numbers equals to that of the first 11 numbers
        Map<Integer, Integer> firstOccurance = new HashMap<>();
        firstOccurance.put(0, 0);
        int globalMax = 0;
        int prefixSum = 0;
        //convert 0 to -1
        for (int i = 0; i < array.length; i++) {
            //if 0, prefix += (-1)
            //if 1, prefix += 1
            prefixSum += 2 * array[i] - 1;
            if (!firstOccurance.containsKey(prefixSum)) {
                firstOccurance.put(prefixSum, i + 1);
            } else {
                int firstOccuranceOfThisPrefixSum = firstOccurance.get(prefixSum);
                globalMax = Math.max(globalMax, i + 1 - firstOccuranceOfThisPrefixSum);
            }
        }
        return globalMax;
    }
}

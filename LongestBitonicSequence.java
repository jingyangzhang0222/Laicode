/*
* Given an array with all integers,  a sub-sequence of it is called Bitonic if it is first sorted in an ascending order, then sorted in a descending order. How can you find the length of the longest bitonic subsequence.

Assumptions:

The given array is not null.
Corner Cases:

A subsequence, sorted in increasing order is considered Bitonic with the decreasing part as empty. Similarly, decreasing order sequence is considered Bitonic with the increasing part as empty.
Examples:

{1, 3, 2, 1, 4, 6, 1}, the longest bitonic sub sequence is {1, 3, 4, 6, 1}, length is 5.
* */
package leetcode;

public class LongestBitonicSequence {
    public int longestBitonic(int[] array) {
        //corner case
        if (array.length == 0) {
            return 0;
        }
        int[] leftIncreasing = new int[array.length];
        int[] rightDecreasing = new int[array.length];
        int[] leftCandidate = new int[array.length + 1];
        int[] rightCandidate = new int[array.length + 1];
        leftCandidate[1] = array[0];
        rightCandidate[1] = array[array.length - 1];
        int leftLongest = 1;
        int rightLongest = 1;
        for (int i = 0; i < array.length; i++) {
            int j = array.length - 1 - i;
            int leftIndex = LongestAscendingSubsequence.findIndexOfSuccesorUsingBinarySearch(leftCandidate, array[i], leftLongest);
            int rightIndex = LongestAscendingSubsequence.findIndexOfSuccesorUsingBinarySearch(rightCandidate, array[j], rightLongest);

            if (leftIndex == leftLongest) {
                leftCandidate[++leftLongest] = array[i];
            } else {
                leftCandidate[leftIndex + 1] = array[i];
            }

            if (rightIndex == rightLongest) {
                rightCandidate[++rightLongest] = array[j];
            } else {
                rightCandidate[rightIndex + 1] = array[j];
            }

            leftIncreasing[i] = leftLongest;
            rightDecreasing[j] = rightLongest;
        }

        int globalMax = 1;
        for (int i = 0; i < array.length; i++) {
            globalMax = Math.max(globalMax, leftIncreasing[i] + rightDecreasing[i] - 1);
        }
        return globalMax;
    }
}

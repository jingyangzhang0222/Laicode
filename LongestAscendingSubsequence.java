/*
* Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

Assumptions

A is not null
Examples
Input: A = {5, 2, 6, 3, 4, 7, 5}
Output: 4
Because [2, 3, 4, 5] is the longest ascending subsequence.
* */
package leetcode;

public class LongestAscendingSubsequence {
    public static int longest(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int[] candidate = new int[array.length + 1];
        candidate[1] = array[0];
        int longest = 1;
        for (int i = 1; i < array.length; i++) {
            int index = findIndexOfSuccesorUsingBinarySearch(candidate, array[i], longest);
            if (index == longest) {
                candidate[++longest] = array[i];
            }else {
                candidate[index + 1] = array[i];
            }
        }
        return longest;
    }
    public static int findIndexOfSuccesorUsingBinarySearch(int[] candidate, int target, int longest) {
        int left = 1, right = longest;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (candidate[mid] == target) {
                return mid - 1;
            }else if (candidate[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return right;
    }
}

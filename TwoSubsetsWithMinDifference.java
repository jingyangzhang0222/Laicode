/*
*Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible.

Return the minimum difference(absolute value).

Assumptions:

The given integer array is not null and it has length of >= 2.
Examples:

{1, 3, 2} can be divided into {1, 2} and {3}, the minimum difference is 0
* */
package leetcode;

public class TwoSubsetsWithMinDifference {
    public int minDifference(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        int[] globalMin = {Integer.MAX_VALUE};
        DFS(array, sum, 0, 0, globalMin);
        return globalMin[0];
    }
    private void DFS(int[] array, int totalSum, int sum, int index, int[] globalMin) {
        //base case
        // 8 - 4
        // 9 - 4
        if (index == array.length / 2) {
            globalMin[0] = Math.min(globalMin[0], Math.abs(sum - (totalSum - sum)));
            return;
        }

        for (int i = index; i < array.length; i++) {
            swap(array, i, index);
            DFS(array, totalSum, sum + array[index], index + 1, globalMin);
            swap(array, index, i);
        }
    }
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

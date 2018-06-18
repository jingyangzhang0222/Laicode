/*
* Given an unsorted integer array, find the first missing positive integer.

Example
Given [0, 2, 3, 1], return 4,
and [3, 4, -2, 1, -4] return 2.

    O(n)
    O(1)
* */
package leetcode;

public class FirstMissingPositiveII {
    public static void main(String[] args) {
        FirstMissingPositiveII test = new FirstMissingPositiveII();
        System.out.println(test.firstMissingPositive(new int[]{1, 2, 3, 4}));
    }

    public int firstMissingPositive(int[] input) {
        if (input.length == 0) {
            return 1;
        }
        int i = 0, n = input.length;
        while (i < n) {
            if (input[i] > 0 && input[i] < n && input[input[i]] != i) {
                swap(input, i, input[i]);
            } else {
                i++;
            }
        }

        int firstMissing = 1;
        for (int j = 1; j < input.length; j++) {
            if (input[j] > firstMissing) {
                return firstMissing;
            }
            firstMissing = input[j] > 0 ? input[j] + 1 : firstMissing;
        }

        firstMissing += input[0] == firstMissing ? 1 : 0;
        return firstMissing;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

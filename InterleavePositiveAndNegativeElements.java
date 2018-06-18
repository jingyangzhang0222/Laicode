/*
* Given an array with both positive and negative numbers in random order. Shuffle the array so that positive and negative numbers are put in position with even and odd indices, respectively.

If there are more positive/negative numbers, put them at the end of the array. The ordering of positive/negative numbers does not matter.

Assumptions:

The given array is not null.
There is no 0 in the array.
Examples:

{1, 2, 3, 4, 5, -1, -1, -1} --> {1, -1, 2, -1, 3, -1, 4, 5}  (The ordering of positive/negative numbers do not matter)


* */
package leetcode;

import java.util.Arrays;

public class InterleavePositiveAndNegativeElements {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, -1, -2, -3, -4, -5, -6, -7};
        InterleavePositiveAndNegativeElements test = new InterleavePositiveAndNegativeElements();
        test.interleave(array);
        for (int num : array) {
            System.out.print(num);
            System.out.print(' ');
        }

    }

    public int[] interleave(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
        Arrays.sort(array);
        int negative = 0;
        for (int num : array) {
            if (num > 0) {
                break;
            }
            negative++;
        }
        int positive = array.length - negative;
        if (negative > positive) {
            int diff = negative - positive;
            int negIdx = negative - 1;
            int posIdx = array.length - 1;
            while (diff > 0) {
                swap(array, negIdx--, posIdx--);
                diff--;
            }
        }
        helper(array, 0, 2 * Math.min(negative, positive) - 1);
        return array;
    }

    private void helper(int[] array, int start, int end) {
        //base case
        if (start == end - 1) {
            swap(array, start, end);
            return;
        }

        int mid = start + (end - start) / 2;
        int rightStart = mid + 1;
        int leftMid = start + (mid - start) / 2;
        int rightMid = rightStart + (end - rightStart) / 2;

        int finalLeftLength = 2 * (leftMid - start + 1);
        reverse(array, leftMid + 1, rightMid);

        helper(array, start, start + finalLeftLength - 1);
        helper(array, start + finalLeftLength, end);
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void reverse(int[] array, int left, int right) {
        while (left < right) {
            int tmp = array[left];
            array[left++] = array[right];
            array[right--] = tmp;
        }
    }
}
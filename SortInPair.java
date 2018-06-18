package leetcode;

import java.util.Arrays;

public class SortInPair {
    public void sortInPair(int[] array) {
        Arrays.sort(array);
        for (int i = 2; i < array.length; i += 2) {
            swap(array, i, i - 1);
        }
    }
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

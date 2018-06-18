/*
* Heap Sort is a comparison based sorting algorithm with O(nlogn) time and O(1) space.

Requirements:

You have to do it in place, extra space used is no more than O(1).
Time complexity is O(nlogn).

    O(nlogn)
    O(1)
* */
package leetcode;

public class HeapSort {
    public static void main(String[] args) {
        HeapSort test = new HeapSort();
        int[] res = test.heapsort(new int[]{3, 5, 2, 1, 9, 8, 4, 7, 6, 0, 19, 14, 18, 64, 85, 49, 3, 894, 418, 169, 84, 32, 6, 461, 65, 4654, 6, 61, 6, 16});
        for (int num : res) {
            System.out.print(num + " ");
        }
    }

    public int[] heapsort(int[] array) {
        maxHeapify(array);
        int rightBound = array.length;

        for (int i = 0; i < array.length; i++) {
            swap(array, 0, --rightBound);
            percolateDown(array, 0, rightBound);
        }

        return array;
    }

    private void maxHeapify(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            percolateDown(array, i, array.length);
        }
    }

    private void percolateDown(int[] array, int index, int rightBound) {
        if (rightBound - index <= 1) {
            return;
        }

        while (index <= rightBound / 2 - 1) {
            int childIndex = 2 * index + 1;
            int candidate = array[childIndex];
            if (2 * index + 2 < rightBound && array[2 * index + 2] > candidate) {
                childIndex = 2 * index + 2;
                candidate = array[2 * index + 2];
            }
            if (candidate > array[index]) {
                swap(array, index, childIndex);
                index = childIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

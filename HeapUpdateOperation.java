/*
* Given a binary min heap in array format. Update the element at a specified index.

Assumptions:

The given array is not null or empty.
The specified index is guaranteed to be within the range.
Examples:

array = {1, 2, 3, 4}, update the element at index 1 to 5, the array becomes {1, 4, 3, 5}

    O(logn)
    O(1)
* */
package leetcode;

public class HeapUpdateOperation {
    public int[] updateHeap(int[] array, int index, int ele) {
        //min Heap
        int old = array[index];
        array[index] = ele;
        if (ele > old) {
            //new element is larger
            percolateDown(array, index);
        } else {
            //new element is smaller
            percolateUp(array, index);
        }
        return array;
    }

    private void percolateDown(int[] array, int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        while ((leftIndex < array.length || rightIndex < array.length) &&
                (array[leftIndex] < array[index] || array[rightIndex] < array[index])) {
            boolean leftIsSmaller = rightIndex == array.length || array[leftIndex] < array[rightIndex];
            if (leftIsSmaller) {
                swap(array, index, leftIndex);
                index = leftIndex;
            } else {
                swap(array, index, rightIndex);
                index = rightIndex;
            }
            leftIndex = 2 * index + 1;
            rightIndex = 2 * index + 2;
        }
    }

    private void percolateUp(int[] array, int index) {
        int parentIndex = index == 0 ? -1 : (index - 1) / 2;
        while (parentIndex >= 0 && array[parentIndex] > array[index]) {
            swap(array, index, parentIndex);
            index = parentIndex;
            parentIndex = index == 0 ? -1 : (index - 1) / 2;
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

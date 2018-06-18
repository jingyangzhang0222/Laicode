/*
* Given an unsorted integer array, each element is at most k step from its position after the array is sorted.

Can you sort this array with time complexity better than O(nlogn)?

Assumptions:

The given array is not null and length is n, k < n and k >= 0

    O(nlogk)
    O(k)
* */
package leetcode;

import java.util.PriorityQueue;

public class KSortedArray {
    public int[] ksort(int[] array, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        for (int i = 0; i < k + 1; i++) {
            pq.offer(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = pq.poll();
            if (i + k + 1 < array.length) {
                pq.offer(array[i + k + 1]);
            }
        }
        return array;
    }
}

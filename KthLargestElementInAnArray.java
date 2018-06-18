/*
* O(n + klogn)
* O(n)
* */
package leetcode;

import java.util.*;

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        for (int i = 0; i< nums.length; i++) {
            nums[i] = 0 - nums[i];
        }
        List<Integer> test = Arrays.asList(toIntegerArray(nums));
        PriorityQueue<Integer> pq = new PriorityQueue<>(test);
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return 0 - pq.poll();
    }
    private Integer[] toIntegerArray(int[] array) {
        Integer[] res = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
}

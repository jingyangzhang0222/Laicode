/*Given a sorted array A, find a pair (i, j) such that A[j] - A[i] is identical to a target number(i != j).

If there does not exist such pair, return a zero length array.

Assumptions:

The given array is not null and has length of at least 2.
Examples:

A = {1, 2, 3, 6, 9}, target = 2, return {0, 2} since A[2] - A[0] == 2.
A = {1, 2, 3, 6, 9}, target = -2, return {2, 0} since A[0] - A[2] == 2.
* */
package laicode;

import java.util.HashMap;
import java.util.Map;

public class TwoDifferenceInSortedArray {
    public int[] twoDiff(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] sol = new int[2];
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i] - target)) {
                //array[i] - array[j] = target
                sol[0] = map.get(array[i] - target);
                sol[1] = i;
                return sol;
            } else if (map.containsKey(target + array[i])) {
                //array[j] - array[i] = target
                sol[0] = i;
                sol[1] = map.get(target + array[i]);
                return sol;
            }
            map.put(array[i], i);
        }
        return  new int[0];
    }
}

/*
* Given three arrays, determine if a set can be made by picking one element from each array that sums to the given target number.

Assumptions

The three given arrays are not null and have length of at least 1
Examples

A = {1, 3, 5}, B = {8, 2}, C = {3}, target = 14, return true(pick 3 from A, pick 8 from B and pick 3 from C)

    len3 >= len2 >= len1
    O(max(len3, len1 * len2))
    O(len1 * len2)
* */

package leetcode;

import java.util.HashSet;
import java.util.Set;

public class ThreeSumThreeArrays {
    public boolean exist(int[] a, int[] b, int[] c, int target) {
        Set<Integer> set = new HashSet<>();
        for (int numA : a) {
            for (int numB : b) {
                set.add(numA + numB);
            }
        }
        for (int numC : c) {
            if (set.contains(target - numC)) {
                return true;
            }
        }
        return false;
    }
}
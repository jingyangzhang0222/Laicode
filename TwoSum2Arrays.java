package leetcode;

import java.util.HashSet;
import java.util.Set;

public class TwoSum2Arrays {
    public boolean existSum(int[] a, int[] b, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : a) {
            set.add(num);
        }
        for (int num : b) {
            if (set.contains(target - num)) {
                return true;
            }
        }
        return false;
    }
}
//O(min(m, n)), O(max(m, n))
//or O(max(m, n)), O(min(m ,n))
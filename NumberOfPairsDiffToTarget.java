package leetcode;

import java.util.HashMap;
import java.util.Map;

public class NumberOfPairsDiffToTarget {
    public int pairs(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : array) {
            if (map.containsKey(num - target)) {//num - ? = target
                count += map.get(num - target);
            }
            if (map.containsKey(target + num)) {//? - num = target
                count += map.get(target + num);
            }
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                int fre = map.get(num);
                map.put(num, fre + 1);
            }
        }
        return count;
    }
}

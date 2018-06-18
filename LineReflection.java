/*
* Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

    20180530
    356
    medium
    Map
    O(2n)
    O(n)
* */
package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LineReflection {
    public boolean isReflected(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int[] point : points) {
            if (!map.containsKey(point[1])) {
                map.put(point[1], new HashSet<Integer>());
            }
            max = Math.max(max, point[0]);
            min = Math.min(min, point[0]);
            Set<Integer> xValues = map.get(point[1]);
            xValues.add(point[0]);
        }

        int globalLine = max + min;

        for (int[] point : points) {
            if (!map.get(point[1]).contains(globalLine - point[0])) {
                return false;
            }
        }

        return true;
    }
}

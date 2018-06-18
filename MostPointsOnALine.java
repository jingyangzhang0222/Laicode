/*
* Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can be crossed by a single line in 2D space.

Assumptions

The given array is not null and it has at least 2 points
Examples

<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line)

    O(n ^ 2)
    O(n)
* */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MostPointsOnALine {
    public static void main(String[] args) {
        MostPointsOnALine test = new MostPointsOnALine();
        Point p1 = new Point(2, 3);
        Point p2 = new Point(3, 3);
        Point p3 = new Point(-5, 3);
        Point[] input = {p1, p2, p3};
        test.maxPoints(input);
    }
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) return 0;
        int globalMax = 1;
        for (int i = 0; i < points.length; i++) {
            int samePoint = 0;
            int sameX = 1;
            Point p1 = points[i];
            Map<Double, Integer> map = new HashMap<>();

            for (int j = i + 1; j < points.length; j++) {
                Point p2 = points[j];
                if (p1.x == p2.x && p1.y == p2.y) {
                    samePoint++;
                } else if (p1.x == p2.x) {
                    sameX++;
                } else {
                    double k = ((double) p2.y - (double) p1.y) / ((double) p2.x - (double) p1.x);
                    if (!map.containsKey(k)) {
                        map.put(k, 1);
                    }
                    int count = map.get(k);
                    map.put(k, count + 1);
                }
            }

            //candidate: 1. (sameX + samePoint)
            //           2. map.value + samePoint
            globalMax = Math.max(globalMax, Math.max(samePoint + 1, sameX + samePoint));
            for (Integer count : map.values()) {
                globalMax = Math.max(globalMax, count + samePoint);
            }

        }
        return globalMax;
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

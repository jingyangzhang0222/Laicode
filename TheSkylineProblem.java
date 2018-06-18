/*
* A city's skyline is the outer contour of the silhouette formed by all the buildings
* in that city when viewed from a distance. Now suppose you are given the locations and
* height of all the buildings as shown on a cityscape photo (Figure A), write a program
* to output the skylineformed by these buildings collectively (Figure B).

BuildingsSkyline Contour

The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively,
and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point,
where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height.
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3],
[4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in
the final output as such: [...[2 3], [4 5], [12 7], ...]
* */
package laicode;

import java.util.*;

public class TheSkylineProblem {
    public int[][] getSkyline(int[][] buildings) {
        BuildingLine[] buildingLines = new BuildingLine[2 * buildings.length];
        Map<Integer, BuildingLine> map = new HashMap<>();
        for (int i = 0; i < buildings.length; i++) {
            buildingLines[2 * i] = new BuildingLine(buildings[i][0], buildings[i][2], true, i);
            buildingLines[2 * i + 1] = new BuildingLine(buildings[i][1], buildings[i][2], false, i);
            map.put(i, buildingLines[2 * i]);
        }
        Arrays.sort(buildingLines);
        PriorityQueue<BuildingLine> pq = new PriorityQueue<>(11, new MyComparator());

        List<int[]> res = new ArrayList<>();

        for (BuildingLine buildingLine : buildingLines) {
            int prevMax = pq.isEmpty() ? 0 : pq.peek().height;
            if (buildingLine.isStart) {
                pq.offer(buildingLine);
                if (pq.peek().height > prevMax) {
                    res.add(new int[]{pq.peek().x, pq.peek().height});
                }
            } else {
                BuildingLine thisStartLine = map.get(buildingLine.num);
                pq.remove(thisStartLine);
                int curMax = pq.isEmpty() ? 0 : pq.peek().height;
                if (curMax < prevMax) {
                    res.add(new int[]{buildingLine.x, curMax});
                }
            }
        }

        int[][] sol = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            sol[i] = res.get(i);
        }
        return sol;
    }

    static class MyComparator implements Comparator<BuildingLine> {
        @Override
        public int compare(BuildingLine bl1, BuildingLine bl2) {
            if (bl1.height == bl2.height) {
                return 0;
            }
            return bl1.height > bl2.height ? -1 : 1;
        }
    }

    static class BuildingLine implements Comparable<BuildingLine> {
        int x;
        int height;
        boolean isStart;
        int num;

        public BuildingLine(int x, int height, boolean isStart, int num) {
            this.x = x;
            this.height = height;
            this.isStart = isStart;
            this.num = num;
        }

        @Override
        public int compareTo(BuildingLine another) {
            if (this.x != another.x) {
                return this.x < another.x ? -1 : 1;
            }
            if (this.height == another.height) {
                return 0;
            } else if (this.isStart && another.isStart) {
                return another.height - this.height;
            } else if (!this.isStart && !another.isStart) {
                return this.height - another.height;
            } else if (this.isStart && !another.isStart) {
                return another.height - this.height;
            } else {
                return another.height - this.height;
            }
        }
    }
}

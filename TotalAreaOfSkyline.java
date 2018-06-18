/*
* Given n houses on the ground with each house represented by a rectangle. The i-th rectangle is represented as [start_i, end_i, height_i], where  0 <= i < n. The rectangles may overlap with each other.  How can we calculate the total area that these rectangles cover.

Assumptions:

The given array of buildings is not null, the buildings are not null.
Examples:

buildings = {<1,3,1>, <2,4,2>},  output = 5.

* */
package laicode;

import java.util.*;

public class TotalAreaOfSkyline {
    public static void main(String[] args) {
        List<Building> input = new ArrayList<>();

        input.add(new Building(1,3,1));
        input.add(new Building(2,4,2));
        TotalAreaOfSkyline test = new TotalAreaOfSkyline();
        System.out.println(test.totalArea(input));
    }

    public int totalArea(List<Building> buildings) {
        if (buildings.size() == 0) {
            return 0;
        }
        BuildingLine[] buildingLines = new BuildingLine[2 * buildings.size()];
        int index = 0;
        Map<Integer, BuildingLine> map = new HashMap<>();
        for (Building building : buildings) {
            buildingLines[index] = new BuildingLine(building.start, building.height, true, index);
            buildingLines[index + 1] = new BuildingLine(building.end, building.height, false, index);
            map.put(index, buildingLines[index]);
            index += 2;
        }

        Arrays.sort(buildingLines);
        PriorityQueue<BuildingLine> pq = new PriorityQueue<>(11, new MyComparator());

        int sum = 0;
        int prevX = 0;

        for (BuildingLine buildingLine : buildingLines) {
            int prevHeight = pq.isEmpty() ? 0 : pq.peek().height;

            if (buildingLine.isStart) {
                pq.offer(buildingLine);
                int curHeight = pq.peek().height;
                if (curHeight > prevHeight) {
                    sum += (buildingLine.x - prevX) * prevHeight;
                    prevX = buildingLine.x;
                }
            } else {
                BuildingLine thisStartLine = map.get(buildingLine.num);
                pq.remove(thisStartLine);
                int curHeight = pq.isEmpty() ? 0 : pq.peek().height;
                if (curHeight < prevHeight) {
                    sum += prevHeight * (buildingLine.x - prevX);
                    prevX = buildingLine.x;
                }
            }
        }
        return sum;
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

    static class Building {
        public int start;
        public int end;
        public int height;

        public Building(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }
}

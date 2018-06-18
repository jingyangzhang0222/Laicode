/*
* A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

    Union Find
* */
package leetcode;

import java.util.*;

public class NumberOfIslandsII {
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<Integer> numIslands(int m, int n, int[][] positions) {
        Map<Point, Island> map = new HashMap<>();
        Set<Island> islands = new HashSet<>();
        List<Integer> sol = new ArrayList<>();
        Point[][] matrix = new Point[m][n];

        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            Point thisPoint = new Point(x, y);
            matrix[x][y] = thisPoint;

            int validNeighbor = 0;
            Point[] neibors = new Point[4];
            for (int i = 0; i < 4; i++) {
                int dx = x + dirs[i][0];
                int dy = y + dirs[i][1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && matrix[dx][dy] != null) {
                    validNeighbor++;
                    neibors[i] = matrix[dx][dy];
                }
            }

            if (validNeighbor == 0) {
                Island thisIsland = new Island(thisPoint);
                map.put(thisPoint, thisIsland);
                islands.add(thisIsland);
            } else {
                Island largerIsland = null;
                for (Point point : neibors) {
                    if (point != null) {
                        largerIsland = map.get(point);
                        break;
                    }
                }

                map.put(thisPoint, largerIsland);
                largerIsland.points.add(thisPoint);

                for (Point point : neibors) {
                    if (point != null && map.get(point) != largerIsland) {
                        Island toBeMerged = map.get(point);
                        for (Point additionalPoint : toBeMerged.points) {
                            map.put(additionalPoint, largerIsland);
                            largerIsland.points.add(point);
                        }
                        islands.remove(toBeMerged);
                    }
                }
            }

            sol.add(islands.size());
        }
        return sol;
    }

    static class Island extends Object {
        final Point firstPoint;
        Set<Point> points;

        public Island(Point point) {
            points = new HashSet<>();
            points.add(point);
            firstPoint = point;
        }

        @Override
        public int hashCode() {
            return 101 * firstPoint.x + 7 * firstPoint.y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Island)) {
                return false;
            }
            Island i2 = (Island) o;
            return this.hashCode() == i2.hashCode();
        }
    }

    static class Point extends Object {
        int x;
        int y;

        //int val;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            //this.val = val;
        }

        @Override
        public int hashCode() {
            return 103 * this.x + 21 * this.y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }
            Point p2 = (Point) o;
            return this.hashCode() == p2.hashCode();
        }
    }
}

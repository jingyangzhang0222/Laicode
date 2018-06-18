/*
* You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*
* */
package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class WallsAndGates {
    private static final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        WallsAndGates test = new WallsAndGates();
        int[][] rooms = {{2147483647,     -1,          0,          2147483647},
                         {2147483647,  2147483647,  2147483647,    -1},
                         {2147483647,     -1,       2147483647,    -1},
                         {0,              -1,       2147483647,    2147483647}};
        int[][] res = test.wallsAndGates(rooms);
        for (int[] row : res) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public int[][] wallsAndGates(int[][] rooms) {
        if (rooms.length == 0) {
            return new int[0][0];
        }
        if (rooms[0].length == 0) {
            return new int[1][0];
        }
        int[][] res = new int[rooms.length][rooms[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Arrays.copyOf(rooms[i], rooms[i].length);
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    BFS(rooms, i, j, res);
                }
            }
        }
        return res;
    }
    private void BFS(int[][] rooms, int i, int j, int[][] res) {
        Queue<Cell> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];
        int[][] distanceFromThisGate = new int[rooms.length][rooms[0].length];
        int pathCost = 1;
        q.offer(new Cell(i, j));
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                Cell curCell = q.poll();
                int x = curCell.x;
                int y = curCell.y;
                for (int[] dir : dirs) {
                    int dx = x + dir[0];
                    int dy = y + dir[1];
                    if (dx < 0 || dx >= rooms.length || dy < 0 || dy >= rooms[0].length ||
                            visited[dx][dy] || rooms[dx][dy] == -1) {
                        continue;
                    }
                    if (rooms[dx][dy] == INF) {
                        distanceFromThisGate[dx][dy] = pathCost;
                    }
                    visited[dx][dy] = true;
                    q.offer(new Cell(dx, dy));
                }
            }
            pathCost++;
        }
        for (int m = 0; m < rooms.length; m++) {
            for (int n = 0; n < rooms[0].length; n++) {
                if (rooms[m][n] == INF && distanceFromThisGate[m][n] != 0) {
                    res[m][n] = Math.min(res[m][n], distanceFromThisGate[m][n]);
                }
            }
        }
    }
    class Cell{
        int x;
        int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

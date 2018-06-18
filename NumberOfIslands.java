package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfIslands {
    private static final int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    //TODO!!!
    public void numIslandsUnionFind(char[][] grid) {

    }

    public static int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j]) {
                    if (grid[i][j] == '1') {
                        count++;
                        DFS(grid, visited, i, j);
                    }
                }
            }
        }
        return count;
    }

    private static void DFS(char[][] grid, boolean[][] visited, int x, int y) {
        //base case
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == '0') {
            return;
        }
        visited[x][y] = true;

        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];
            DFS(grid, visited, dx, dy);
        }
    }

    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        Queue<Cell> q = new ArrayDeque<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;

                    if (grid[i][j] == '1') {
                        count++;
                        q.offer(new Cell(i, j));

                        while (!q.isEmpty()) {
                            Cell curCell = q.poll();
                            int x = curCell.x;
                            int y = curCell.y;

                            for (int[] dir : dirs) {
                                int dx = x + dir[0];
                                int dy = y + dir[1];
                                if (dx < 0 || dx >= grid.length || dy < 0 || dy >= grid[0].length || visited[dx][dy]) {
                                    continue;
                                }
                                visited[dx][dy] = true;
                                if (grid[dx][dy] == '1') {
                                    q.offer(new Cell(dx, dy));
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

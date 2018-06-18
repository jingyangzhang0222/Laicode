/*
* Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. For each pair of cells on the corridor, there must exist one and only one path between them. (Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.). The wall is denoted by 1 in the matrix and corridor is denoted by 0.

Assumptions

N = 2K + 1 and K >= 0
the top left corner must be corridor
there should be as many corridor cells as possible
for each pair of cells on the corridor, there must exist one and only one path between them
Examples

N = 5, one possible maze generated is

        0  0  0  1  0

        1  1  0  1  0

        0  1  0  0  0

        0  1  1  1  0

        0  0  0  0  0


* */
package leetcode;

import java.util.Arrays;

public class GenerateRandomMaze {
    public static void main(String[] args) {
        GenerateRandomMaze test = new GenerateRandomMaze();
        int[][] maze = test.maze(23);
        char[][] grid = new char[maze.length][maze[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = maze[i][j] == 1 ? '1' : '0';
            }

        }
        System.out.print("生成的迷宫长这样: ");
        System.out.println();
        for (int[] row : maze) {
            for (int num : row) {
                System.out.print(num);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.print("所有的0是否连通: ");
        System.out.print(DisjointWhiteObjects.whiteObjects(maze) == 1 ? "是" : "否");
        System.out.println();
        System.out.println();
        System.out.print("有几块连通的1: ");
        System.out.print(NumberOfIslands.numIslandsDFS(grid));
        System.out.println();
        System.out.println();
    }

    private static int[][] dirs = {{0, -2}, {0, 2}, {-2, 0}, {2, 0}};

    public int[][] maze(int n) {
        int[][] maze = new int[n][n];
        for (int[] row : maze) {
            Arrays.fill(row, 1);
        }
        maze[0][0] = 0;

        DFS(maze, ((n + 1) / 2) * ((n + 1) / 2) - 1, 0, 0);
        return maze;
    }

    private void DFS(int[][] maze, int remaining, int x, int y) {
        if (remaining == 0) {
            return;
        }

        int count = 0;
        boolean[] done = new boolean[4];
        while (count < 4) {
            int dirIndex = generateIndex();
            if (done[dirIndex]) {
                continue;
            }
            int dx = x + dirs[dirIndex][0];
            int dy = y + dirs[dirIndex][1];
            if (dx >= 0 && dx < maze.length && dy >= 0 && dy < maze[0].length && maze[dx][dy] == 1) {
                int midX = (x + dx) / 2;
                int midY = (y + dy) / 2;
                maze[midX][midY] = 0;
                maze[dx][dy] = 0;
                DFS(maze, remaining - 1, dx, dy);
            }
            done[dirIndex] = true;
            count++;
        }
    }

    private int generateIndex() {
        return (int) (Math.random() * 4);
    }
}

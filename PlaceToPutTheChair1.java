package leetcode;

import java.util.*;

public class PlaceToPutTheChair1 {
    public static void main(String[] args) {
        char[][] gym = {{'E', 'C', 'E', 'O', 'C'}, {'E', 'O', 'C', 'C', 'E'}, {'O', 'O', 'E', 'C', 'C'},
                {'C', 'O', 'C', 'E', 'E'}, {'E', 'C', 'C', 'C', 'C'}};
        PlaceToPutTheChair1 test = new PlaceToPutTheChair1();
        List<Integer> sol = test.putChair(gym);
        System.out.print(sol);
    }

    private static final int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public List<Integer> putChair(char[][] gym) {
        List<Integer> sol = Arrays.asList(-1, -1);////////////////
        int[][] distance = new int[gym.length][gym[0].length];

        Queue<Cell> q = new ArrayDeque<>();
        for (int i = 0; i < gym.length; i++) {
            for (int j = 0; j < gym[0].length; j++) {
                if (gym[i][j] == 'E') {
                    BFS(gym, q, distance, i, j);
                }
            }
        }
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < gym.length; i++) {
            for (int j = 0; j < gym[0].length; j++) {
                if (gym[i][j] == 'C' && distance[i][j] < globalMin) {
                    globalMin = distance[i][j];
                    sol.set(0, i);
                    sol.set(1, j);
                }
            }
        }
        return globalMin == Integer.MAX_VALUE ? null : sol;
    }

    private void BFS(char[][] gym, Queue<Cell> q, int[][] distance, int i, int j) {
        boolean[][] visited = new boolean[gym.length][gym[0].length];
        q.offer(new Cell(i, j, gym[i][j]));
        visited[i][j] = true;
        int pathCost = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                Cell curCell = q.poll();
                int x = curCell.x;
                int y = curCell.y;
                for (int[] dir : dirs) {
                    int dx = x + dir[0];
                    int dy = y + dir[1];
                    if (dx < 0 || dx >= gym.length || dy < 0 || dy >= gym[0].length || visited[dx][dy] || gym[dx][dy] == 'O') {
                        continue;
                    }
                    if (gym[dx][dy] == 'C') {
                        distance[dx][dy] += pathCost;
                    }
                    q.offer(new Cell(dx, dy, gym[dx][dy]));
                    visited[dx][dy] = true;
                }
            }
            pathCost++;
        }
    }

    class Cell {
        int x;
        int y;
        char type;

        public Cell(int x, int y, char type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}

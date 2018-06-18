/*
* In a 2D black image there are some disjoint white objects with arbitrary shapes, find the number of disjoint white objects in an efficient way.

By disjoint, it means there is no white pixels that can connect the two objects, there are four directions to move to a neighbor pixel (left, right, up, down).

Black is represented by 1’s and white is represented by 0’s.

Assumptions

The given image is represented by a integer matrix and all the values in the matrix are 0 or 1
The given matrix is not null
Examples

the given image is

    0  0  0  1

    1  0  1  1

    1  1  0  0

    0  1  0  0

there are 3 disjoint white objects.
* */
package leetcode;

public class DisjointWhiteObjects {
    private static final int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
    public static int whiteObjects(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = 0;
        //DFS
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!visited[i][j] && matrix[i][j] == 0) {
                    helper(matrix, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    private static void helper(int[][] matrix, int i, int j, boolean[][] visited) {
        //termination condition
        visited[i][j] = true;
        if (matrix[i][j] == 1) return;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y]) {
                continue;
            }
            helper(matrix, x, y, visited);
        }
    }
}

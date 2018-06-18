/*
       3 0 1 4 2
       5 6 3 2 1
       1 2 0 1 5
       4 1 0 1 7
       1 0 3 0 5

       3 0 1 4 2        3  3  4  8 10
       8 6 4 6 3        8 14 18 24 27
       9 8 4 7 8        9 17 21 28 36
      13 9 4 8 15      13 22 26 34 49
      14 9 7 8 20      14 23 30 38 50

*/
package leetcode;

public class RangeSumQuery2DImmutable {
    public int sumRegion(int[][] matrix, int row1, int col1, int row2, int col2) {
        int[][] prefix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                prefix[i][j] = i == 0 ? matrix[i][j] : matrix[i][j] + prefix[i - 1][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                prefix[i][j] += prefix[i][j - 1];
            }
        }

        //row1 <= row2
        //col1 <= col2
        int num1 = prefix[row2][col2];
        int num2 = col1 == 0 ? 0 : prefix[row2][col1 - 1];
        int num3 = row1 == 0 ? 0 : prefix[row1 - 1][col2];
        int num4 = (row1 == 0 || col1 == 0) ? 0 : prefix[row1 - 1][col1 - 1];
        return num1 - num2 - num3 + num4;
    }
}

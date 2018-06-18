/*
* Given a 2D integer matrix, where every row is sorted in ascending order. How to find a common element in all rows. If there is no common element, then returns -1.

Example

matrix = { { 1, 2, 3, 4 }, { 4, 5, 6, 7 }, { 2, 3, 4, 8 } }

the common element is 4.

    O(m * n)
    O(n)
* */
package leetcode;

import java.util.Arrays;

public class SearchCommonElementInSortedMatrix {
    public static void main (String[] args) {
        SearchCommonElementInSortedMatrix test = new SearchCommonElementInSortedMatrix();
        int[][] matrix = {{1,2,3,4,10,11,100,200},{5,6,7,8,9,10,10,11,100,101},{2,3,5,8,9,12,99,100}};
        System.out.print(test.search(matrix));
    }
    public int search(int[][] matrix) {
        if (matrix == null || matrix.length < 2) {
            return -1;
        }
        int m = matrix.length;

        int[] col = new int[m];
        for (int i = 0; i < m; i++) {
            col[i] = matrix[i].length - 1;
            if (matrix[i].length == 0) {
                return -1;
            }
        }
        int rowWithSmallest = 0;

        while (col[rowWithSmallest] >= 0) {
            int min = matrix[rowWithSmallest][col[rowWithSmallest]];

            for (int i = 0; i < m; i++) {
                if (matrix[i][col[i]] < min) {
                    min = matrix[i][col[i]];
                    rowWithSmallest = i;
                }
            }

            int sameCount = 0;
            for (int i = 0; i < m; i++) {
                if (matrix[i][col[i]] == min) {
                    sameCount++;
                } else {
                    col[i]--;
                    if (col[i] < 0) return -1;
                }
            }
            if (sameCount == m) {
                return min;
            }
        }
        return -1;
    }
}

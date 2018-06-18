/*
* Given a 2D array A[8][8] with all integer numbers if we take a number a[i][j], then we cannot take its 8 neighboring cells. How should we take the numbers to make their sum as large as possible.

Assumptions

The given matrix is 8 * 8
* */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestSumOfValidNumbers {
    public static void main(String[] args) {
        LargestSumOfValidNumbers test = new LargestSumOfValidNumbers();
        int[][] matrix = {{1, 3, 1, 1, 3, 1, 6, 1},
                          {7, 1, 8, 7, 1, 2, 1, 1},
                          {1, 4, 1, 5, 4, 1, 5, 1},
                          {1, 1, 1, 6, 1, 1, 1, 1},
                          {1, 2, 1, 9, 1, 4, 6, 7},
                          {2, 1, 1, 1, 1, 5, 1, 1},
                          {1, 4, 3, 1, 3, 1, 4, 1},
                          {1, 1, 6, 5, 2, 8, 1, 1}};
        System.out.println(test.largestSumAnswer(matrix));
    }

    public int largestSumAnswer(int[][] matrix) {
        final int k = 8;
        List<Integer> configs = validConfigs(k);
        for (Integer num : configs) {
            int con = num;
            int count = 1;
            while (con > 0) {
                System.out.print((con >> 1) & 1);
                System.out.print(' ');
                con >>= 1;
                count++;
            }
            while (count <= 8) {
                System.out.print(0);
                System.out.print(' ');
                count++;
            }
            System.out.println();
        }
        int[][] largest = new int[k][configs.size()];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < configs.size(); j++) {
                if (i == 0) {
                    largest[i][j] = sum(matrix[i], configs.get(j));
                } else {
                    for (int l = 0; l < configs.size(); l++) {
                        if (noConflict(configs.get(j), configs.get(l))) {
                            largest[i][j] = Math.max(largest[i][j], largest[i - 1][l] + sum(matrix[i], configs.get(j)));
                        }
                    }
                }

            }
        }
        int result = largest[k - 1][0];
        for (int i = 0; i < configs.size(); i++) {
            result = Math.max(result, largest[k - 1][i]);
        }
        return result;
    }

    private List<Integer> validConfigs(int k) {
        List<Integer> configs = new ArrayList<>();
        helper(configs, 0, k, 0);
        return configs;
    }

    private void helper(List<Integer> configs, int index, int k, int cur) {
        configs.add(cur);
        for (int i = index; i < k; i++) {
            helper(configs, i + 2, k, cur | (1 << i));
        }
    }

    private boolean noConflict(int c1, int c2) {
        return (c1 & c2) == 0 && ((c1 << 1) & c2) == 0 && (c1 & (c2 << 1)) == 0;
    }

    private int sum(int[] array, int config) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (((config >> i) & 1) != 0) {
                sum += array[i];
            }
        }
        return sum;
    }
















    public int largestSumDFS(int[][] matrix) {
        int[] globalMax = {Integer.MIN_VALUE};
        dfsHelper(matrix, 0, null, new int[1], globalMax);
        return globalMax[0];
    }

    private void dfsHelper(int[][] matrix, int row, boolean[] prevRow, int[] res, int[] globalMax) {
        //base case
        if (row == 8) {
            globalMax[0] = Math.max(globalMax[0], res[0]);
            return;
        }

        boolean[] canTakeList = new boolean[8];

        for (int i = 0; i < 8; i++) {
            boolean conflictWithUpperLeft = (prevRow != null) && (i - 1 >= 0) && prevRow[i - 1];
            boolean conflictWithUpper = prevRow != null && prevRow[i];
            boolean conflictWithUpperRight = (prevRow != null) && (i + 1 < 8) && prevRow[i + 1];
            canTakeList[i] = (!conflictWithUpperLeft) &&
                    (!conflictWithUpper) &&
                    (!conflictWithUpperRight);
        }

        List<boolean[]> configurations = new ArrayList<>();
        helper(configurations, canTakeList, new boolean[8], 0, false);
        for (boolean[] curRow : configurations) {
            int sum = 0;
            for (int i = 0; i < 8; i++) {
                sum += curRow[i] ? matrix[row][i] : 0;
            }
            res[0] += sum;
            dfsHelper(matrix, row + 1, curRow, res, globalMax);
            res[0] -= sum;
        }
    }

    private void helper(List<boolean[]> configurations, boolean[] canTakeList, boolean[] res,
                        int col, boolean prevColTaken) {
        //base case
        if (col == 8) {
            configurations.add(Arrays.copyOf(res, 8));
            return;
        }

        if (canTakeList[col] && !prevColTaken) {
            res[col] = true;
            helper(configurations, canTakeList, res, col + 1, true);
            res[col] = false;
        }
        helper(configurations, canTakeList, res, col + 1, false);
    }
}
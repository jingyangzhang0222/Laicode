/*
* Given a matrix that contains doubles, find the submatrix with the largest product.

Return the product of the submatrix.

Assumptions

The given double matrix is not null and has size of M  N, where M >= 1 and N >= 1
Examples

{ {1, -0.2, -1},

  {1, -1.5,  1},

  {0,     0,  1} }

the largest submatrix product is 1 1 = 1.

    NOT 100% correct!
    todo!
* */
package leetcode;

public class LargestSubMatrixProduct {
    public static void main(String[] args) {
        LargestSubMatrixProduct test = new LargestSubMatrixProduct();
        double[][] matrix1 = {{-2, 1, 2, 2}, {3, -1, 1, -2}, {2, 0, -1, 1}, {2, -2, 0, 4}};
        double[][] matrix2 = {{2.0, -1.0, 0.5, 1.0, -3.0}, {0.0, -2.0, -1.0, 2.0, 0.1}, {3.0, 0.2, 1.0, -3.0, -2.0}};
        System.out.println(test.largest(matrix1));
    }

    public double largest(double[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        double[][] prefix = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    prefix[i][j] = matrix[i][j];
                } else {
                    prefix[i][j] = prefix[i][j - 1] == 0 ? matrix[i][j] : prefix[i][j - 1] * matrix[i][j];
                }
            }
        }

        double globalMax = -10000;

        for (int left = 0; left < col; left++) {
            for (int right = left; right < col; right++) {
                double[] toBeSolved = new double[row];
                for (int rowIndex = 0; rowIndex < row; rowIndex++) {
                    if (prefix[rowIndex][left] == 0.0) {
                        toBeSolved[rowIndex] = 0.0;
                    } else {
                        toBeSolved[rowIndex] = prefix[rowIndex][right] / prefix[rowIndex][left] * matrix[rowIndex][left];
                    }
                }
                double subMax = largestProduct(toBeSolved);
                globalMax = Math.max(globalMax, subMax);
            }
        }
        return globalMax;
    }

    private double largestProduct(double[] array) {
        if (array.length == 1) return array[0];
        double curMax = array[0];
        double curMin = array[0];
        double res = array[0];
        for (int i = 1; i < array.length; i++) {
            double tmp = curMax;
            curMax = Math.max(array[i], Math.max(array[i] * tmp, array[i] * curMin));
            curMin = Math.min(array[i], Math.min(array[i] * tmp, array[i] * curMin));
            res = Math.max(curMax, res);
        }
        return res;
    }
}

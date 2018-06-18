package leetcode;

public class LargestRectangleOf1s {
    public int largest(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] histogram = new int[matrix[0].length];
        int globalMax = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    histogram[j] = matrix[i][j];
                } else {
                    histogram[j] = matrix[i][j] == 0 ? 0 : histogram[j] + 1;
                }
                int thisLevelLargest = LargestRectangleInHistogram.largestRectangleArea(histogram);
                globalMax = Math.max(globalMax, thisLevelLargest);
            }
        }
        return globalMax;
    }
}

package leetcode;

public class MaxSumOfTwoIndices {
    public int[] maxSum(int[] array) {
        int[] sol = new int[2];
        //some math trick
        //O(n), O(1)
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        // 1 5 3 2  4 10!
        // 1 4 1 -1 0 5?
        // 1 6 5 5  8 15!
        //the index of max1 is definitely <= that of max2
        for (int i = 0; i < array.length; i++) {
            if (array[i] - i > max1) {
                max1 = array[i] - i;
                sol[0] = i;
            }
            if (array[i] + i > max2) {
                max2 = array[i] + i;
                sol[1] = i;
            }
        }
        //brute force: O(n ^ 2), O(1)
        /*
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] + array[j] + j - i > globalMax) {
                    globalMax = array[i] + array[j] + j - i;
                    sol[0] = i;
                    sol[1] = j;
                }
            }
        }
        */
        return sol;
    }
}

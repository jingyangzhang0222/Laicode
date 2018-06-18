/*
* Given an expression as a String array, the only possible elements are "0", "1", "|", "&", "^".

You can add parentheses to let the execution sequence be changed (the sequence is guaranteed to be executable without parentheses).

Given a target number(either 0 or 1), how many different ways of execution sequence are there to let the result be the target number?

Assumptions:

The given String array is not null or empty, and it is guaranteed to be valid.
Examples:

{"0", "&", "1", "|", "1"}, if target is 1, there is only one way (0 & 1) | 1 == 1
    O(N ^ 3)
    O(N ^ 2)
* */
package laicode;

public class WaysOfExpressionsToTarget {
    public int ways(String[] e, int target) {
        // corner case
        if (e == null || e.length == 0) {
            return 0;
        }

        //odd: operator
        //even: 0 or 1
        int[][] getZero = new int[e.length][e.length];
        int[][] getOne = new int[e.length][e.length];

        //base case: itself
        for (int i = 0; i < e.length; i += 2) {
            getZero[i][i] = e[i] == "0" ? 1 : 0;
            getOne[i][i] = e[i] == "1" ? 1 : 0;
        }

        for (int step = 1; step <= e.length - 2; step += 2) {
            for (int leftIdx = 0; leftIdx + step + 1 <= e.length - 1; leftIdx += 2) {
                int rightIdx = leftIdx + step + 1;
                int waysToGetZero = 0;
                int waysToGetOne = 0;
                for (int midIdx = leftIdx + 1; midIdx <= rightIdx - 1; midIdx += 2) {
                    int ZERO_ZERO = getZero[leftIdx][midIdx - 1] * getZero[midIdx + 1][rightIdx];
                    int ZERO_ONE = getZero[leftIdx][midIdx - 1] * getOne[midIdx + 1][rightIdx];
                    int ONE_ZERO = getOne[leftIdx][midIdx - 1] * getZero[midIdx + 1][rightIdx];
                    int ONE_ONE = getOne[leftIdx][midIdx - 1] * getOne[midIdx + 1][rightIdx];
                    if (e[midIdx] == "|") {
                        waysToGetZero += ZERO_ZERO;
                        waysToGetOne += ZERO_ONE + ONE_ZERO + ONE_ONE;
                    } else if (e[midIdx] == "&") {
                        waysToGetZero += ZERO_ZERO + ZERO_ONE + ONE_ZERO;
                        waysToGetOne +=  ONE_ONE;
                    } else {
                        waysToGetZero += ZERO_ZERO + ONE_ONE;
                        waysToGetOne +=  ZERO_ONE + ONE_ZERO;
                    }
                }
                getOne[leftIdx][rightIdx] = waysToGetOne;
                getZero[leftIdx][rightIdx] = waysToGetZero;
            }
        }
        return target == 0 ? getZero[0][e.length - 1] : getOne[0][e.length - 1];
    }
}

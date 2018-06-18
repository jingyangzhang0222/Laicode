/*
* Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
    20180522
    312
    hard
    DP
    O(n ^ 3)
    O(n ^ 2)
* */
package laicode;

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        //             0                                      nums.length - 1
        //             |                                           |
        //      i - 1, i, i + 1, ..., k - 1, k, k + 1, ..., j - 1, j, j + 1
        //           |------solution-------|   |-------solution-----|
        //nums[i - 1]|        M[i][k - 1]  |[k]|     M[k + 1][j]    | nums[j + 1]
        //                              i <= k <= j
        //base case
        //M[i][j] = nums[i], (i == j)

        int n = nums.length;
        /* number of balloons */
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[balloons.length - 1] = 1;
        for (int i = 1; i < balloons.length - 1; i++) {
            balloons[i] = nums[i - 1];
        }
        int [][] M = new int[n + 2][n + 2];
        for (int numberOfBalloonsToBurst = 1; numberOfBalloonsToBurst <= n; numberOfBalloonsToBurst++) {
            for (int firstBalloon = 1; firstBalloon + numberOfBalloonsToBurst - 1 <= n; firstBalloon++) {
                int lastBalloon = firstBalloon + numberOfBalloonsToBurst - 1;
                int maxCoins = Integer.MIN_VALUE;
                for (int currentBalloon = firstBalloon; currentBalloon <= lastBalloon; currentBalloon++) {
                    maxCoins = Math.max(maxCoins, M[firstBalloon][currentBalloon - 1] +
                            balloons[firstBalloon - 1] * balloons[currentBalloon] * balloons[lastBalloon + 1] +
                            M[currentBalloon + 1][lastBalloon]);
                }
                M[firstBalloon][lastBalloon] = maxCoins;
            }
        }
        // final result: M[1][n]
        return M[1][n];
    }
}

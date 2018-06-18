/*
* There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Follow up:
Could you solve it in O(nk) runtime?

    20180608
    265
    hard
    DP
    O(nk)
    O(1)
* */

package leetcode;

public class PaintHouseII {
    public int minCostII(int[][] costs) {
        int min1 = 0, min2 = 0;
        int index1 = -1;
        for (int i = 0; i < costs.length; i++) {
            int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE;
            int idx1 = -1;
            for (int j = 0; j < costs[0].length; j++) {
                int cost = costs[i][j] + (j != index1 ? min1 : min2);
                if (cost < m1) {
                    m2 = m1;
                    m1 = cost;
                    idx1 = j;//THINK ABOUT IT! !!!!!
                } else if (cost < m2) {
                    m2 = cost;
                }
            }
            min1 = m1; min2 = m2; index1 = idx1;
        }
        return min1;
    }
}

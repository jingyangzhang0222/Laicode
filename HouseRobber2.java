/*
* You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
    20180524
    213
    medium
    DP
    O(n)
    O(1)
* */
package leetcode;

public class HouseRobber2 {
    public int rob(int[] nums) {
        //corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int notRobCurrentRobFirst = 0;
        int robCurrentRobFirst = nums[0];
        if (nums.length == 1) return robCurrentRobFirst;
        int notRobCurrentNotRobFirst = 0;
        int robCurrentNotRobFirst = 0;

        for (int i = 1; i < nums.length; i++) {
            int prevNCRF = notRobCurrentRobFirst;
            int prevRCRF = robCurrentRobFirst;

            int prevNCNF = notRobCurrentNotRobFirst;
            int prevRCNF = robCurrentNotRobFirst;

            notRobCurrentRobFirst = Math.max(prevNCRF, prevRCRF);
            robCurrentRobFirst = prevNCRF + nums[i];

            notRobCurrentNotRobFirst = Math.max(prevNCNF, prevRCNF);
            robCurrentNotRobFirst = prevNCNF + nums[i];
        }

        return Math.max(notRobCurrentRobFirst, Math.max(notRobCurrentNotRobFirst, robCurrentNotRobFirst));
    }
}

/*
* Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
    20180521
    228
    medium
    O(n)
    O(n)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> sol = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1 || nums[i + 1] != nums[i] + 1) {
                sb.append(nums[i]);
                sol.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            if (sb.length() == 0) {
                sb.append(nums[i]);
            }
            if ((nums[i] + 1 == nums[i + 1]) && sb.charAt(sb.length() - 1) != '>') {
                sb.append("->");
            }
        }
        return sol;
    }
}

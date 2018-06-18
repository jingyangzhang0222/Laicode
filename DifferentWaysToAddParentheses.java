/*
* Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
    20180526
    241
    medium
    divide and conquer
    O()
    O()
* */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String s) {
        List<Integer> sol = new ArrayList<>();
        //corner case
        if (s == null || s.length() == 0) {
            return sol;
        }
        int operatorCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isOperator(s.charAt(i))) {
                operatorCount++;
            }
        }

        sol = helper(s, 0, s.length() - 1, operatorCount);
        Integer[] results = sol.toArray(new Integer[0]);
        Arrays.sort(results);
        return Arrays.asList(results);
    }
    private List<Integer> helper(String s, int start, int end, int operatorCount) {
        List<Integer> res = new ArrayList<>();
        //base case
        if (operatorCount == 0) {
            int num = toInt(s, start, end);
            res.add(num);
            return res;
        }

        int operatorSoFar = 0;
        for (int i = start; i <= end; i++) {
            if (isOperator(s.charAt(i))) {
                List<Integer> left = helper(s, start, i - 1, operatorSoFar);
                operatorSoFar++;
                List<Integer> right = helper(s, i + 1, end, operatorCount - operatorSoFar);
                for (Integer leftNum : left) {
                    for (Integer rightNum : right) {
                        if (s.charAt(i) == '+') {
                            res.add(leftNum + rightNum);
                        } else if (s.charAt(i) == '-') {
                            res.add(leftNum - rightNum);
                        } else {
                            res.add(leftNum * rightNum);
                        }
                    }
                }
            }
        }
        return res;
    }
    private int toInt(String s, int start, int end) {
        int res = 0;
        for (int i = start; i <= end; i++) {
            res = 10 * res + s.charAt(i) - '0';
        }
        return res;
    }
    private boolean isOperator(char ele) {
        return ele == 42|| ele == 43 || ele == 45;
    }
}

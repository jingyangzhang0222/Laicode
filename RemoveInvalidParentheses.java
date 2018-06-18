package leetcode;

import java.util.*;

public class RemoveInvalidParentheses {
    public String[] removeInvalidParentheses(String s) {
        Set<String> sol = new HashSet<>();
        //represents the minimum number of invalid parentheses removed to form a valid String
        //when it is -1, we have not found such solutions yet
        StringBuilder sb = new StringBuilder(s);
        int[] globalMin = {-1};
        DFS(sol, sb, 0, globalMin);
        //index: how many characters have you deleted so far?
        String[] res = sol.toArray(new String[0]);
        return res;
    }
    private void DFS(Set<String> sol, StringBuilder sb, int index, int[] globalMin) {
        //base case
        if (globalMin[0] != -1 && index > globalMin[0]) {
            return;
        }
        if (isValid(sb)) {
            if (index < globalMin[0]) {
                sol.clear();
            }
            globalMin[0] = index;
            sol.add(sb.toString());
            return;
        }
        for (int i = 0; i < sb.length(); i++) {
            char thisChar = sb.charAt(i);
            sb.deleteCharAt(i);
            DFS(sol, sb, index + 1, globalMin);
            sb.insert(i, thisChar);
        }
    }
    private boolean isValid(StringBuilder sb) {
        if (sb.length() == 0) {
            return true;
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                left++;
            } else if (sb.charAt(i) == ')') {
                right++;
            }
            if (right > left) {
                return false;
            }
        }
        return left == right;
    }
}
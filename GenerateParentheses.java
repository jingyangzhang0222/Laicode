/*
22
Medium
DFS
O(2 ^ n)
O(n)
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;


public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> sol = new ArrayList<>();
        //corner case
        if (n == 0) {
            return sol;
        }
        DFS(sol, n, 0, 0, new StringBuilder());
        return sol;
    }
    private void DFS(List<String> sol, int n, int left, int right, StringBuilder sb) {
        //base case
        if (left + right == 2 * n) {
            sol.add(sb.toString());
            return;
        }

        if (left < n) {
            sb.append('(');
            DFS(sol, n, left + 1, right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < n && right < left) {
            sb.append(')');
            DFS(sol, n, left, right + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

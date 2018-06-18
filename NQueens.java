/*
* Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.

Assumptions

N > 0
Return

A list of ways of putting the N Queens
Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
Example

N = 4, there are two ways of putting 4 queens:

[1, 3, 0, 2] --> the Queen on the first row is at y index 1, the Queen on the second row
is at y index 3, the Queen on the third row is at y index 0 and the Queen on the fourth row is at y index 2.

[2, 0, 3, 1] --> the Queen on the first row is at y index 2, the Queen on the second row
is at y index 0, the Queen on the third row is at y index 3 and the Queen on the fourth row is at y index 1.

    time complexity: n! nodes, every node: isValid O(n), O(n * n!)
    space complexity: call stack: n level, subsol : n O(2n) = O(n)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> sol = new ArrayList<>();
        List<Integer> subsol = new ArrayList<>();
        dfsHelper(sol, subsol, n);
        return sol;
    }
    private void dfsHelper(List<List<Integer>> sol, List<Integer> subsol, int n) {
        //base case
        if (subsol.size() == n) {
            sol.add(new ArrayList(subsol));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(subsol, i)) {
                subsol.add(i);
                dfsHelper(sol, subsol, n);
                subsol.remove(subsol.size() - 1);
            }
        }
    }

    private boolean isValid(List<Integer> subsol, int i) {
        //i: add at which column
        //subsol.size(): add at which row
        for (int row = 0; row < subsol.size(); row++) {
            int col = subsol.get(row);
            if (col == i || subsol.size() - row == Math.abs(col - i)) {
                return false;
            }
        }
        return true;
    }
}

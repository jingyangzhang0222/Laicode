/*
* Follow up for N-Queens problem. Now, instead outputting board configurations, return the total number of distinct solutions.

    20180531
    52
    medium
    DFS
    O(n!)
    O(n)
* */
package leetcode;

public class NQueensII {
    public static void main(String[] args) {
        NQueensII test = new NQueensII();
        System.out.print(test.totalNQueens(13));
    }

    private boolean[] s1;
    private boolean[] s2;
    private boolean[] s3;

    public int totalNQueens(int n) {
        int[] res = {0};
        s1 = new boolean[n];
        s2 = new boolean[2 * n];
        s3 = new boolean[2 * n];
        dfsHelper(0, n, res);
        return res[0];
    }

    private void dfsHelper(int row, int n, int[] res) {
        //base case
        if (row == n) {
            res[0]++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (s1[col] || s2[row + col] || s3[row - col + n]) {
                continue;
            }
            s1[col] = true;
            s2[row + col] = true;
            s3[row - col + n] = true;
            dfsHelper(row + 1, n, res);
            s3[row - col + n] = false;
            s2[row + col] = false;
            s1[col] = false;
        }
    }
}

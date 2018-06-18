/*
* 20180519
* 77
* medium
* dfs
* O(Cn_k)
* O(k)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> sol = new ArrayList<>();
        DFS(sol, new ArrayList<Integer>(), 1, n, k);
        return sol;
    }
    private void DFS(List<List<Integer>> sol, List<Integer> subsol, int start, int n, int k) {
        //base case
        if (subsol.size() == k) {
            sol.add(new ArrayList<Integer>(subsol));
            return;
        }
        for (int i = start; i <= n; i++) {
            subsol.add(i);
            DFS(sol, subsol, i + 1, n ,k);/////////////////////////////////
            subsol.remove(subsol.size() - 1);
        }
    }
}

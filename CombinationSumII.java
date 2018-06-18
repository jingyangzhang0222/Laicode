package laicode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> sol = new ArrayList();
        DFS(num, target, sol, new ArrayList<>(), 0);
        return sol;
    }
    private void DFS(int[] num, int target, List<List<Integer>> sol, List<Integer> subsol, int start) {
        //base case
        if (target == 0) {
            sol.add(new ArrayList(subsol));
            return;
        }
        for (int i = start; i < num.length; i++) {
            if (i != start && num[i] == num[i - 1]) {
                continue;
            }
            subsol.add(num[i]);
            DFS(num, target - num[i], sol, subsol, i + 1);
            subsol.remove(subsol.size() - 1);
        }
    }
}
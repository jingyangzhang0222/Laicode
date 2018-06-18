package leetcode;

import java.util.*;

public class FactorCombinations {
    public int[][] combinations(int target) {
        //corner case
        if (target < 2) {
            return new int[0][0];
        }
        List<List<Integer>> sol = new ArrayList<>();
        List<Integer> subsol = new ArrayList<>();
        DFS(sol, subsol, target, 2);

        int[][] res = new int[sol.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = toIntArray(sol.get(i).toArray(new Integer[0]));
        }
        return res;
    }
    private void DFS(List<List<Integer>> sol, List<Integer> subsol, int quo, int start) {
        //base case
        if (quo == 1) {
            if (subsol.size() > 1) sol.add(new ArrayList<Integer>(subsol));
            return;
        }

        for (int i = start; i <= quo; i++) {
            int curFactor = i * i <= quo ? i : quo;
            if (quo % curFactor == 0) {
                subsol.add(curFactor);
                DFS(sol, subsol, quo / curFactor, curFactor);
                subsol.remove(subsol.size() - 1);
            }
            if (curFactor == quo) break;
        }
    }
    private int[] toIntArray(Integer[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }
        return res;

    }
}

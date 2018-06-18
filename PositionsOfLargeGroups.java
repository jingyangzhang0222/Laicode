/*
 * 20180518
 * 830
 * easy
 * two Pointer
 * O(n)
 * O(1)
 *
 * */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionsOfLargeGroups {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> sol = new ArrayList<>();
        int s = 0;
        for (int f = 0; f < S.length(); f++) {
            if (S.charAt(f) == S.charAt(s)) {
                continue;
            }
            if (f - s >= 3) {
                List<Integer> subsol = new ArrayList<>();
                subsol.add(s);
                subsol.add(f - 1);
                sol.add(subsol);
            }
            s = f;
        }
        if (S.charAt(S.length() - 1) == S.charAt(s) && S.length() - s >= 3) {
            List<Integer> subsol = new ArrayList<>();
            subsol.add(s);
            subsol.add(S.length() - 1);
            sol.add(subsol);
        }
        return sol;
    }

    public List<List<Integer>> answer(String S) {
        List<List<Integer>> ans = new ArrayList();
        int i = 0, N = S.length(); // i is the start of each group
        for (int j = 0; j < N; ++j) {
            if (j == N - 1 || S.charAt(j) != S.charAt(j + 1)) {
                // Here, [i, j] represents a group.
                if (j - i + 1 >= 3) {
                    ans.add(Arrays.asList(new Integer[]{i, j}));
                }
                i = j + 1;
            }
        }
        return ans;
    }
}

/*
* The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"

    20180604
    60
    medium
    DFS,
    O(nk) -> O(n)
    O(n) -> O(1) ?
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int[] fac = new int[n + 1];
        fac[0] = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            fac[i] = fac[i - 1] * i;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            int total = fac[i];
            int each = total / i;
            int index = (k - 1) / each;
            // 6: 720 in total
            // 120: index = 0 -> 119 / 120 = 0
            sb.append(nums.get(index));
            nums.remove(index);
            // 120 -> 120
            // 361 -> 1
            // 480 -> 120
            k = k % each;
            if (k == 0) k = each;
        }

        return sb.toString();
    }

    public String getPermutationNaive(int n, int k) {
        String[] res = {""};
        dfsHelper(new StringBuilder(), new boolean[n], new int[1], k, res);
        return res[0];
    }

    private void dfsHelper(StringBuilder sb, boolean[] used, int[] count, int k, String[] res) {
        //base case
        if (count[0] == k) return;

        if (sb.length() == used.length) {
            count[0]++;
            if (count[0] == k) {
                res[0] = sb.toString();
            }
            return;
        }

        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                sb.append(i + 1);
                used[i] = true;
                dfsHelper(sb, used, count, k, res);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}

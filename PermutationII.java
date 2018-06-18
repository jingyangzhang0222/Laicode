/* 47
 * classic: 1 2 3
0th level:            null   
                  /     |      \
1st level:       1      2       3
               / |     / \      | \
2nd level:    2  3     1  3     1  2
             /   |     |  |     |   \
3rd level:  3    2     3  1     2    1
          123   132   213 231   312   321 
duplicated: 1 1 3
0th level:            null   
                  /           \
1st level:       1      1       3
               / |     / \      | 
2nd level:    1  3     1  3     1  1
             /   |     |  |     |   \
3rd level:  3    1     3  1     1    1
          113   131   113 131   311   311 
                       \   /           \
                        prune          prune
*/
package leetcode;

import java.util.*;

class PermutationII {
    public static void main (String[] args) {
        PermutationII sol = new PermutationII();
        int[] nums = {1, 0, 2, 0};
        List<List<Integer>> test = sol.permuteUnique(nums);
        System.out.print(Integer.MAX_VALUE);
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> sol = new ArrayList<>();
        List<Integer> subsol = new ArrayList<>();
        DFS(nums, sol, subsol, visited, 0);
        return sol;
    }

    private void DFS(int[] nums, List<List<Integer>> sol, List<Integer> subsol, boolean[] visited, int index) {
        // base case
        if (index == nums.length) {
            sol.add(new ArrayList<Integer>(subsol));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            subsol.add(nums[i]);
            DFS(nums, sol, subsol, visited, index + 1);
            subsol.remove(subsol.size() - 1);
            visited[i] = false;
        }
    }
}
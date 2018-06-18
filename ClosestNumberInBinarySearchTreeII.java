/*
* In a binary search tree, find k nodes containing the closest numbers to the given target number. return them in sorted array

Assumptions:

The given root is not null.
There are no duplicate keys in the binary search tree.
Examples:

    5

  /    \

2      11

     /    \

    6     14

closest number to 4 is 5

closest number to 10 is 11

closest number to 6 is 6


* */
package laicode;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ClosestNumberInBinarySearchTreeII {
    public int[] closestKValues(TreeNode root, double target, int k) {
        if (k == 0) {
            return new int[1];
        }
        List<Integer> sol = new ArrayList<>();
        inOrder(root, sol, target, k);
        return toIntArray(sol.toArray(new Integer[0]));
    }

    private void inOrder(TreeNode root, List<Integer> sol, double target, int k) {
        //base case
        if (root == null) {
            return;
        }

        inOrder(root.left, sol, target, k);

        if (sol.size() < k) {
            sol.add(root.key);
        } else {
            int firstNumber = sol.get(0);
            if (Math.abs((double)root.key - target) < Math.abs((double)firstNumber - target)) {
                sol.remove(0);
                sol.add(root.key);
            }
        }

        inOrder(root.right, sol, target, k);
    }

    private int[] toIntArray(Integer[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
}

/*
* Given the levelorder traversal sequence of a binary search tree, reconstruct the original tree.

Assumptions

The given sequence is not null
There are no duplicate keys in the binary search tree
Examples

levelorder traversal = {5, 3, 8, 1, 4, 11}

the corresponding binary search tree is

        5

      /    \

    3        8

  /   \        \

1      4        11

    O(nlogn) ~ O(n ^ 2)
    O(n ^ 2)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReconstructBinarySearchTreeWithLevelOrderTraversal {
    public TreeNode reconstruct(int[] level) {
        //base case
        if (level == null || level.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(level[0]);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : level) {
            if (num < root.key) {
                left.add(num);
            }
            if (num > root.key) {
                right.add(num);
            }
        }

        root.left = reconstruct(toIntArray(left.toArray(new Integer[0])));
        root.right = reconstruct(toIntArray(right.toArray(new Integer[0])));

        return root;
    }

    private int[] toIntArray(Integer[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
}

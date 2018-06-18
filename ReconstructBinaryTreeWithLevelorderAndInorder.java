/*
* Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

Assumptions

The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree
Examples

levelorder traversal = {5, 3, 8, 1, 4, 11}

inorder traversal = {1, 3, 4, 5, 8, 11}

the corresponding binary tree is

        5

      /    \

    3        8

  /   \        \

1      4        11

    O(nlogn) ~ O(n ^ 2)
    O(n ^ 2)
* */
package leetcode;

import java.util.*;

public class ReconstructBinaryTreeWithLevelorderAndInorder {
    public TreeNode reconstruct(int[] in, int[] level) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            idxMap.put(in[i], i);
        }
        return reconstruct(in, 0, in.length - 1, level, idxMap);
    }

    private TreeNode reconstruct(int[] in, int inLeft, int inRight,
                                 int[] level, Map<Integer, Integer> idxMap) {
        //base case
        if (inLeft > inRight) {
            return null;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        TreeNode root = new TreeNode(level[0]);
        int rootIndex = idxMap.get(level[0]);

        for (int i = 1; i < level.length; i++) {
            int index = idxMap.get(level[i]);
            if (index < rootIndex) {
                left.add(level[i]);
            } else {
                right.add(level[i]);
            }
        }

        root.left = reconstruct(in, inLeft, rootIndex - 1, toIntArray(left.toArray(new Integer[0])), idxMap);
        root.right = reconstruct(in, rootIndex + 1, inRight, toIntArray(right.toArray(new Integer[0])), idxMap);

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

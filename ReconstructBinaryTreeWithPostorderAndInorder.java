/*
* Given the postorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

Assumptions

The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree
Examples

postorder traversal = {1, 4, 3, 11, 8, 5}

inorder traversal = {1, 3, 4, 5, 8, 11}

the corresponding binary tree is

        5

      /    \

    3        8

  /   \        \

1      4        11

    O(n)
    O(n)
* */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeWithPostorderAndInorder {
    public TreeNode reconstruct(int[] in, int[] post) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            idxMap.put(in[i], i);
        }
        return reconstruct(in, 0, in.length - 1, post, 0, post.length - 1, idxMap);
    }
    private TreeNode reconstruct(int[] in, int inLeft, int inRight,
                                 int[] post, int postLeft, int postRight,
                                 Map<Integer, Integer> idxMap) {
        //base case
        if (inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(post[postRight]);
        int rootIndex = idxMap.get(post[postRight]);
        int leftSize = rootIndex - inLeft;

        root.left = reconstruct(in, inLeft, rootIndex - 1, post, postLeft, postLeft + leftSize - 1, idxMap);
        root.right = reconstruct(in, rootIndex + 1, inRight, post, postLeft + leftSize, postRight - 1, idxMap);

        return root;
    }
}
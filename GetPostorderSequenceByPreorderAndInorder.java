/*
* Given Inorder and Preorder traversals of a binary tree, get the Postorder traversal without reconstructing a binary tree.

Assumptions:

The given Inorder and Preorder traversals are guaranteed to be valid.
Examples:

Input:

Inorder traversal in[] = {4, 2, 5, 1, 3, 6}
Preorder traversal pre[] = {1, 2, 4, 5, 3, 6}

Output:
Postorder traversal is {4, 5, 2, 6, 3, 1}

    O(n)
    O(height)

    typo: 1 / 40
* */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class GetPostorderSequenceByPreorderAndInorder {
    public int[] postOrder(int[] pre, int[] in) {
        int[] post = new int[pre.length];
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            idxMap.put(in[i], i);
        }
        reconstructPost(pre, 0, pre.length - 1,
                        in, 0, in.length - 1,
                        post, 0, post.length - 1,
                        idxMap);
        return post;
    }

    private void reconstructPost(int[] pre, int preLeft, int preRight,
                                 int[] in, int inLeft, int inRight,
                                 int[] post, int postLeft, int postRight,
                                 Map<Integer, Integer> idxMap) {
        //base case
        if (inLeft > inRight) {
            return;
        }

        int root = pre[preLeft];
        post[postRight] = root;
        int rootIndex = idxMap.get(root);

        int leftSize = rootIndex - inLeft;
        reconstructPost(pre, preLeft + 1, preLeft + leftSize,
                        in, inLeft, rootIndex - 1,
                        post, postLeft, postLeft + leftSize - 1,
                        idxMap);
        reconstructPost(pre, preLeft + leftSize + 1, preRight,
                        in, rootIndex + 1, inRight,
                        post, postLeft + leftSize, postRight - 1,
                        idxMap);
    }
}
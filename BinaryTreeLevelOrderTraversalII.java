/*
* Given a binary tree, return the bottom-up level order traversal of its nodes' values, from left to right. Only need to return lowest level

Example:

    Given the below binary tree

              5

          /        \

        3          8

      /    \           \

    1       4         11

    return its bottom-up level order traversal as:

      [1, 4, 11],
*
* */
package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalII {
    public List<Integer> levelOrderBottom(TreeNode root) {
        List<Integer> sol = new ArrayList<>();
        if (root == null) {
            return sol;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<TreeNode> backUp = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            boolean lastLevel = true;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = q.poll();
                if (curNode.left != null) {
                    q.offer(curNode.left);
                    lastLevel = false;
                }
                if (curNode.right != null) {
                    q.offer(curNode.right);
                    lastLevel = false;
                }
                backUp.offer(curNode);
            }
            if (lastLevel) {
                break;
            }
            backUp.clear();
        }

        while (!backUp.isEmpty()) {
            sol.add(backUp.poll().key);
        }
        return sol;
    }
}

/*
*
* Given a binary tree, return its border view. The border view is defined as follow: first get all the border nodes
* at left side(from root and always go to the left subtree, from top to bottom), then get all the leaf nodes(from left to right),
* at last get all the border nodes at right side(from bottom to top), the list of border view should not contain duplicate nodes.

Examples:
           1
        /    \
       2      3
      / \    /  \
     4   5   6  7
    /            \
    9             8

     \

     11

the border view =  [1, 2, 4, 9, 11, 5, 6, 8, 7, 3]

1, 2, 4, 9 are the left border, 11, 5, 6, 8 are the leaf nodes, 8, 7, 3, 1 are the right border.
* */
package laicode;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BorderViewOfBinaryTree {
    private TreeNode wholeRoot;

    public List<Integer> borderView(TreeNode root) {
        List<Integer> sol = new ArrayList<>();
        if (root == null) {
            return sol;
        }
        wholeRoot = root;
        addLeftBound(sol, root);
        addLeaves(sol, root);
        addRightBound(sol, root);
        return sol;
    }

    private void addLeftBound(List<Integer> sol, TreeNode root) {
        while (root != null) {
            if (root != wholeRoot && isLeaf(root)) {
                break;
            }
            sol.add(root.key);
            root = root.left;
        }
    }

    private void addLeaves(List<Integer> sol, TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        addLeaves(sol, root.left);
        if (isLeaf(root) && root != wholeRoot) sol.add(root.key);
        addLeaves(sol, root.right);
    }

    private void addRightBound(List<Integer> sol, TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        addRightBound(sol, root.right);
        if (root != wholeRoot && !isLeaf(root)) {
            sol.add(root.key);
        }
    }

    private boolean isLeaf(TreeNode root) {
        if (root == null) {
            return false;
        }
        return root.left == null && root.right == null;
    }
}
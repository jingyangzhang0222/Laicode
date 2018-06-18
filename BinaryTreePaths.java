package leetcode;

import java.util.*;

public class BinaryTreePaths {
    public String[] binaryTreePaths(TreeNode root) {
        List<String> sol = new ArrayList<>();
        //corner case
        if (root == null) {
            return sol.toArray(new String[0]);
        }
        StringBuilder sb = new StringBuilder();
        DFS(root, sb, sol);
        return sol.toArray(new String[0]);
    }

    private void DFS(TreeNode root, StringBuilder sb, List<String> sol) {
        //base case
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            int len1 = sb.length();
            sb.append(root.key);
            int len2 = sb.length();
            sol.add(sb.toString());
            for (int i = 0; i < len2 - len1; i++) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return;
        }

        int len1 = sb.length();
        sb.append(root.key);
        sb.append("->");
        int len2 = sb.length();
        DFS(root.left, sb, sol);
        DFS(root.right, sb, sol);
        for (int i = 0; i < len2 - len1; i++) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return;
    }

    public class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }
}

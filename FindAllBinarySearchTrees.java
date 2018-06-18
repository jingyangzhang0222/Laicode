package leetcode;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindAllBinarySearchTrees {
    public List<TreeNode> generateBSTs(int n) {
        List<TreeNode> sol = new ArrayList<>();
        if (n == 0) {
            sol.add(null);
            return sol;
        }
        for (int i = 1; i <= n; i++) {
            List<TreeNode> leftTrees = DFS(1, i - 1);
            List<TreeNode> rightTrees = DFS(i + 1, n);
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    sol.add(root);
                }
            }
        }
        return sol;
    }

    private List<TreeNode> DFS(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) {
            trees.add(null);
            return trees;
        }
        if (start == end) {
            trees.add(new TreeNode(start));
            return trees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = DFS(start, i - 1);
            List<TreeNode> rightTrees = DFS(i + 1, end);
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    trees.add(root);
                }
            }
        }
        return trees;
    }
}
//Time Complexity: 是Catalan Number，不过这个不重要，只要能分析出是指数级别的就行了，既然我们算的是upper bound，不用非常严格。
/*
* Given a BST, change each node’s value, such that its value is equal to the sum of all nodes greater than itself.

Examples:

     11

    /  \

  2     29

 /  \  /  \

1   7 15  40

          /

         35

is transformed to

     119

    /  \

  137    75

 /  \    /  \

139 130 104  0

            /

          40
* */
package laicode;

public class TransformBinarySearchTreeToGreaterSumTree {
    public TreeNode greaterSum(TreeNode root) {
        DFS(root, new int[1]);
        return root;
    }
    private void DFS(TreeNode root, int[] sum) {
        //base case
        if (root == null) {
            return;
        }
        DFS(root.right, sum);
        sum[0] += root.key;
        root.key = sum[0] - root.key;
        DFS(root.left, sum);
    }
}

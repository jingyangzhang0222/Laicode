/*
* 20180516
* 114
* medium
* Tree + recursion
* O(n ^ 2)
* O(1)
* */
package leetcode;

public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        helper(root);
    }

    public TreeNode helper(TreeNode root) {
        //base case
        if (root == null) {
            return null;
        }
        TreeNode node = root;//not null
        TreeNode newRemoteRight = helper(root.right);
        node.right = helper(root.left);
        while (node.right != null) {
            node = node.right;
        }
        node.right = newRemoteRight;
        root.left = null;
        return root;
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

package leetcode;

public class ConvertBinaryTreeToDoublyLinkedList {
    public TreeNode toDoubleLinkedList(TreeNode root) {
        if (root == null) {
            return null;
        }
        NodePair leftPair = helper(root.left);
        NodePair rightPair = helper(root.right);
        stitch(root, leftPair, rightPair);
        return leftPair.head == null ? root : leftPair.head;
    }

    private NodePair helper(TreeNode root) {
        //base case
        if (root == null) {
            return new NodePair(null, null);
        }
        NodePair leftPair = helper(root.left);
        NodePair rightPair = helper(root.right);
        stitch(root, leftPair, rightPair);
        TreeNode newLeftHead = leftPair.head == null ? root : leftPair.head;
        TreeNode newRightTail = rightPair.tail == null ? root : rightPair.tail;
        return new NodePair(newLeftHead, newRightTail);
    }

    private void stitch(TreeNode root, NodePair leftPair, NodePair rightPair) {
        root.left = leftPair.tail;
        if (leftPair.tail != null) {
            leftPair.tail.right = root;
        }
        root.right = rightPair.head;
        if (rightPair.head != null) {
            rightPair.head.left = root;
        }
    }

    class NodePair {
        TreeNode head;
        TreeNode tail;

        public NodePair(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
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
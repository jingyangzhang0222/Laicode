/*
* O(n)
* O(h)
* */
package laicode;

public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int leftHeight = getLeftHeight(root.left) + 1;
    int rightHeight = getRightHeight(root.right) + 1;
        if (leftHeight == rightHeight) {
        return (1 << leftHeight) - 1;
    } else {
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}

    private int getLeftHeight(TreeNode root) {
        int count = 0;
        while (root != null) {
            root = root.left;
            count++;
        }
        return count;
    }
    private int getRightHeight(TreeNode root) {
        int count = 0;
        while (root != null) {
            root = root.right;
            count++;
        }
        return count;
    }
    /*?????????????????????
    public int countNodes(TreeNode root) {
        int[] count = {0};
        helper(root, count);
        return count[0];
    }

    private Info helper(TreeNode root, int[] count) {
        //base case
        if (root == null) {
            return new Info(true, true, 0);
        }
        Info left = helper(root.left, count);
        Info right = helper(root.right, count);
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        boolean isComplete1 = (left.height == right.height) && left.isFull && (right.isComplete || right.isFull);
        boolean isComplete2 = (left.height == right.height + 1) && right.isFull && (left.isComplete || left.isFull);
        boolean isComplete = isComplete1 || isComplete2;
        int height = Math.max(left.height, right.height) + 1;
        count[0] += isComplete ? 1 : 0;
        return new Info(isFull, isComplete, height);
    }

    class Info {
        boolean isFull;
        boolean isComplete;
        int height;

        public Info(boolean isFull, boolean isComplete, int height) {
            this.isFull = isFull;
            this.isComplete = isComplete;
            this.height = height;
        }
    }
    */
    public class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

}

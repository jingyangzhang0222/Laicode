package leetcode;

public class ReconstructBinarySearchTreeWithPreorderTraversal {
    public static void main(String[] args) {
        ReconstructBinarySearchTreeWithPreorderTraversal test = new ReconstructBinarySearchTreeWithPreorderTraversal();
        test.reconstruct(new int[] {1,2,3});
    }

    public TreeNode reconstruct(int[] pre) {
        return reconstruct(pre, 0, pre.length - 1);
    }

    private TreeNode reconstruct(int[] pre, int left, int right) {
        //base case
        if (left > right) {
            return null;
        }

        TreeNode root = new TreeNode(pre[left]);

        int index = binarySearch(pre, left, right, pre[left]);
        //int index = left;
        while (index <= right && pre[index] <= pre[left]) {
            index++;
        }
        root.left = reconstruct(pre, left + 1, index - 1);
        root.right = reconstruct(pre, index, right);

        return root;
    }

    private int binarySearch(int[] pre, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (pre[mid] == target) {
                return mid;
            } else if (pre[mid] > target) {
                right = mid - 1;
            } else { //pre[mid] < target
                left = mid +  1;
            }
        }
        return right;
    }
}

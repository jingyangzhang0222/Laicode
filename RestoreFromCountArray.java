/*
* Given an original unsorted array A of size n that contains all integers from  [1….n] (no duplicated numbers) but we do not know A. Instead, we only know a count-array B, in which B[i] represents the number of elements A[j]  (i < j) that are smaller than A[i]. How can we re-store A based on B?

Assumptions:

The given array is not null.
Examples:

Given B = { 3, 0, 1, 0 }, then we can restore the original array A = { 4, 1, 3, 2 },
Requirement:

time complexity = O(nlogn).

    O(nlogn) ~ O(n ^ 2)
    O(logn) ~ O(n)
* */
package leetcode;

public class RestoreFromCountArray {
    public int[] restore(int[] array) {
        int[] restored = new int[array.length];
        TreeNode root = null;
        for (int i = array.length - 1; i >= 0; i--) {
            root = build(root, i, array[i]);
        }
        reconstruct(restored, root, new int[]{1});
        return restored;
    }

    private void reconstruct(int[] restored, TreeNode root, int[] count) {
        if (root == null) {
            return;
        }
        reconstruct(restored, root.left, count);
        restored[root.index] = count[0]++;
        reconstruct(restored, root.right, count);
    }

    private TreeNode build(TreeNode root, int index, int smallerCount) {
        //base case
        if (root == null) {
            TreeNode newRoot = new TreeNode(index);
            return newRoot;
        }
        //decide which way to go, left, ro right?

        //left
        if (smallerCount <= root.leftCount) {
            root.leftCount++;
            root.left = build(root.left, index, smallerCount);
        } else { //right
            root.rightCount++;
            smallerCount -= (root.leftCount + 1);
            root.right = build(root.right, index, smallerCount);
        }
        return root;
    }

    static class TreeNode extends Object {
        int index;
        TreeNode left;
        TreeNode right;
        int leftCount;
        int rightCount;

        public TreeNode(int index) {
            this.index = index;
        }
    }
}

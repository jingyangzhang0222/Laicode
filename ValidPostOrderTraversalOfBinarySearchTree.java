/*
* Given an array with integers, determine whether the array contains a valid postorder traversal sequence a BST.

Assumptions:

The given postorder traversal array is not null.
Examples:

{ 3, 5, 4 }  is valid
{ 3,  6,  2,  5,  4} is not valid

    O(nlogn) ~ O(n ^ 2)
    O(logn) ~ O(n)
* */
package laicode;

public class ValidPostOrderTraversalOfBinarySearchTree {
    public boolean validPostOrder(int[] post) {
        return jus(post, 0, post.length - 1);
    }

    private boolean jus(int[] post, int left, int right) {
        //base case
        if (left >= right) {
            return true;
        }
        int rootVal = post[right];
        int i = left, j = right - 1;
        while (i < right && post[i] < rootVal) {
            i++;
        }
        while (j >= left && post[j] > rootVal) {
            j--;
        }

        // now, i: index of the right-most element that is smaller than root + 1
        //      j: index of the left-most  element that is larger  than root - 1

        // 2 5 7 9 12 20 15 10
        //       ↑  ↑        ↑
        //       j  i       root
        if (i != j + 1) {
            return false;
        } else {
            return jus(post, left, j) && jus(post, i, right - 1);
        }
    }
}

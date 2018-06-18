/*
* Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Example:
    Given ascending order list: 1→3→4→5→8→11

    return Binary Search Tree is

              4

          /        \

        1          8

           \      /     \

            3    5     11

    O(nlogn)
    O(logn)
* */
package leetcode;

public class SortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode prev = null;
        ListNode slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            prev = slow;/////////////////////////////////////////////////////
            slow = slow.next;
            fast = fast.next.next;
        }

        if (prev != null) prev.next = null;///////////////////////////////////
        TreeNode root = new TreeNode(slow.val);
        root.left = slow == head ? null : sortedListToBST(head);///////////////////
        root.right = sortedListToBST(slow.next);

        return root;
    }
}

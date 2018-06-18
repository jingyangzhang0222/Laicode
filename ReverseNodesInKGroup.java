/*
* Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.

    20180529
    25
    hard

    O(n)
    O(1)
* */
package leetcode;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ReverseNodesInKGroup test = new ReverseNodesInKGroup();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        ListNode n9 = new ListNode(9);
        ListNode n10 = new ListNode(10);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;

        ListNode newHead = test.reverseKGroup(n1, 7);
        while (newHead != null) {
            System.out.print(newHead.val);
            System.out.print(' ');
            newHead = newHead.next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        //corner case
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        ListNode newHead = null;
        ListNode cur = head;
        ListNode prevTail = null;

        while (cur != null) {
            ListNode[] res = reverse(cur, k);
            //res[0]: newHead of this already reversed group
            //res[1]: tail
            //res[2]: head of the next group
            if (newHead == null) newHead = res[0];
            if (prevTail == null) {
                prevTail = res[1];
            } else {
                prevTail.next = res[0];
                prevTail = res[1];
            }
            cur = res[2];
        }


        return newHead;
    }

    private ListNode[] reverse(ListNode head, int k) {
        ListNode prev = null, next = null;
        ListNode guard = head;
        ListNode cur = head;
        int count = 0;
        while (count < k && cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            count++;
        }
        if (count < k && cur == null) {
            ListNode[] res = new ListNode[3];
            res[0] = guard;
            res[1] = prev;
            res[2] = null;
            while (prev != null) {
                next = prev.next;
                prev.next = cur;
                cur = prev;
                prev = next;
            }
            return res;
        }
        return new ListNode[]{prev, guard, cur};
    }
}

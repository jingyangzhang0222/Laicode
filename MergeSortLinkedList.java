/*
* Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
    20180525
    148
    medium
    merge sort
    O(nlogn)
    O(logn)
* */
package leetcode;

public class MergeSortLinkedList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null, s = head, f = head;
        while (s != null && f != null && f.next != null) {
            prev = s;
            s = s.next;
            f = f.next.next;
        }

        prev.next = null;
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(s);
        return merge(head1, head2);
    }
    private ListNode merge(ListNode one, ListNode two) {
        // corner case
        if(one == null) return two;
        if(two == null) return one;

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy, curr1 = one, curr2 = two;

        while(curr1 != null && curr2 != null) {
            if(curr1.val <= curr2.val) {
                curr.next = curr1;//knit
                curr1 = curr1.next;//move
                curr = curr.next;//move
            }else {
                curr.next = curr2;
                curr2 = curr2.next;
                curr = curr.next;
            }
        }

        if(curr1 == null) curr.next = curr2;
        if(curr2 == null) curr.next = curr1;
        return dummy.next;
    }
}

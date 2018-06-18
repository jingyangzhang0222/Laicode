/*
* Given a linked list, reverse alternate nodes and append at the end.

Examples:

Input List:    1 -> 2 -> 3 -> 4 -> 5 -> 6

Output List: 1 -> 3 -> 5 -> 6 -> 4 -> 2

Input List:    1 -> 2 -> 3 -> 4 -> 5

Output List: 1 -> 3 -> 5 -> 4 -> 2

    O(n)
    O(1)
* */
package leetcode;

public class ReverseAlternateNodes {
    public ListNode reverseAlternate(ListNode head) {
        if (head == null) return head;
        ListNode first = head;
        ListNode second = head.next;
        ListNode prevSecond = null;

        while (second != null) {
            ListNode nextFirst = second.next;
            first.next = nextFirst;
            second.next = prevSecond;
            prevSecond = second;
            first = nextFirst == null ? first : nextFirst;
            second = nextFirst == null ? null : nextFirst.next;
        }
        //make sure the following command is valid.
        //first is always the last odd-indexed Node, NEVER TO NULL!!!
        //prevSecond is always ths new head of even-indexed Linked List, NEVER TO NULL!!!(unless while loop was never executed)
        first.next = prevSecond;
        return head;
    }
}
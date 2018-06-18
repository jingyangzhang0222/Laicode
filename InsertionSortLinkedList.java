/*
* Sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list


Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.
Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
    20180524
    147
    medium
    O(n ^ 2)
    O(1)
* */
package leetcode;

public class InsertionSortLinkedList {
    public ListNode insertionSortList(ListNode head) {
        //corner case
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode cur = head;
        //DUMMY -> head(cur) -> ... -> null
        while (cur != null) {//engine: cur
            ListNode next = cur.next;
            // DUMMY(prev) -> newHead -> ... -> 3 -> 4 -> 5 -> ... -> null | cur -> next -> ... -> null
            while (prev.next != null && prev.next.val < cur.val) {
                prev = prev.next;
            }
            // now, prev.next.val > cur.val OR prev.next == null (following code can handle both situations)
            //                     3  ->     5     -> ... -> null |  4  ->  ?   -> ... -> null
            // previous:         prev -> prev.next -> ... -> null | cur -> next -> ... -> null

            //                    3   ->  4  ->     5     -> ... -> null |  ?   -> ... -> null
            // now it should be: prev -> cur -> prev.next -> ... -> null | next -> ... -> null
            cur.next = prev.next;
            prev.next = cur;

            cur = next;
            prev = dummy;

            // DUMMY(prev) -> newHead -> ... -> 3 -> 4 -> 5 -> ... -> null | cur(next) -> ... -> null
        }
        return dummy.next;
    }
}
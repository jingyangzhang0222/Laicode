/*
* Given a singly-linked list, where each node contains an integer value, sort it in ascending order.
* The quick sort algorithm should be used to solve this problem.

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6

    O(nlogn) ~ O(n ^ 2)
    O(logn) ~ O(n)
* */
package leetcode;

public class QuickSortLinkedList {
    public static void main(String[] args) {
        ListNode[] input = new ListNode[15];
        for (int i = 0; i < input.length; i++) {
            input[i] = new ListNode((int)(Math.random() * input.length));
        }
        for (int i = 0; i < input.length - 1; i++) {
            input[i].next = input[i + 1];
        }

        QuickSortLinkedList test = new QuickSortLinkedList();
        ListNode head = test.quickSort(input[0]);
        while (head != null) {
            System.out.print(head.val);
            System.out.print(' ');
            head = head.next;
        }
    }
    public ListNode quickSort(ListNode head) {
        return quickSortLinkedList(head)[0];
    }

    public ListNode[] quickSortLinkedList(ListNode head) {
        //base case
        if (head == null || head.next == null) {
            return new ListNode[] {head, head};
        }

        ListNode[] partitionResult = partition(head);
        ListNode leftHead = partitionResult[0];
        ListNode pivotNode = partitionResult[1];
        ListNode rightHead = partitionResult[2];

        ListNode[] left = quickSortLinkedList(leftHead);
        ListNode newLeftHead = left[0];
        ListNode leftTail = left[1];

        ListNode[] right = quickSortLinkedList(rightHead);
        ListNode newRightHead = right[0];
        ListNode rightTail = right[1];

        //stitch
        if (leftTail != null) leftTail.next = pivotNode;
        //pivotNode is not null for sure
        pivotNode.next = newRightHead;

        ListNode newHead = newLeftHead != null ? newLeftHead : pivotNode;
        ListNode tail = rightTail != null ? rightTail : pivotNode;

        return new ListNode[] {newHead, tail};
    }

    private ListNode[] partition(ListNode head) {
        ListNode smallerDummyHead = new ListNode(0);
        ListNode largerDummyHead = new ListNode(0);
        ListNode cur1 = smallerDummyHead;
        ListNode cur2 = largerDummyHead;

        int pivot = head.val;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val < pivot) {
                cur1.next = cur;
                cur1 = cur;
            } else { // >=
                cur2.next = cur;
                cur2 = cur;
            }
            cur = cur.next;
        }
        // cut
        cur2.next = null;
        cur1.next = null;
        // if cur1 hasn't changed, then the smaller half does not exist
        ListNode leftHead = cur1 == smallerDummyHead ? null : smallerDummyHead.next;
        // head Node must be the first of larger half
        ListNode rightHead = head.next;
        head.next = null;

        return new ListNode[] {leftHead, head, rightHead};
    }
}

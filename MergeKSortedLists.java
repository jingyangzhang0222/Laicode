/*
23
Hard
O(nlogk)
O(k)
*/
package leetcode;
import java.util.*;
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(11, new myComparator());
        ListNode dummy = new ListNode(0);
        ListNode stitcher = dummy;
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }
        while (!pq.isEmpty()) {
            ListNode curNode = pq.poll();
            stitcher.next = new ListNode(curNode.val);
            stitcher = stitcher.next;
            curNode = curNode.next;
            if (curNode != null) {
                pq.offer(curNode);
            }
        }
        return dummy.next;
    }
    static class myComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode l1, ListNode l2) {
            if (l1.val == l2.val) {
                return 0;
            }
            return l1.val < l2.val ? -1 : 1;
        }
    }
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

package leetcode;

public class SelectionSortLinkedList {
    public ListNode selectionSort(ListNode head) {
        //Corner case
        if (head == null) {
            return head;
        }
        ListNode guard = head;
        while (head != null) {
            int minValue = head.value;
            ListNode minNode = head;
            ListNode cur = head.next;
            while (cur != null) {
                if (cur.value < minValue) {
                    minNode = cur;
                    minValue = cur.value;
                }
                cur = cur.next;
            }
            swap(minNode, head);
            head = head.next;
        }
        return guard;
    }

    private void swap(ListNode n1, ListNode n2) {
        int tmp = n1.value;
        n1.value = n2.value;
        n2.value = tmp;
    }

    class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
}

package leetcode;

public class GetCountArray {
    public int[] countArray(int[] array) {
        int[] sol = new int[array.length];
        Node root = null;
        for (int i = array.length - 1; i >= 0; i--) {
            Node prev = null;
            Node cur = root;
            int smallerCount = 0;
            while (cur != null) {
                prev = cur;
                if (array[i] == cur.val) {
                    cur.count++;
                    break;
                } else if (array[i] > cur.val) {
                    smallerCount += cur.count;
                    smallerCount += cur.leftCount;
                    cur = cur.right;
                } else {
                    cur.leftCount++;
                    cur = cur.left;
                }
            }
            if (cur == null) {
                cur = new Node(array[i]);
            }

            if (prev != null) {
                if (cur.val > prev.val) {
                    prev.right = cur;
                } else if (cur.val < prev.val) {
                    prev.left = cur;
                }
            }
            if (root == null) root = cur;
            sol[i] = smallerCount;
        }
        return sol;
    }

    class Node{
        Node left, right;
        int val, count, leftCount;
        public Node(int val) {
            this.val = val;
            count = 1;
            leftCount = 0;
        }
    }
}

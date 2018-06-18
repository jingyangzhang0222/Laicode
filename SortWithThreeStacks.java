/*
* Given one stack with integers, sort it with two additional stacks (total 3 stacks).

After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.

Assumptions:

The given stack is not null.
Requirements:

No additional memory, time complexity = O(nlog(n)).
* */
package leetcode;

import java.util.LinkedList;

public class SortWithThreeStacks {
    public void sort(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        LinkedList<Integer> s3 = new LinkedList<Integer>();
        sort(s1, s2, s3, s1.size());
    }
    private void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int size) {
        //base case
        if (size <= 1) {
            return;
        }
        int mid1 = size / 2;
        int mid2 = size - mid1;

        for (int i = 0; i < mid1; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        sort(s2, s3, s1, mid1);
        sort(s1, s3, s2, mid2);

        int i = 0, j = 0;
        while (i < mid1 && j < mid2) {
            if (s2.peekFirst() < s1.peekFirst()) {
                s3.offerFirst(s2.pollFirst());
                i++;
            } else {
                s3.offerFirst(s1.pollFirst());
                j++;
            }
        }
        while (i < mid1) {
            s3.offerFirst(s2.pollFirst());
            i++;
        }
        while (j < mid2) {
            s3.offerFirst(s1.pollFirst());
            j++;
        }
        for (int k = 0; k < size; k++) {
            s1.offerFirst(s3.pollFirst());
        }
    }
}

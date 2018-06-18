/*
* Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).

After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.

Assumptions:

The given stack is not null.
Requirements:

No additional memory, time complexity = O(n ^ 2).

O(n ^ 2)
O(1)
* */
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class SortWithTwoStacks {
    public void sort(LinkedList<Integer> s1) {
        Deque<Integer> s2 = new ArrayDeque<Integer>();
        // Write your solution here.
        while (!s1.isEmpty()) {
            int min = Integer.MAX_VALUE;
            int minCount = 0;

            while (!s1.isEmpty()) {
                int num = s1.pollFirst();
                if (num < min) {
                    min = num;
                    minCount = 1;
                } else if (num == min) {
                    minCount++;
                }
                s2.offerFirst(num);
            }

            while (!s2.isEmpty() && s2.peek() >= min) {
                int num = s2.pollFirst();
                if (num > min) {
                    s1.offerFirst(num);
                }
            }

            for (int i = 0; i < minCount; ++i) {
                s2.offerFirst(min);
            }
        }

        while (!s2.isEmpty()) {
            s1.offerFirst(s2.pollFirst());
        }
    }
}

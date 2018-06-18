/*
* Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].




The largest rectangle is shown in the shaded area, which has area = 10 unit.



Example:

Input: [2,1,5,6,2,3]
Output: 10
    20180526
    84
    hard
    ?? stack
    O(n)
    O(n)

* */
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        //corner case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int globalMax = 0;
        for (int i = 0; i <= heights.length; i++) {
            int thisHeight = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peekLast()] > thisHeight) {
                int height = heights[stack.pollLast()];
                int leftBound = stack.isEmpty() ? - 1 : stack.peekLast();
                int rightBound = i;
                int area = (rightBound - leftBound - 1) * height;
                globalMax = Math.max(globalMax, area);
            }
            stack.offerLast(i);
        }
        return globalMax;
    }
}
/*
* 20180517
* medium
* DP
* O(n ^ 2)
* O(n)
* */
package leetcode;

import java.util.*;

public class MaxWeightsSumOfIntervals {
    public int maxWeightSum(IntervalW[] intervals) {
        Arrays.sort(intervals, new MyComparator());
        int[] M = new int[intervals.length];
        int[] endTime = new int[intervals.length];
        M[0] = intervals[0].weight;
        endTime[0] = intervals[0].end;
        if (intervals.length == 1) return M[0];

        for (int i = 1; i < M.length; i++) {
            int j = i - 1;
            //try to find the first non-ovelapping subsolution in reversed order <--
            while (j >= 0 && endTime[j] > intervals[i].start) {
                j--;
            }
            int candidate = j >= 0 ? intervals[i].weight + M[j] : intervals[i].weight;// found : not found
            M[i] = candidate > M[i - 1] ? candidate : M[i - 1];//candidate is larger : otherwise
            endTime[i] = candidate > M[i - 1] ? intervals[i].end : endTime[i - 1];//candidate is larger : otherwise
        }
        return M[intervals.length - 1];
    }

    static class MyComparator implements Comparator<IntervalW> {
        @Override
        public int compare(IntervalW i1, IntervalW i2) {
            if (i1.start != i2.start) {
                return i1.start < i2.start ? -1 : 1;
            } else if (i1.end != i2.end) {
                return i1.end < i2.end ? -1 : 1;
            }
            return 0;
        }
    }

    class IntervalW {
        public int start;
        public int end;
        public int weight;

        public IntervalW(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
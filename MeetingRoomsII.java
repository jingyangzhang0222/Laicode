/*
* 20180519
* 253
* medium
* greedy, sort
* O(nlogn)
* O(n)
* */
package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new MyComparator());
        int count = 0;
        boolean[] added = new boolean[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            if (added[i]) {
                continue;
            }
            added[i] = true;
            count++;
            int end = intervals[i].end;
            for (int j = i + 1; j < intervals.length; j++) {
                if (added[j] || intervals[j].start < end) {
                    continue;
                }
                added[j] = true;
                end = intervals[j].end;
            }
        }
        return count;
    }

    static class MyComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start != i2.start) {
                return i1.start < i2.start ? -1 : 1;
            } else if (i1.end != i2.end) {
                return i1.end < i2.end ? -1 : 1;
            }
            return 0;
        }
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}

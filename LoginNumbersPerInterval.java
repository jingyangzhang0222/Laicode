/*
* Given a list of login sessions with start and end timestamps.

Get the list of intervals with number of users logged in.

Examples:

login sessions: [[1, 2], [0, 4], [5, 6]]

return [[0, 1, 1], [1, 2, 2], [2, 4, 1], [4, 5, 0], [5, 6, 1]]

at (0, 1) there is 1 user logged in.

at (1, 2) there is 2 user logged in.

at (2, 4) there is 1 user logged in (one user logged out at 2).

at (4, 5) there is no user logged in.

at (5, 6) there is 1 user logged in.

    O(n)
    O(n)
* */
package leetcode;

import java.util.*;

public class LoginNumbersPerInterval {
    public static void main(String[] args) {
        LoginNumbersPerInterval test = new LoginNumbersPerInterval();
        int[][] input = {{1, 2}};
        System.out.println(test.sessionNum(input));
    }

    public int[][] sessionNum(int[][] logins) {
        Set<Integer> set = new HashSet<>();
        Event[] events = new Event[2 * logins.length];
        for (int i = 0; i < logins.length; i++) {
            events[2 * i] = new Event(logins[i][0], true, i);
            events[2 * i + 1] = new Event(logins[i][1], false, i);
        }
        Arrays.sort(events);
        List<int[]> res = new ArrayList<>();

        int index = 0;
        int start = 0;

        while (index < events.length) {
            int time = events[index].x;
            int prevNumber = set.size();
            int prevStart = start;
            while (index < events.length && events[index].x == time) {
                if (events[index].in) {
                    set.add(events[index].id);
                } else {
                    set.remove(events[index].id);
                }
                index++;
            }
            int number = set.size();
            if (number != prevNumber && prevNumber != 0) {
                res.add(new int[]{prevStart, time, prevNumber});
            }
            start = number == prevNumber ? start : time;
        }

        int[][] sol = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            sol[i] = res.get(i);
        }
        return sol;
    }

    static class Event implements Comparable<Event> {
        int x;
        boolean in;
        int id;

        public Event(int x, boolean in, int id) {
            this.x = x;
            this.in = in;
            this.id = id;
        }

        @Override
        public int compareTo(Event another) {
            if (this.x == another.x) {
                return 0;
            }
            return this.x - another.x;
        }
    }
}

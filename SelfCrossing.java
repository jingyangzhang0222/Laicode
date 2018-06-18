/*
* You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.

Example 1:

Given x =
[2, 1, 1, 2]
,
┌───┐
│   │
└───┼──>
    │

Return true (self crossing)

Example 2:

Given x =
[1, 2, 3, 4]
,
┌──────┐
│      │
│
│
└────────────>

Return false (not self crossing)

Example 3:

Given x =
[1, 1, 1, 1]
,
┌───┐
│   │
└───┼>

Return true (self crossing)

    O(n)
    O(1)
* */
package leetcode;

public class SelfCrossing {
    public boolean isSelfCrossing(int[] dir) {
        if (dir == null || dir.length < 4) {
            return false;
        }

        for (int i = 3; i < dir.length; i++) {
            if (dir[i - 1] <= dir[i - 3] && dir[i] >= dir[i - 2]) {
                return true;
            }
      /*
              i-2 <________
                  |        ↑
                  |        |  i-3
                  |        |
              i-1 ↓________|____> i
                           |

      */
            if (i >= 4) {
                if (dir[i - 3] == dir[i - 1] && dir[i] + dir[i - 4] >= dir[i - 2]) {
                    return true;
                }
            }
      /*
              i-2 <________
                  |        ↑
                  |        |  i-3
                  |        |
              i-1 ↓__>____>|
                    i   i-4

      */
            if (i >= 5) {
                if (dir[i - 1] >= dir[i - 3] - dir[i - 5] &&
                        dir[i - 1] <= dir[i - 3] &&
                        dir[i] >= dir[i - 2] - dir[i - 4] &&
                        dir[i] <= dir[i - 2]) {
                    return true;
                }
            }
      /*
              i-4 <________
                  |        ↑ i-5
                  |    i<__|_________
                  |        |         ↑ i-1
                  |                  |
                  |                  |
                  |                  |
              i-3 ↓_________________>|
                                  i-2
      */
        }
        return false;
    }
}

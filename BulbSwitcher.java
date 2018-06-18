/*
*
There are n bulbs that are initially off. You first turn on all the bulbs.
Then, you turn off every second bulb. On the third round, you toggle every third
bulb (turning on if it's off or turning off if it's on). For the ith round,
you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].

So you should return 1, because there is only one bulb is on.


* */
package laicode;

public class BulbSwitcher {
    public int bulbSwitch(int n) {
        // 1, q1, q2, ..., qn, x, n / qn, ..., n / q2, n / q1, n
        //                     ↑
        //                square root
        // odd if it is perfect square number -> on
        // even if it is not -> off
        // n = 60
        // 1  -> 1
        // 4  -> 1, |2|, 4
        // 9  -> 1, |3|, 9
        // 16 -> 1, 2, |4|, 8, 16
        // ....
        return (int)Math.sqrt(n);
    }
}

/*
* The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
    20180521
    89
    medium
    ?
    O(2 ^ n)
    O(1)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> graycode(int n) {
        List<Integer> sol = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            sol.add(i ^ i >> 1);
        }
        return sol;
    }
}
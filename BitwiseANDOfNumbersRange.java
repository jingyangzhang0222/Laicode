/*
* Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0

    20180527
    201
    medium
    bit
    O(1)
    O(1)
* */
package leetcode;

public class BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        int res = 0;
        int i = 31;
        while (i >= 0 && ((m >> i) & 1) == ((n >> i) & 1)) {
            res |= (((m >> i) & 1) << (i--));
        }
        return res;
    }
}
/*
7
easy
1. minus
2. out of int boundary
3.trailing zeros
*/
package leetcode;

public class ReverseInteger {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = 10 * res + (long)x % 10;
            x /= 10;
        }
        if (res > (long)Integer.MAX_VALUE || res < (long)Integer.MIN_VALUE) {
            return 0;
        }
        return (int)res;
    }
}

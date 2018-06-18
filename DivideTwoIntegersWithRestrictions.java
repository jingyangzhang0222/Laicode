package leetcode;

public class DivideTwoIntegersWithRestrictions {
    public static void main(String[] args) {
        DivideTwoIntegersWithRestrictions test = new DivideTwoIntegersWithRestrictions();
        System.out.print(test.divide(100, 9));
    }
    public int divide(int a, int b) {
        //corner case
        if (b == 0) {
            return Integer.MAX_VALUE;
        }
        if (a == 0) {
            return 0;
        }
        boolean positive = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = Math.abs(a);
        b = Math.abs(b);
        int maxShift = 0;
        int res = 0;
        while (a >= (b << (maxShift + 1))) {
            maxShift++;
        }
        int shift = maxShift;
        while (shift >= 0) {
            if (a >= b << shift) {
                a -= b << shift;
                res += 1 << shift;
            }
            shift--;
        }
        return positive ? res : -res;
    }
}

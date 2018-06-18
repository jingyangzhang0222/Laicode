/*
* Given two integers a and b, return the result of a / b in String with compact format. The repeated decimal part should be identified and enclosed by "()".

Examples

0 / 2 = "0"

4 / 2 = "2"

1 / 2 = "0.5"

-14 / 12 = "-1.1(6)"

1 / 11 = "0.(09)"

1 / 0 = "NaN"

-1 / 0 = "NaN"
* */
package laicode;

import java.util.HashSet;
import java.util.Set;

public class CompactDivide {
    public String divide(int a, int b) {
        //corner case
        if (a * b == 0) {
            return b == 0 ? "NaN" : "0";
        }
        StringBuilder sb = new StringBuilder();
        if (a * b < 0) {
            sb.append('-');
        }

        a = Math.abs(a);
        b = Math.abs(b);

        int quo = a / b;
        a -= b * quo;
        sb.append(quo);
        if (a == 0) return sb.toString();

        sb.append('.');
        while (b % 10 == 0) {
            b /= 10;
            sb.append('0');
        }
        sb.append('(');
        int parentheseAt = sb.length() - 1;

        Set<Integer> set = new HashSet<>();

        a *= 10;
        while (!set.contains(a) && a != 0) {
            set.add(a);
            quo = a / b;
            sb.append(quo);
            a -= b * quo;
            a *= 10;
        }

        if (a != 0) {
            sb.append(')');
            return sb.toString();
        }
        sb.deleteCharAt(parentheseAt);
        return sb.toString();
    }
}

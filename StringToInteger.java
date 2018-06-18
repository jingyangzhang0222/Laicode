package leetcode;

public class StringToInteger {
    /*
    1. The function first discards as many
    whitespace characters as necessary until
    the first non-whitespace character is found.

    2. starting from this character, takes an optional
    initial plus or minus sign followed by as many numerical
    digits as possible, and interprets them as a numerical value.

    3. The string can contain additional characters after
    those that form the integral number, which are ignored and
    have no effect on the behavior of this function.

    4. If the first sequence of non-whitespace characters in
    str is not a valid integral number, or if no such sequence
    exists because either str is empty or it contains only whitespace
    characters, no conversion is performed.

    5. If no valid conversion could be performed, a zero value is
    returned. If the correct value is out of the range of representable
    values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
    */
    public int atoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int i = 0;

        long res = 0;

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        boolean positive = true;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            positive = s.charAt(i) == '+';
            i++;
        }

        while (i < n && Character.isDigit(s.charAt(i))) {
            res = 10 * res + s.charAt(i) - '0';
            if (res > (long)Integer.MAX_VALUE + 1) {
                break;
            }
            i++;
        }

        res = positive ? res : -res;

        if (res >= (long)2147483647) {
            return 2147483647;
        }
        if (res <= (long)(-2147483648)) {
            return -2147483648;
        }
        return (int)res;
    }
}

/*
* Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

Example:

Input: 13
Output: 6
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

    20180603
    233
    hard
    math
    O(log_10 n)
    O(1)
* */
package leetcode;

public class NumberOfDigitOne {
    public int countDigitOne(int n) {
    /*
    one:
    1 - 10: 1
    1 - 20: 2          161: 17
    ...                 |
    1 - 160: 16        170: 17

    ten:                 1605: 160
    1 - 100: 10			 1610: 161
    1 - 200: 20          1611: 162
    ...                  ...
    1 - 1600: 160        1619: 170
                           |
                         1700: 170

    hundred:
    1 - 1000: 100
    1 - 2000: 200          16100: 1601
    ...                    16101: 1602
                           16102: 1603
    1 - 16000: 1600          ...
                           16199: 1700
                             |
                           17000: 1700

    1090291:

    one: 109029 1
         109029*1 + 1

    ten: 10902 91
         10902*10 + max(0, min(19, 91) - 10 + 1) = 109020 + 10

    hundred: 1090 291
             1090*100 + max(0, min(199, 291) - 100 + 1) = 109000 + 100

    thousand: 109 0291
              109*1000 + max(0, min(1999, 0291) - 1000 + 1)

    man: 10 90291
         10*10000 + max(0, min(19999, 90291) - 10000 + 1)

    juman: 1 090291
           1*100000 + max(0, min(199999, 090291) - 100000 + 1)

    hyakuman:_ 1090291
             0*1000000 + max(0, min(1999999, 1090291) - 1000000 + 1)
    */
        int sum = 0;
        long digit = 1;
        while (digit <= (long)n) {//////////////
            int i  = (int)digit;
            int j = 10 * i;
            int basic = n / (j) * i;
            int res = n % (j);
            int bonus = Math.max(0, Math.min(2 * i - 1, res) - i+ 1);

            sum += basic + bonus;
            digit *= 10;
        }
        return sum;
    }
}

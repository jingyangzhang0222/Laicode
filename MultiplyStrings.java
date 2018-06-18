/*
* Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
    20180527
    43
    medium
    math
    O(m * n)
    O(m + n)
* */
package leetcode;

import java.util.Arrays;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        //corner case
        if (num1 == "0" || num2 == "0") {
            return "0";
        }
        // m + n - 1 ~ 0
        char[] res = new char[num1.length() + num2.length()];
        Arrays.fill(res, '0');
        for (int i = 0; i < num1.length(); i++) {
            int index = num1.length() - 1 - i;
            mul(res, num2, num1.charAt(index), i);
        }
        int offset = -1;
        for (int i = 0; i < res.length; i++) {
            if (res[i] != '0') {
                offset = i;
                break;
            }
        }
        return new String(res, offset, res.length - offset);
    }

    private void mul(char[] res, String number, char digit, int offset) {
        if (digit == '0') {
            return;
        }
        int[] tmp = new int[number.length() + 1];

        int mul1 = digit - '0';
        int carry = 0;
        for (int i = 0; i <= number.length(); i++) {
            int index =  number.length() - 1 - i;
            int mul2 = i == number.length() ? 0 : number.charAt(index) - '0';
            int mulResult = mul2 * mul1;
            int overallResult = mulResult + carry;
            int thisDigit = overallResult % 10;
            carry = overallResult / 10;
            tmp[index + 1] = thisDigit;
        }

        int addCarry = 0;
        for (int i = 0; i < tmp.length; i++) {
            int tmpIndex = tmp.length - 1 - i;
            int resIndex = res.length - 1 - offset - i;
            int addResult = addCarry + tmp[tmpIndex] + res[resIndex] - '0';
            addCarry = addResult / 10;
            int thisDigit = addResult % 10;
            res[resIndex] = (char)('0' + thisDigit);
        }
    }
}

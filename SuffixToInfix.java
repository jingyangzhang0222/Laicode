/*
* Given a suffix expression, convert it to infix one with the minimum number parentheses.

The expression is represented by a string and each token is separated by ' '.

Examples:

"1 2 3 - +" is converted to "1 + 2 - 3", ("1 + ( 2 - 3 )" is not the correct answer since it has an extra parenthese).

"1 2 3 +" is converted to "1 + 2 3"

"1 2 3 + " is converted to "1 ( 2 + 3 )"


* */
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SuffixToInfix {
    public String convert(String suffix) {
        String[] elements = suffix.split(" ");
        Deque<String> operand = new ArrayDeque<>();

        String prevOperator = "=";

        for (String s : elements) {
            if (Character.isDigit(s.charAt(0))) {
                operand.offerLast(s);
            } else {
                String num2 = operand.pollLast();
                String num1 = operand.pollLast();
                boolean needParentheses = (s.equals("*") || s.equals("/")) &&
                        (prevOperator.equals("+") || prevOperator.equals("-"));
                if (needParentheses) {
                    num2 = "(" + num2 + ")";
                }
                operand.offerLast(num1 + s + num2);
                prevOperator = s;
            }
        }
        return operand.pollLast();
    }
}

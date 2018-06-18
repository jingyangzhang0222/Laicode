/*
* Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, , / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:

"3+22" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5

    O(n)
    O(n)
* */
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorII {
    public static void main(String[] args) {
        BasicCalculatorII test = new BasicCalculatorII();
        System.out.println(test.calculate(" 3 *5 * 2 /4/7*9-3*5/4+5 / 2 /2 * 3/1-6*2+2/3+2*2*8*3"));
    }
    public int calculate(String s) {
        //corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Character> operator = new ArrayDeque<>();
        Deque<Integer> operand = new ArrayDeque<>();

        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (c == ' ') {
                index++;
                continue;
            } else if (c == '*' || c == '/' || c == '+' || c == '-') {
                index++;
                operator.offer(c);
            } else {
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = 10 * num + s.charAt(index) - '0';
                    index++;
                }
                if (operator.isEmpty() || operator.peekLast() == '+' || operator.peekLast() == '-') {
                    operand.offerLast(num);
                } else if (operator.pollLast() == '*') {
                    operand.offerLast(operand.pollLast() * num);
                } else {
                    operand.offerLast(operand.pollLast() / num);
                }
            }
        }
        while (!operator.isEmpty()) {
            char op = operator.pollFirst();//////////////////////////
            int num1 = operand.pollFirst();//////////////////////////
            int num2 = operand.pollFirst();/////////////////////////
            operand.offerFirst(op == '+' ? num1 + num2 : num1 - num2);//////////////////
        }
        return operand.pop();
    }
}

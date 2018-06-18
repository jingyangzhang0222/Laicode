package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateSuffixExpression {
    public int evaluate(String[] suffix) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String input : suffix) {
            if (input == "-") {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                stack.offerLast(num2 - num1);
            } else if (input == "+") {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                stack.offerLast(num2 + num1);
            } else if (input == "*") {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                stack.offerLast(num2 * num1);
            } else if (input == "/") {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                stack.offerLast(num2 / num1);
            } else {
                stack.offer(toInt(input));
            }
        }
        return stack.pollLast();
    }

    private int toInt(String input) {
        int res = 0;
        for (int i = 0; i < input.length(); i++) {
            res = 10 * res + input.charAt(i) - '0';
        }
        return res;
    }
}

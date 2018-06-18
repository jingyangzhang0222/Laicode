/*
20
easy
 */

package leetcode;

import java.util.*;

public class ValidParentheses {
    public boolean isValid(String s) {
        //corner case
        if (s == null || s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.offerLast(s.charAt(i));
            } else if (!isValid(stack, s.charAt(i))) {
                return false;
            }
        }
        return stack.isEmpty();
    }
    private boolean isValid(Deque<Character> stack, char x) {
        if (stack.isEmpty()) return false;
        if (x == ')') {
            return stack.pollLast() == '(';
        } else if (x == ']') {
            return stack.pollLast() == '[';
        } else {
            return stack.pollLast() == '{';
        }
    }
}

/*
*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:

"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.

    https://www.youtube.com/watch?v=9c0WHgIsk5g
    O(n)
    O(n)

    typo: 11 / 60
* */
package laicode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    public static void main(String[] args) {
        BasicCalculator test = new BasicCalculator();
        System.out.println(test.calculate("1+((4+5)+(3-3)+(6+8)) - (1 + (2 - 3)+ (4 -(4-(5-(6 + 7)) + 8) -1))"));
    }

    public int calculate(String e) {
        Deque<Pair> operator = new ArrayDeque<>();/*    typo11    */
        Deque<Integer> operand = new ArrayDeque<>();

        int level = 0;
        int index = 0;

        while (index < e.length()) {
            if (e.charAt(index) == '(') {/*    typo1     */
                level++;
                index++;
            } else if (e.charAt(index) == ')') {
                level--;
                index++;
            } else if (Character.isDigit(e.charAt(index))) {/*    typo2     */
                int num = 0;
                while (index < e.length() && Character.isDigit(e.charAt(index))) {/*    typo3, 8    */
                    num = 10 * num + e.charAt(index) - '0';/*    typo4, 10     */
                    index++;
                }
                operand.offerLast(num);/*    typo5     */
            } else if (e.charAt(index) == '+' || e.charAt(index) == '-') {
                update(operator, operand, level);
                operator.offerLast(new Pair(e.charAt(index), level));
                index++;
            } else {
                index++;
            }
        }

        update(operator, operand, level);

        return operand.pollLast();
    }

    private void update(Deque<Pair> operator, Deque<Integer> operand, int level) {
        while (!operator.isEmpty() && operator.peekLast().level >= level) {
            Pair p = operator.pollLast();
            char op = p.op;
            int num2 = operand.pollLast();
            int num1 = operand.pollLast();
            if (op == '+') {
                operand.offerLast(num1 + num2);
            } else {
                operand.offerLast(num1 - num2);
            }
        }
    }

    static class Pair {/*    typo7     */
        char op;
        int level;

        public Pair(char op, int level) {
            this.op = op;
            this.level = level;
        }
    }
}
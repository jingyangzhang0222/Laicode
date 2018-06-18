package leetcode;

import java.util.*;

public class AllValidPermutaionsOfParenthesesII {
    private static final char[] leftTable = {'(', '[', '{'};
    private static final char[] rightTable = {')', ']', '}'};
    private final int[] leftAdded = new int[3];
    private final int[] rightAdded = new int[3];

    public List<String> validParentheses(int l, int m, int n) {
        int[] limit = {l, m, n};
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        List<String> sol = new ArrayList<>();
        DFS(sol, sb, stack, limit);
        return sol;
    }

    private void DFS(List<String> sol, StringBuilder sb, Deque<Character> stack, int[] limit) {
        //base case
        if (sb.length() == 2 * (limit[0] + limit[1] + limit[2])) {
            sol.add(sb.toString());
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (leftAdded[i] < limit[i]) {
                addLeft(sb, stack, i);
                DFS(sol, sb, stack, limit);
                subLeft(sb, stack, i);
            }
            if (rightAdded[i] < leftAdded[i] && stack.peekLast() == leftTable[i]) {
                addRight(sb, stack, i);
                DFS(sol, sb, stack, limit);
                subRight(sb, stack, i);
            }
        }
    }

    private void addLeft(StringBuilder sb, Deque<Character> stack, int index) {
        sb.append(leftTable[index]);
        stack.offerLast(leftTable[index]);
        leftAdded[index]++;
    }

    private void subLeft(StringBuilder sb, Deque<Character> stack, int index) {
        leftAdded[index]--;
        stack.pollLast();
        sb.deleteCharAt(sb.length() - 1);
    }

    private void addRight(StringBuilder sb, Deque<Character> stack, int index) {
        sb.append(rightTable[index]);
        stack.pollLast();
        rightAdded[index]++;
    }

    private void subRight(StringBuilder sb, Deque<Character> stack, int index) {
        rightAdded[index]--;
        stack.offerLast(leftTable[index]);
        sb.deleteCharAt(sb.length() - 1);
    }
}
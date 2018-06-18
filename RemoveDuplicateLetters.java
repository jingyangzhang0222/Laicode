/*
* Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"

    20180607
    316
    hard
    String, greedy
    O(n)
    O(n)
首先对字符串出现的个数进行统计。
然后对字符串扫描，每遇到一个字符串，判断其是否在栈中，如果在, 那么就说明去动前面的元素毫无意义,
就算动了也只能把当前的元素安排到之前那个相同元素的位置而已, 还有可能让后面没有机会再出现的字母白白牺牲则跳过。（计数 – 1）

如果不在栈中，和栈顶的元素判断，要是当前栈顶的元素比较大而且cnt不为0（也就是说之后还有这个元素），就把栈顶弹出。然后把当前的元素入栈。
有一点类似Longest common sub-sequence, 但不完全一样

🍣 🍣 🍣 🍣 🍣
* */
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        RemoveDuplicateLetters test = new RemoveDuplicateLetters();
        System.out.println(test.removeDuplicateLetters("bacdacbcdac"));

    }
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }

        Set<Character> inStack = new HashSet<>();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (inStack.contains(ch)) {////////////////////
                map[ch - 'a']--;
                continue;
            }

            while (!stack.isEmpty() && (int)stack.peekLast() >= (int)ch && map[stack.peekLast() - 'a'] > 0) {
                char removed = stack.pollLast();
                inStack.remove(removed);
            }

            stack.offerLast(ch);
            inStack.add(ch);
            map[ch - 'a']--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }
}
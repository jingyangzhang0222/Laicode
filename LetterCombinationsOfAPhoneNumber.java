/*
* 20180517
* 17
* medium
* DFS
* O(4 ^ n)
* O(n)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    static final char[][] dic = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'},
            {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    public List<String> letterCombinations(String digits) {
        List<String> sol = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return sol;
        }
        DFS(digits, 0, new StringBuilder(), sol);
        return sol;
    }

    private void DFS(String digits, int index, StringBuilder sb, List<String> sol) {
        //base case
        if (index == digits.length()) {
            sol.add(sb.toString());
            return;
        }
        for (char letter : dic[digits.charAt(index) - '0']) {
            sb.append(letter);
            DFS(digits, index + 1, sb, sol);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

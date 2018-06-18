/*
* Insert the least number of characters to a string in order to make the new string a palindrome. Return the least number of characters should be inserted.

Assumptions:

The given string is not null.

    O(n ^ 2)
    O(n)

    需要插入的字符一定是存在于原字符串中的
             ↓
    如果reverse了之后和原串能形成common subsequence,
    那么覆盖的那些字符就不再需要插入, 因为他们在原串中就被take care了
             ↓
    那么需要take care的就是那些没有配对上的
             ↓
    原串长度减去原串与反串的LCS
* */
package leetcode;

public class LeastInsertionsToFormAPalindrome {
    public static void main(String[] args) {
        LeastInsertionsToFormAPalindrome test = new LeastInsertionsToFormAPalindrome();
        System.out.println(test.leastInsertion("ab"));
        //System.out.println(test.longestCommonSubsequence("evoli", "oogle"));
    }
    public int leastInsertion(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return s.length() - longestCommonSubsequence(s, sb.toString());
    }

    private int longestCommonSubsequence(String s, String t) {
        if (s.length() * t.length() == 0) return 0;
        int[][] M = new int[s.length() + 1][t.length() + 1];
        for (int row = 1; row < M.length; row++) {
            for (int col = 1; col < M[0].length; col++) {
                M[row][col] = s.charAt(row - 1) == t.charAt(col - 1) ? M[row - 1][col - 1] + 1 : Math.max(M[row - 1][col], M[row][col - 1]);
            }
        }
        return M[M.length - 1][M[0].length - 1];
    }
}

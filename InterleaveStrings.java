/*
* Given three strings A, B and C. Determine if C can be created by merging A and B in a way that maintains the relative order of the characters in A and B.

Assumptions

none of A, B, C is null
Examples

C = "abcde", A = "acd", B = "be", return true
C = "abcde", A = "adc", B = "be", return false
    20180525
    97
    hard
    DP
    O(n ^ 2)
    O(n ^ 2) -> O(n) (ANS)
* */
package leetcode;

public class InterleaveStrings {
    public boolean canMerge(String a, String b, String c) {
        if (a.length() + b.length() != c.length()) {
            return false;
        }
        boolean[][] canMerge = new boolean[a.length() + 1][b.length() + 1];
        for (int i = 0; i < canMerge.length; i++) {
            for (int j = 0; j < canMerge[0].length; j++) {
                if (i + j == 0) {
                    canMerge[i][j] = true;
                }
                if (i > 0 && a.charAt(i - 1) == c.charAt(i + j - 1)) {
                    canMerge[i][j] |= canMerge[i - 1][j];
                }
                if (j > 0 && b.charAt(j - 1) == c.charAt(i + j - 1)) {
                    canMerge[i][j] |= canMerge[i][j - 1];
                }
            }
        }
        return canMerge[a.length()][b.length()];
    }
    public boolean isInterleaveANS(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[] = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }
}

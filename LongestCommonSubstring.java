/*
* Find the longest common substring of two given strings.

Assumptions

The two given strings are not null
Examples

S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”
O(n ^ 2)
O(n ^ 2)
* */
package leetcode;

public class LongestCommonSubstring {
    public String longestCommon(String s, String t) {
        if (s.length() * t.length() == 0) {
            return "";
        }

        int[][] c = new int[s.length() + 1][t.length() + 1];
        int globalMax = 0;
        int start = 0;
        for (int i = 1; i < c.length; i++) {
            for (int j = 1; j < c[0].length; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    if (c[i][j] > globalMax) {
                        globalMax = c[i][j];
                        start = i - globalMax;
                    }
                }
            }
        }
        return s.substring(start, start + globalMax);
    }
}

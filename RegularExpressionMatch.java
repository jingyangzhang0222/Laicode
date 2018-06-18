package leetcode;

public class RegularExpressionMatch {
    public static void main(String[] args) {
        RegularExpressionMatch test = new RegularExpressionMatch();
        System.out.println(test.isMatch("f", ".*"));
    }
    public boolean isMatch(String input, String pattern) {
        int m = input.length();
        int n = pattern.length();
        boolean[][] M = new boolean[m + 1][n + 1];
        M[m][n] = true;

        for (int i = m; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                boolean singleMatch = (i < m && (input.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));
                if (j + 1 < n && pattern.charAt(j + 1) == '*') {
                    M[i][j] = singleMatch && M[i + 1][j] || M[i][j + 2];
                } else {
                    M[i][j] = singleMatch && M[i + 1][j + 1];
                }
            }
        }
        return M[0][0];
    }
}

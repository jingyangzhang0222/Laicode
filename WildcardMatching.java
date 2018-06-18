package laicode;

public class WildcardMatching {
    public boolean match(String input, String pattern) {
        if (input.length() == 0) {
            return pattern.length() == 0 || (pattern.length() == 1 && pattern.charAt(0) == '*');
        }
        if (pattern.length() == 0) {
            return input.length() == 0;
        }
        int m = input.length();
        int n = pattern.length();
        boolean[][] M = new boolean[m + 1][n + 1];
        M[m][n] = true;
        M[m][n - 1] = pattern.charAt(n - 1) == '*';

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (pattern.charAt(j) == '*') {
                    M[i][j] = M[i][j + 1] || M[i + 1][j];
                } else {
                    boolean singleMatch = input.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?';
                    M[i][j] = singleMatch && M[i + 1][j + 1];
                }
            }
        }
        return M[0][0];
    }
}

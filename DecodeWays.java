package leetcode;

public class DecodeWays {
    public int numDecodeWay(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] M = new int[s.length() + 1];
        for (int i = 0; i < M.length; i++) {
            if (i < 2) {
                M[i] = 1;
            } else {
                int j = i - 1;
                if (s.charAt(j) == '0') {
                    if (s.charAt(j - 1) != '1' && s.charAt(j - 1) != '2') {
                        return 0;
                    }
                    M[i] = M[i - 2];
                } else if ((s.charAt(j - 1) == '1' && s.charAt(j) - '0' >= 1 && s.charAt(j) - '0' <= 9) ||
                        (s.charAt(j - 1) == '2' && s.charAt(j) - '0' >= 1 && s.charAt(j) - '0' <= 6)
                        ) {
                    M[i] = M[i - 1] + M[i - 2];
                } else {
                    M[i] = M[i - 1];
                }
            }
        }
        return M[s.length()];
    }
}

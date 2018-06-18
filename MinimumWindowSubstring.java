/*
* Given a string S and a string T, find the minimum window in S which will contain all the characters in T

Input: S = “ADOBECODEBANC”

          T = “ABC”

Output: “BANC”

    O(n)
    O(1)
* */
package leetcode;

public class MinimumWindowSubstring {
    public String minWindow(String S, String T) {
        if (T == null || T.length() == 0) return "";
        int[] mapT = new int[26];
        int countT = 0;

        for (int i = 0; i < T.length(); i++) {
            if (mapT[T.charAt(i) - 'A'] == 0) countT++;
            mapT[T.charAt(i) - 'A']++;
        }

        int[] mapS = new int[26];
        int s = 0, typeCount = 0, start = 0;
        int globalMin = Integer.MAX_VALUE;

        for (int f = 0; f < S.length(); f++) {
            mapS[S.charAt(f) - 'A']++;
            if (mapT[S.charAt(f) - 'A'] != 0 && mapS[S.charAt(f) - 'A'] == mapT[S.charAt(f) - 'A']) {
                typeCount++;
            }

            while (typeCount == countT) {
                if (f - s < globalMin) {
                    globalMin = f - s;
                    start = s;
                }
                mapS[S.charAt(s) - 'A']--;
                if (mapT[S.charAt(s) - 'A'] != 0 && mapS[S.charAt(s) - 'A'] < mapT[S.charAt(s) - 'A']) {
                    typeCount--;
                }
                s++;
            }

        }
        return globalMin == Integer.MAX_VALUE ? "" : S.substring(start, start + globalMin + 1);
    }
}

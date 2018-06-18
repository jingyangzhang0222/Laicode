/*
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.

    20180601
    159
    hard
    sliding window, string
    O(n)
    O(1)
* */
package leetcode;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String input) {
        int[] map = new int[256];
        int s = 0;
        int typeCount = 0;
        int globalMax = 0;

        for (int f = 0; f < input.length(); f++) {
            map[input.charAt(f)]++;
            if (map[input.charAt(f)] == 1) {
                typeCount++;
            }

            while (typeCount > 2) {
                map[input.charAt(s)]--;
                if (map[input.charAt(s)] == 0) {
                    typeCount--;
                }
                s++;
            }

            globalMax = Math.max(globalMax, f - s + 1);
        }
        return globalMax;
    }
}

/*
* Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.

    20180601
    340
    hard
    sliding window, string
    O(n)
    O(1)
* */
package leetcode;

public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String input, int k) {
        int[] map = new int[256];
        int globalMax = 0, s = 0, typeCount = 0;
        for (int f = 0; f < input.length(); f++) {
            if (map[input.charAt(f)]++ == 0)  typeCount++;
            while (typeCount > k) {
                if (--map[input.charAt(s++)] == 0) typeCount--;
            }
            globalMax = Math.max(globalMax, f - s + 1);
        }
        return globalMax;
    }
}

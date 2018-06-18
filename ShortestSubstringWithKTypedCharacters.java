/*
* Given a string, return the shortest contiguous substring that contains exactly k type of characters.

Return an empty string if there does not exist such substring.

Assumptions:

The given string is not null.
k >= 0.
Examples:

input = "aabcc", k = 3, output = "abc".
input = "aabbbcccc", k = 3, output = "abbbc".
input = "aabcc", k = 4, output = "".

    O(n)
    O(1)
* */
package leetcode;

public class ShortestSubstringWithKTypedCharacters {
    public String shortest(String input, int k) {
        int[] map = new int[26];
        int typeCount = 0;
        int s = 0;
        int start = 0;
        int globalMin = Integer.MAX_VALUE;

        for (int f = 0; f < input.length(); f++) {
            map[input.charAt(f) - 'a']++;
            if (map[input.charAt(f) - 'a'] == 1) typeCount++;

            while (typeCount == k) {
                if (f - s + 1 < globalMin) {
                    start = s;
                    globalMin = f - s + 1;
                }
                map[input.charAt(s) - 'a']--;
                if (map[input.charAt(s++) - 'a'] == 0) typeCount--;
            }
        }

        return globalMin == Integer.MAX_VALUE ? "" : input.substring(start, start + globalMin);
    }
}

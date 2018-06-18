package leetcode;

public class LongestSubstringWithKTypedCharacters {
    public String longest(String input, int k) {
        if (k == 0) return "";
        int s = 0, start = 0, globalMax = Integer.MIN_VALUE, typeCount = 0;
        int[] map = new int[256];
        for (int f = 0; f < input.length(); f++) {
            if (map[input.charAt(f)]++ == 0) typeCount++;
            while (typeCount > k) {
                if (--map[input.charAt(s++)] == 0) typeCount--;
            }
            if (typeCount == k && f - s + 1 > globalMax) {
                globalMax = f - s + 1;
                start = s;
            }
        }
        return globalMax == Integer.MIN_VALUE ? null : input.substring(start, start + globalMax);
    }
}

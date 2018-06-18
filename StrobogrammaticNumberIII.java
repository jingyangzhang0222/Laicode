/*
* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.

    20180605
    248
    hard
    math
    O(5 ^ n)
    O(n)
* */
package leetcode;

public class StrobogrammaticNumberIII {
    private static final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public static void main(String[] args) {
        StrobogrammaticNumberIII test = new StrobogrammaticNumberIII();
        System.out.println(test.strobogrammaticInRange("0", "1"));
    }

    public int strobogrammaticInRange(String low, String high) {
        int[] count = {0};
        for (int i = low.length(); i <= high.length(); i++) {
            dfs(low, high, new char[i], count, 0, i - 1);
        }
        return count[0];
    }

    private void dfs(String low, String high, char[] ch, int[] count, int left, int right) {
        //base case
        if (left > right) {
            String s = new String(ch);
            if (((s.length() == low.length() && s.compareTo(low) < 0) ||
                    (s.length() == high.length() && s.compareTo(high) > 0))) {
                return;
            }
            count[0]++;
            return;
        }

        for (char[] pair : pairs) {
            ch[left] = pair[0];
            ch[right] = pair[1];
            if (ch.length != 1 && ch[0] == '0') {
                continue;
            }
            if (left == right && pair[0] != pair[1]) {
                continue;
            }
            dfs(low, high, ch, count, left + 1, right - 1);
        }
    }
}

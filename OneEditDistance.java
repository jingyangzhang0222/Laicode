/*
* Given two strings s and t, determine if they are both one edit distance apart.

Note:

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.

    20180603
    161
    medium
    string
    O(n)
    O(1)
* */
package leetcode;

public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) >= 2) {
            return false;
        }

        if (s.length() == t.length()) {
            return oneEditDistanceReplace(s, t);
        } else {
            return oneEditDistanceNoReplace(s, t);
        }
    }

    private boolean oneEditDistanceReplace(String s, String t) {
        int diff = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diff++;
            }
            if (diff > 1) return false;
        }
        return diff == 1;
    }

    private boolean oneEditDistanceNoReplace(String s, String t) {
        String sh = s.length() < t.length() ? s : t;
        String lo = s.length() > t.length() ? s : t;
        int shIndex = 0;
        int loIndex = 0;
        while (shIndex < sh.length()) {
            loIndex = shIndex;
            if (sh.charAt(shIndex) != lo.charAt(loIndex)) {
                loIndex++;
                break;
            }
            shIndex++;
        }
        if (shIndex == sh.length() && loIndex == lo.length() - 1) return true;
        while (shIndex < sh.length() && loIndex < lo.length()) {
            if (sh.charAt(shIndex++) != lo.charAt(loIndex++)) {
                return false;
            }
        }
        return true;
    }
}
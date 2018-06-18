/*
* Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:

pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.


* */
package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPatternII {
    public static void main(String[] args) {
        WordPatternII test = new WordPatternII();
        String pattern = "ab";
        String s = "aa";
        System.out.println(test.wordPatternMatch(pattern, s));
    }

    public boolean wordPatternMatch(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Set<String> asValue = new HashSet<>();
        return dfsHelper(pattern, s, 0, 0, map, asValue);
    }

    private boolean dfsHelper(String pattern, String s, int index, int start, Map<Character, String> map, Set<String> asValue) {
        //base case
        if (start == s.length() || index == pattern.length()) {
            return index == pattern.length() && start == s.length();
        }

        char p = pattern.charAt(index);
        String mappedString = map.get(p);

        if (mappedString == null) {
            for (int i = 1; start + i <= s.length(); i++) {
                String newMappedString = s.substring(start, start + i);
                if (asValue.add(newMappedString)) {///////////////////////////////
                    map.put(p, newMappedString);
                    boolean found = dfsHelper(pattern, s, index + 1, start + i, map, asValue);
                    if (found) return true;
                    map.remove(p);
                    asValue.remove(newMappedString);
                }
            }
            return false;
        } else {
            int indexForMappedString = 0;
            while (indexForMappedString < mappedString.length()) {
                char toBeChecked = mappedString.charAt(indexForMappedString);
                if (start + indexForMappedString >= s.length() || s.charAt(start + indexForMappedString) != toBeChecked) {
                    return false;
                }
                indexForMappedString++;
            }
            return dfsHelper(pattern, s, index + 1, start + indexForMappedString, map, asValue);
        }
    }
}

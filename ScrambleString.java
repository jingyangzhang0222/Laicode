/*
* Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
    20180523
    87
    hard
    recursion
    O(n!)
    O(n)
* */
package leetcode;

public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        //corner case
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        return helper(s1.toCharArray(), 0, s2.toCharArray(), 0, s1.length());
    }
    private boolean helper(char[] c1, int start1, char[] c2, int start2, int size) {
        //base case
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            return c1[start1] == c2[start2];
        }

        int[] frequencies = new int[26];
        for (int i = 0; i < size; i++) {
            frequencies[c1[start1 + i] - 'a']++;
            frequencies[c2[start2 + i] - 'a']--;
        }
        for (int frequency : frequencies) {
            if (frequency != 0) {
                return false;
            }
        }

        for (int len1 = 1; len1 <= size - 1; len1++) {
            int len2 = size - len1;
            boolean notSwap = helper(c1, start1, c2, start2, len1) && helper(c1, start1 + len1, c2, start2 + len1, len2);
            if (notSwap) return true;
            boolean swap = helper(c1, start1, c2, start2 + len2, len1) && helper(c1, start1 + len1, c2, start2, len2);
            if (swap) return true;
        }
        return false;
    }
}

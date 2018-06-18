/*
* Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

todo! KMP!
* */
package leetcode;

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        String newString = s + "#" +reversed;
        int[] lps = new int[2 * s.length() + 1];
        for (int i = 1; i < lps.length; i++) {
            int prevIndex = lps[i - 1];
            while (prevIndex > 0 && newString.charAt(prevIndex) != newString.charAt(i)) {
                prevIndex = lps[prevIndex - 1];
            }
            lps[i] = prevIndex + (newString.charAt(prevIndex) == newString.charAt(i) ? 1 : 0);
        }
        return new StringBuilder(s.substring(lps[lps.length - 1])).reverse().toString() + s;
    }
}
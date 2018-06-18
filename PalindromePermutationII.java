/*
* Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].
* */
package leetcode;

import java.util.*;

public class PalindromePermutationII {
    public String[] generatePalindromes(String s) {
        List<String> sol =new ArrayList<>();
        DFS(s.toCharArray(), sol, 0);
        String[] res =  sol.toArray(new String[0]);
        Arrays.sort(res);
        return res;
    }
    private void DFS(char[] input, List<String> sol, int index) {
        //bnase case
        if (index == input.length && isPalindrome(input)) {
            sol.add(new String(input));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < input.length; i++) {
            if (set.add(input[i])) {
                swap(input, i, index);
                DFS(input, sol, index + 1);
                swap(input, index, i);
            }
        }
    }
    private boolean isPalindrome(char[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left++] != array[right--]) {
                return false;
            }
        }
        return true;
    }
    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

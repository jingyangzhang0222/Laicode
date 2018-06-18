/*
* Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.

    O(n)
    O(1)
* */
package leetcode;

public class ShortestWordDistanceIII {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (word1.equals(word2)) {
            return searchSame(words, word1);
        } else {
            return searchDifferent(words, word1, word2);
        }
    }

    private int searchDifferent(String[] words, String word1, String word2) {
        int globalMin = words.length;
        int pre1 = -1, pre2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (pre2 != -1) {
                    globalMin = Math.min(globalMin, i - pre2);
                }
                pre1 = i;
            }
            if (words[i].equals(word2)) {
                if (pre1 != -1) {
                    globalMin = Math.min(globalMin, i - pre1);
                }
                pre2 = i;
            }
        }
        return globalMin;
    }

    private int searchSame(String[] words, String word) {
        int prevIdx = -1;
        int globalMin = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                if (prevIdx != -1) {
                    globalMin = Math.min(globalMin, i - prevIdx);
                }
                prevIdx = i;
            }
        }
        return globalMin;
    }
}

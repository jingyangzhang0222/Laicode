/*
* Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
* */
package laicode;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    private static final int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'},
                          {'e', 't', 'a', 'e'},
                          {'i', 'h', 'k', 'r'},
                          {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        WordSearchII test = new WordSearchII();
        String[] result = test.findWords(board, words);
        for (String word : result) {
            System.out.println(word);
        }
    }

    public String[] findWords(char[][] board, String[] words) {
        Trie trieRoot = new Trie();
        for (String word : words) {
            insert(trieRoot, word, 0, new StringBuilder());
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        List<String> sol = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsHelper(board, sol, trieRoot, visited, i, j);
            }
        }
        return sol.toArray(new String[0]);
    }

    private void dfsHelper(char[][] board, List<String> sol, Trie root, boolean[][] visited, int x, int y) {
        //base case
        char ch = board[x][y];
        if (root.children[ch - 'a'] == null) {
            return;
        }
        Trie child = root.children[ch - 'a'];
        if (child.childrenCount == 0 && !child.inSol) {
            sol.add(child.prefix);
            child.inSol = true;
            return;
        }

        visited[x][y] = true;
        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];
            if (dx < 0 || dx >= board.length || dy < 0 || dy >= board[0].length || visited[dx][dy]) {
                continue;
            }
            dfsHelper(board, sol, root.children[ch - 'a'], visited, dx, dy);
        }
        visited[x][y] = false;
    }

    private void insert(Trie root, String word, int index, StringBuilder sb) {
        //base case
        if (index == word.length()) {
            root.prefix = sb.toString();
            return;
        }

        char ch = word.charAt(index);
        root.prefix = sb.toString();
        sb.append(ch);

        if (root.children[ch - 'a'] == null) {
            root.children[ch - 'a'] = new Trie();
            root.childrenCount++;
        }

        insert(root.children[ch - 'a'], word, index + 1, sb);
    }

    static class Trie {
        Trie[] children;
        String prefix;
        int childrenCount;
        boolean inSol;

        public Trie() {
            children = new Trie[26];
            childrenCount = 0;
        }
    }
}

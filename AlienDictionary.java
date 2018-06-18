package laicode;

import java.util.*;

public class AlienDictionary {
    private boolean valid = true;
    public String alienOrder(String[] input) {
        char[][] strs = new char[input.length][];
        for (int i = 0; i < input.length; i++) {
            strs[i] = input[i].toCharArray();
        }
        if (strs == null || strs.length == 0 || strs[0].length == 0) {
            return "";
        }

        List<Node> graph = new ArrayList<>();
        Map<Character, Node> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length; j++) {
                if (!map.containsKey(strs[i][j])) {
                    Node node = new Node(strs[i][j]);
                    map.put(strs[i][j], node);
                    graph.add(node);
                }
                Node curNode = map.get(strs[i][j]);
                boolean hasPrev = (i > 0)
                        && (j < input[i - 1].length())
                        && (strs[i - 1][j] != strs[i][j])
                        && input[i - 1].substring(0, j).equals(input[i].substring(0, j));
                if (hasPrev) {
                    map.get(strs[i - 1][j]).neighbors.add(curNode);
                }
            }
        }

        Deque<Character> stack = new ArrayDeque<>();
        Set<Character> inStack = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            if (!node.visited) {
                dfs(node, stack, inStack);
            }
            while (!stack.isEmpty()) {
                sb.append(stack.pollLast());
            }
        }
        if (!valid) return "";
        return sb.toString();
    }

    private void dfs(Node node, Deque<Character> stack, Set<Character> inStack) {
        node.visited = true;
        for (Node nei : node.neighbors) {
            if (!nei.visited) {
                dfs(nei, stack, inStack);
            }
        }

        if (inStack.contains(node.ch)) {
            valid = false;
            return;
        }

        inStack.add(node.ch);
        stack.offerLast(node.ch);
    }

    static class Node extends Object{
        private final char ch;
        Set<Node> neighbors;
        public boolean visited;

        public Node(char ch) {
            this.ch = ch;
            neighbors = new HashSet<>();
        }

        @Override
        public int hashCode() {
            return (int)ch;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof Node)) {
                return false;
            }

            return this.hashCode() == o.hashCode();
        }
    }
}

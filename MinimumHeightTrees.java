package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> sol = new ArrayList<>();
        //corner case
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            sol.add(0);
            return sol;
        }

        GraphNode[] view = new GraphNode[n];
        for (int i = 0; i < n; i++) {
            view[i] = new GraphNode(n, i);
        }

        for (int[] edge : edges) {
            view[edge[0]].setNei(edge[1]);
            view[edge[1]].setNei(edge[0]);
        }

        int globalMin = n + 1;

        for (GraphNode curNode : view) {

            boolean[] visited = new boolean[n];
            int depth = 0;
            Queue<GraphNode> q = new LinkedList<>();
            q.offer(curNode);
            visited[curNode.val] = true;

            while (!q.isEmpty()) {
                int size = q.size();
                for (int j = 0; j < size; j++) {
                    GraphNode thisNode = q.poll();
                    for (int i = 0; i< n; i++) {
                        if (thisNode.neighbors[i] && !visited[i]) {
                            q.offer(view[i]);
                            visited[i] = true;
                        }
                    }
                }
                depth++;
            }

            if (depth < globalMin) {
                globalMin = depth;
                sol.clear();
                sol.add(curNode.val);
            } else if (depth == globalMin) {
                sol.add(curNode.val);
            }
        }

        return sol;
    }

    class GraphNode{
        int val;
        boolean[] neighbors;
        public GraphNode(int n, int val) {
            this.val = val;
            neighbors = new boolean[n];
        }
        public void setNei(int nei) {
            neighbors[nei] = true;
        }
    }
}

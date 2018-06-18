/*
* Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

    20810526
    332
    medium
    DFS
    O(nlogn)
    O(n)
* */
package leetcode;

import java.util.*;

public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] pair : tickets) {
            if (!graph.containsKey(pair[0])) {
                graph.put(pair[0], new PriorityQueue<String>());
            }
            graph.get(pair[0]).offer(pair[1]);
        }
        List<String> sol = new ArrayList<>();
        DFS("JFK", graph, sol);
        Collections.reverse(sol);
        return sol;
    }

    private void DFS(String start, Map<String, PriorityQueue<String>> graph, List<String> sol) {
        PriorityQueue<String> destinations = graph.get(start);
        while (destinations != null && !destinations.isEmpty()) {////////////////////
            DFS(destinations.poll(), graph, sol);
        }
        sol.add(start);
    }
}

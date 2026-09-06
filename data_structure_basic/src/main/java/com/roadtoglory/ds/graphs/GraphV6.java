/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.graphs;
/*
*
*

This class GraphV6 is created and managed by subhe
Created on 16-06-2026 at 07:24 for the project Undirected Basic Graph Implementation With List

*
*
*/

import java.util.*;

public class GraphV6 extends GraphV3 {

    public List<Integer> bfs() {
        List<List<Integer>> adjList = getAdjList();
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        Set<Integer> alrAddedToQ = new HashSet<>();
        for (int i = 0; i < adjList.size(); i++) {
            if (alrAddedToQ.add(i)) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    Integer node = queue.poll();
                    result.add(node);
                    List<Integer> neighbours = adjList.get(node);
                    for (Integer nei : neighbours) {
                        if (alrAddedToQ.add(nei)) {
                            queue.add(nei);
                        }
                    }

                }
            }
        }
        return result;
    }

    public List<Integer> dfs() {
        List<List<Integer>> adjList = getAdjList();
        List<Integer> result = new ArrayList<>();
        boolean[] isAddedList = new boolean[adjList.size()];
        for (int i = 0; i < adjList.size(); i++) {
            dfs(i, result, isAddedList, adjList);
        }
        return result;
    }

    private void dfs(int node, List<Integer> result, boolean[] isAddedList, List<List<Integer>> adjList) {
        if (!isAddedList[node]) {
            result.add(node);
            isAddedList[node] = true;
        }
        for (Integer neighbour : adjList.get(node)) {
            if (!isAddedList[neighbour]) {
                dfs(neighbour, result, isAddedList, adjList);
            }
        }
    }

    public boolean isCycle() {
        List<List<Integer>> adjList = getAdjList();
        boolean result = false;
        boolean[] visited = new boolean[adjList.size()];
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) {
                result = isCycle(i, -1, visited, adjList);
                if (result) {
                    return true;
                }
            }

        }
        return result;
    }

    private boolean isCycle(int node, int lastVisited, boolean[] visited, List<List<Integer>> adjList) {
        if (!visited[node]) {
            visited[node] = true;
            for (Integer neighbour : adjList.get(node)) {
                if (neighbour != lastVisited) {
                    boolean result = isCycle(neighbour, node, visited, adjList);
                    if (result) {
                        return true;
                    }
                }
            }

        }
        else {
            return true;
        }
        return false;

    }


}

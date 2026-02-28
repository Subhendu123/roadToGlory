/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.graphs;
/*
*
*

This class GraphV4 is created and managed by subhe
Created on 05-02-2026 at 06:47 for the project Udemy Directed Graph implementation

* ***************** DIRECTED GRAPH *****************************
*
*
*/

import java.util.*;

public class GraphV4 {

    public static final int COUNTER = -1;
    private static int count = 0;
    private static List<List<Integer>> result = new ArrayList<>();
    private static Integer resultArrIndex = 0;
    Stack<Integer> topoStack = new Stack<>();
    private List<List<Integer>> dirAdjList;
    private int capacity;

    public GraphV4() {
        this(4);
    }

    public GraphV4(int capacity) {
        this.dirAdjList = new ArrayList<>();
        this.capacity = capacity;
    }

    public void insert(int vertex) {
        if (this.dirAdjList.isEmpty() || vertex >= this.dirAdjList.size() || this.dirAdjList.get(vertex) == null) {
            this.dirAdjList.add(vertex, new ArrayList<>());
        }
    }

    public void insert(int vertex, int edge) {
        // Directed from vertx to edge.
        insert(vertex);
        insert(edge);
        this.dirAdjList.get(vertex).add(edge);
    }

    public void printGraph() {
        for (int i = 0; i < this.dirAdjList.size(); i++) {
            System.out.println("[ " + i + " -> " + this.dirAdjList.get(i) + " ]");
        }
    }

    public void printGraph(List<List<Integer>> directedGraph) {
        for (int i = 0; i < directedGraph.size(); i++) {
            System.out.println("[ " + i + " -> " + directedGraph.get(i) + " ]");
        }
    }

    public List<Integer> dfsTraversalBasic() {

        List<Integer> result = new ArrayList<>();
        boolean[] visitedNodes = new boolean[this.dirAdjList.size()];
        Arrays.fill(visitedNodes, false);


        for (int i = 0; i < this.dirAdjList.size(); i++) {
            dfsTraversal(result, i, visitedNodes);
        }
        return result;

    }

    public Stack<Integer> dfsTraversalBasic(boolean isRev) {

        Stack<Integer> result = new Stack<>();
        boolean[] visitedNodes = new boolean[this.dirAdjList.size()];
        Arrays.fill(visitedNodes, false);


        for (int i = 0; i < this.dirAdjList.size(); i++) {
            dfsTraversalRev(result, i, visitedNodes);
        }
        return result;

    }

    private void dfsTraversalRev(Stack<Integer> result, int vertex, boolean[] visitedNodes) {


        for (Integer neighbour : this.dirAdjList.get(vertex)) {
            if (!visitedNodes[neighbour]) {
                dfsTraversal(result, neighbour, visitedNodes);
            }
        }

        // All children Done - now print the main vertex
        if (!visitedNodes[vertex]) {
            // First Time Traversing through this element
            result.push(vertex);
            visitedNodes[vertex] = true;
        }

    }

    private void dfsTraversal(List<Integer> result, int vertex, boolean[] visitedNodes) {

        if (!visitedNodes[vertex]) {
            // First Time Traversing through this element
            result.add(vertex);
            visitedNodes[vertex] = true;
        }
        for (Integer neighbour : this.dirAdjList.get(vertex)) {
            if (!visitedNodes[neighbour]) {
                dfsTraversal(result, neighbour, visitedNodes);
            }
        }

    }

    public List<List<Integer>> sccKosaraju() {

        Stack<Integer> dfsRev = dfsTraversalBasic(true);
        List<List<Integer>> result = new ArrayList<>();
        System.out.println("dfs Rev " + dfsRev);

        List<List<Integer>> transposeGraphList = new ArrayList<>(this.dirAdjList.size());

        for (int i = 0; i < this.dirAdjList.size(); i++) {
            transposeGraphList.add(i, new ArrayList<>());
        }

        for (int i = 0; i < this.dirAdjList.size(); i++) {
            List<Integer> neighbours = this.dirAdjList.get(i);
            for (Integer neighbour : neighbours) {
                transposeGraphList.get(neighbour).add(i);
            }
        }
        System.out.println("The transpose graph ");
        printGraph(transposeGraphList);

        boolean[] visitedNodes = new boolean[transposeGraphList.size()];
        Arrays.fill(visitedNodes, false);

        int scc_counter = -1;
        for (Integer vertex : dfsRev) {
            if (!visitedNodes[vertex]) {
                scc_counter++;
                result.add(new ArrayList<>());
                dfsTraversalOfTransG(result, scc_counter, vertex, visitedNodes, transposeGraphList);
            }
        }
//        while (!dfsRev.empty()) {
//            Integer vertex = dfsRev.peek();
//            dfsRev.pop();
//            scc_counter++;
//            result.add(new ArrayList<>());
//            dfsTraversalOfTransG(result, scc_counter, vertex, visitedNodes, transposeGraphList);
//        }
        System.out.println("SCC Count " + scc_counter);
        return result;

    }

    private void dfsTraversalOfTransG(List<List<Integer>> result, Integer counter, Integer vertex,
                                      boolean[] visitedNodes,
                                      List<List<Integer>> transposeGraphList) {

        if (visitedNodes[vertex]) {
            return;
        }
        result.get(counter).add(vertex);
        visitedNodes[vertex] = true;

        List<Integer> reachableNeighbours = transposeGraphList.get(vertex);
        for (Integer neighbour : reachableNeighbours) {
            dfsTraversalOfTransG(result, counter, neighbour, visitedNodes, transposeGraphList);
        }
    }


    public List<List<Integer>> dfsTraversal() {
        Boolean[] visitedNodes = new Boolean[this.dirAdjList.size()];
        Arrays.fill(visitedNodes, false);
        for (int i = 0; i < this.dirAdjList.size(); i++) {

            if (!visitedNodes[i]) {
                dfsTraversal(i, null, visitedNodes, resultArrIndex);
//                topoStack.push(i);
            }
        }
        System.out.println("topo stack L " + topoStack);
        return result;
    }

    private void dfsTraversal(Integer vertex, Integer parent, Boolean[] visitedNodes, Integer resultArrIndex) {


        List<Integer> childEdges = this.dirAdjList.get(vertex);

        if (visitedNodes[vertex]) {
            // cycle identified
            List<Integer> currTrvList = result.get(resultArrIndex);
            if (currTrvList.contains(vertex)) {
                System.out.println("Yes Cycle detected now for the vertex " + vertex);
            }
            return;
        }
        visitedNodes[vertex] = true;
//        if(parent != null)
        if (resultArrIndex >= result.size()) {
            List<Integer> prevNodesToCopy = resultArrIndex > 0 ? result.get(resultArrIndex - 1) : null;
//            if (resultArrIndex <= result.size() && resultArrIndex > 0 && result.get(resultArrIndex - 1) != null) {
//                prevNodesToCopy = result.get(resultArrIndex - 1);
//            }

            if (prevNodesToCopy == null) {
                prevNodesToCopy = new ArrayList<>();
                prevNodesToCopy.add(vertex);
                // we can check the cycle here also
                result.add(prevNodesToCopy);
            }
            else {
                // collect till parents;
                if (parent != null) {
                    List<Integer> tillParentNode = new ArrayList<>();
                    for (Integer edge : prevNodesToCopy) {
                        tillParentNode.add(edge);

                        if (edge.compareTo(parent) == 0) {
                            break;
                        }
                    }
                    tillParentNode.add(vertex);
                    result.add(tillParentNode);
                }
            }


        }
        else {
            // if this is repeated, do not add in case of cycle detection
            result.get(resultArrIndex).add(vertex);
        }
        for (int i = 0; i < childEdges.size(); i++) {
            dfsTraversal(childEdges.get(i), vertex, visitedNodes, resultArrIndex);
            if (!topoStack.contains(childEdges.get(i))) topoStack.push(childEdges.get(i));
            if (i < childEdges.size() - 1) {
                // till the last but one index / element
                resultArrIndex++;
            }
        }
        if (!topoStack.contains(vertex)) topoStack.add(vertex);

    }

    public List<Integer> shortestDistanceFromV(Integer sourceVertex) {

        Map<Integer, Integer> visitedEdgeMap = new HashMap<>();
        for (int i = 0; i < this.dirAdjList.size(); i++) {
            if (!visitedEdgeMap.containsKey(i)) {
                if (i == 0) {
                    visitedEdgeMap.put(i, 0);
                }
            }
            List<Integer> childV = this.dirAdjList.get(i);
            for (Integer child : childV) {
                Integer childDist = visitedEdgeMap.get(i) + 1;
                if (visitedEdgeMap.containsKey(child)) {
                    Integer childOldDist = visitedEdgeMap.get(child);
                    if (childOldDist > childDist) {
                        visitedEdgeMap.put(child, childDist);
                    }
                }
                else {
                    visitedEdgeMap.put(child, visitedEdgeMap.get(i) + 1);
                }
            }

        }
        return visitedEdgeMap.values().stream().toList();

    }

    public boolean isCycle() {
       /* Map<Integer, Boolean> visitedVerticesMap = new HashMap<>();
        List<Integer> traversedVetices = new ArrayList<>();
        int index = 0;
        for (; index < this.dirAdjList.size(); index++) {
            if (!visitedVerticesMap.containsKey(index)) {
                boolean isACycleTrue = isCycle(index, visitedVerticesMap, traversedVetices);
                if (isACycleTrue) {
                    return true;
                }
            }

        }
        return false;*/


        Boolean[] visitedNodes = new Boolean[this.dirAdjList.size()];
        List<List<Integer>> trvList = new ArrayList<>();
        Integer resultListIndex = 0;
        Arrays.fill(visitedNodes, false);
        boolean isCycle = false;
        for (int i = 0; i < this.dirAdjList.size(); i++) {

            if (!visitedNodes[i]) {
//                isCycle = isCycleDFS(i, null, visitedNodes, resultArrIndex, trvList);
                if (isCycle) {
                    break;
                }
            }
        }
        return isCycle;
    }

    /*public boolean isCycleDFS(Integer vertex, Integer parent, Boolean[] visitedNodes, Integer resultListIndex,
                              List<List<Integer>> trvList) {
        List<Integer> childEdges = this.dirAdjList.get(vertex);

        if (visitedNodes[vertex]) {
            // cycle identified
            List<Integer> currTrvList = trvList.get(resultListIndex);
            if (currTrvList.contains(vertex)) {
                return true;
            }
        }
        visitedNodes[vertex] = true;
//        if(parent != null)
        if (resultListIndex >= trvList.size()) {
            List<Integer> prevNodesToCopy = resultListIndex > 0 ? trvList.get(resultListIndex - 1) : null;
//            if (resultArrIndex <= result.size() && resultArrIndex > 0 && result.get(resultArrIndex - 1) != null) {
//                prevNodesToCopy = result.get(resultArrIndex - 1);
//            }

            if (prevNodesToCopy == null) {
                prevNodesToCopy = new ArrayList<>();
                prevNodesToCopy.add(vertex);
                // we can check the cycle here also
                trvList.add(prevNodesToCopy);
            }
            else {
                // collect till parents;
                if (parent != null) {
                    List<Integer> tillParentNode = new ArrayList<>();
                    for (Integer edge : prevNodesToCopy) {
                        tillParentNode.add(edge);

                        if (edge.compareTo(parent) == 0) {
                            break;
                        }
                    }
                    tillParentNode.add(vertex);
                    trvList.add(tillParentNode);
                }
            }


        }
        else {
            // if this is repeated, do not add in case of cycle detection
            result.get(resultListIndex).add(vertex);
        }

        for (int i = 0; i < childEdges.size(); i++) {
            if (i == 0) {
                // till the last but one index / element
                resultListIndex++;
            }
            return isCycleDFS(childEdges.get(i), vertex, visitedNodes, resultListIndex, trvList);
        }
        return false;
    }*/

    public int countCycle() {
        Map<Integer, Boolean> visitedVerticesMap = new HashMap<>();
        List<Integer> traversedVetices = new ArrayList<>();
        count = 0;
        int index = 0;
        for (; index < this.dirAdjList.size(); index++) {

            count += countCycle(index, visitedVerticesMap, traversedVetices);
        }
        return count;
    }

    private synchronized int countCycle(int vertex, Map<Integer, Boolean> visitedVerticesMap,
                                        List<Integer> traversedVetices) {

        if (visitedVerticesMap.containsKey(vertex)) {
            return 0;
        }
        List<Integer> edges = this.dirAdjList.get(vertex);
        traversedVetices.add(vertex);
        visitedVerticesMap.put(vertex, true);
        if (edges == null || edges.isEmpty()) {
            return 0;
        }
        for (Integer edge : edges) {
            if (traversedVetices.contains(edge) && traversedVetices.size() > 2) {
                count++;
//                System.out.println("Cycle detected for [ " + edge + " " + vertex + " ] and the count is " + count);
            }
            countCycle(edge, visitedVerticesMap, traversedVetices);
        }

        return count;
    }

    private Boolean isCycle(int vertex, Map<Integer, Boolean> visitedVerticesMap, List<Integer> traversedVetices) {

        if (visitedVerticesMap.containsKey(vertex)) {
            return false;
        }
        List<Integer> edges = this.dirAdjList.get(vertex);
        traversedVetices.add(vertex);
        visitedVerticesMap.put(vertex, true);
        if (edges == null || edges.isEmpty()) {
            return false;
        }
        for (Integer edge : edges) {
            if (traversedVetices.contains(edge)) {
                return true;
            }
            return isCycle(edge, visitedVerticesMap, traversedVetices);
        }

        return false;

    }

    public List<Integer> topologicalSortWithDfs() {

        dfsTraversal();
        return topoStack;

    }

    public List<Integer> topologicalSort() {

        HashMap<Integer, List<Integer>> childParentRelMap = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < this.dirAdjList.size(); i++) {

            List<Integer> children = this.dirAdjList.get(i);
            for (Integer edge : children) {
                if (!edge.equals(Integer.MIN_VALUE)) {
                    List<Integer> parents = childParentRelMap.getOrDefault(edge, new ArrayList<>());
                    parents.add(i);
                    childParentRelMap.put(edge, parents);
                }
            }
        }

        boolean isACycle = true;

        // Find out the nodes / vertices with 0 parent. The super parents.
        for (int i = 0; i < this.dirAdjList.size(); i++) {
            if (!childParentRelMap.containsKey(i)) {
                resultList.add(i);
                isACycle = false;
            }
        }
        if (isACycle) {
            System.out.println("This is a cycle");
            return resultList;
        }
        int index = 0;
        while (!childParentRelMap.isEmpty()) {
            if (!childParentRelMap.containsKey(index)) {
                index++;
                continue;
            }
            List<Integer> parents = childParentRelMap.get(index);
            for (Integer parentEdge : parents) {
                if (!resultList.contains(parentEdge)) {
                    // it has some parent that should be printed first
                    resultList.add(parentEdge);
                }
            }
            resultList.add(index);
            childParentRelMap.remove(index);
            index++;

        }

        return resultList;

    }
}

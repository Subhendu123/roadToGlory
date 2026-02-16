/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.graphs;
/*
*
*

This class GraphV3 is created and managed by subhe
*
* This is an Undirected Graph Implementation with List of List approach
Created on 02-02-2026 at 21:20 for the project Udemy Graph Impl

*
*
*/

import java.util.*;

public class GraphV3 {


    private List<List<Integer>> adjList = null;

    public GraphV3() {
        this(4);
    }

    public GraphV3(int capacity) {
        this.adjList = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            this.adjList.add(new ArrayList<>());
        }
    }

    public void addVertex(int vertex) {
        if (vertex >= this.adjList.size()) {
            this.adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int vertex, int edge) {
        addVertex(vertex);
        this.adjList.get(vertex).add(edge);
        addVertex(edge);
        this.adjList.get(edge).add(vertex);
    }

    public void printGraph() {
        for (int i = 0; i < this.adjList.size(); i++) {
            System.out.println("[ " + i + " -> " + this.adjList.get(i) + " ]");
        }
    }

    public List<Integer> dfsTraversal(int vertex, Map<Integer, Boolean> visitedEdges, List<Integer> result) {

        result.add(vertex);
        visitedEdges.put(vertex, true);
        List<Integer> children = this.adjList.get(vertex);
        if (children == null || children.isEmpty()) {
            return null;
        }
        for (Integer edge : children) {
            if (!visitedEdges.containsKey(edge)) {
                dfsTraversal(edge, visitedEdges, result);
            }
        }


        return result;
    }

    public List<Integer> dfsTraversal(int vertex) {
        HashMap<Integer, Boolean> visitedEdges = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int count = 0;
        while (visitedEdges.size() != this.adjList.size()) {
            List<Integer> remEdges = this.adjList.get(visitedEdges.size());
            for (Integer edge : remEdges) {
                dfsTraversal(edge, visitedEdges, result);
            }
            count++;
        }

        System.out.println("Count is " + count);
        return result;
    }

    public List<Integer> shortestPath() {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> resMap = new HashMap<>();
        resMap.put(0, 0);
        result.add(0);

        for (int i = 0; i < this.adjList.size(); i++) {
            List<Integer> edges = this.adjList.get(i);
            for (int edge : edges) {
                if (!resMap.containsKey(edge)) {
                    resMap.put(edge, resMap.get(i) + 1);
                    result.add(resMap.get(i) + 1);
                }
            }

        }
        return result;
    }

    public boolean isCycle(boolean dfs) {

        HashMap<Integer, Boolean> visitedEdges = new HashMap<>();

        int counter = 0;
        cycleDetection(0, counter);

        System.out.println("Coumet " + counter);
        return counter > 0;
    }

    private void cycleDetection(int evalVertex, int counter) {

        if (evalVertex >= this.adjList.size()) {
            return;
        }

        List<Integer> childrenEdges = this.adjList.get(evalVertex);

        Set<Integer> nonParentEdges = new HashSet<>();
        if (childrenEdges != null && childrenEdges.size() > 1) {

            for (Integer child : childrenEdges) {

                List<Integer> nestedChildren = this.adjList.get(child);
                if (nestedChildren != null && !nestedChildren.isEmpty()) {
                    for (Integer nestedChild : nestedChildren) {
                        if (nestedChild != evalVertex && !nonParentEdges.add(nestedChild)) {
                            counter++;
                            System.out.println(
                                    "The counter is " + counter + " and the child [" + nestedChild + " , " + child + "] for parent v " + evalVertex);
//                            return;
                        }
                        System.out.println("nested non parent edges " + nonParentEdges.toString());
                    }
                }

            }
        }
        cycleDetection(evalVertex + 1, counter);

    }

    public boolean isCycle() {
        for (int i = 0; i < this.adjList.size(); i++) {
            List<Integer> children = this.adjList.get(i);
            Map<Integer, Integer> cycleDetectionMap = new HashMap<>();
            if (children.size() > 1) {
                for (Integer edge : children) {
                    List<Integer> childrenEdges = this.adjList.get(edge);
                    for (Integer cedge : childrenEdges) {
                        if (cedge != i) {
                            cycleDetectionMap.put(cedge, cycleDetectionMap.getOrDefault(cedge, 0) + 1);
                        }
                        if (cycleDetectionMap.containsKey(cedge) && cycleDetectionMap.get(cedge) > 1) {
                            return true;
                        }
                    }
                }
            }

        }
        return false;
    }


    public List<Integer> bfsTraversal(int vertex) {
        List<Integer> result = new ArrayList<>();
        result.add(vertex);
        List<Integer> edges = this.adjList.get(vertex);
        result.addAll(edges);
        Set<Integer> set = new HashSet<>(result);
        for (Integer edge : edges) {
            List<Integer> elments = this.adjList.get(edge);
            for (Integer e : elments) {
                if (set.add(e)) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    public List<Integer> bfsTraversal() {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < this.adjList.size(); i++) {
            if (set.add(i)) {
                result.add(i);
            }
            List<Integer> edges = this.adjList.get(i);
            for (Integer edge : edges) {
                if (set.add(edge)) {
                    result.add(edge);
                }
            }


        }
        return result;
    }

    public Integer countConnectedGraphs() {
        int count = 0;
        Boolean[] vistedArr = new Boolean[this.adjList.size()];
        for (int i = 0; i < vistedArr.length; i++) {
            vistedArr[i] = false;
        }


        for (int i = 0; i < this.adjList.size(); i++) {
            if (vistedArr[i]) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>(this.adjList.get(i));
            vistedArr[i] = true;

            while (!queue.isEmpty()) {

                Integer e = queue.poll();
                vistedArr[e] = true;
                for (Integer element : this.adjList.get(e)) {
                    if (!queue.contains(element) && !vistedArr[element]) {
                        queue.add(element);
                    }
                }

            }
            count++;


        }
        return count;
    }

    public List<Integer> topologicalSort() {

        HashMap<Integer, List<Integer>> childParentRelMap = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < this.adjList.size(); i++) {

            List<Integer> children = this.adjList.get(i);
            for (Integer edge : children) {
                if (!edge.equals(Integer.MIN_VALUE)) {

                    List<Integer> parents = childParentRelMap.getOrDefault(edge, new ArrayList<>());
                    parents.add(i);
                    childParentRelMap.put(edge, parents);
                }
            }
        }

        int index = 0;
        while (!childParentRelMap.isEmpty()) {
            List<Integer> parents = childParentRelMap.get(index);
            if (parents.isEmpty()) {
                resultList.add(index);
                childParentRelMap.remove(index);
                index++;
            }
            else {
                boolean doPrint = true;
                for (Integer parentEdge : parents) {
                    if (resultList.contains(parentEdge)) {
                        resultList.remove(parentEdge);
                    }
                    else {
                        // it has some parent that is still not traversed
                        index = parentEdge;
                        doPrint = false;
                        // to check if this parent is printed or empty
                    }
                }
                if (doPrint) {
                    // All the
                    resultList.add(index);
                    childParentRelMap.remove(index);
                    index++;
                }


            }
        }

        return resultList;

    }
}

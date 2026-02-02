/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.graphs;
/*
*
*

This class GraphV2 is created and managed by subhe
Created on 29-01-2026 at 07:00 for the project Udemy Geeks For Geeks

*
*
*/

import java.util.*;

public class GraphV2 {

    private static Map<Integer, List<Integer>> graphData = null;
    private boolean isUndirected = false;

    public GraphV2() {
        new GraphV2(true);
    }

    public GraphV2(boolean undirected) {
        this.graphData = new LinkedHashMap<>();
        System.out.println("Creating the graph data " + this.graphData);
        this.isUndirected = undirected;
    }

    public boolean isUndirected() {
        return isUndirected;
    }

    public void createGraphWithList(int[] input) {

    }


    public void addEdge(int vertex, int edge) {
        // check if the vertex is already added
        if (!graphData.containsKey(vertex)) {
            // vertex does not exist
            List<Integer> edges = new ArrayList<>();
            edges.add(edge);
            graphData.put(vertex, edges);
        }
        else {
            graphData.get(vertex).add(edge);
        }

        // for undirected graph
        if (isUndirected()) {
            if (!graphData.containsKey(edge)) {
                // vertex does not exist
                List<Integer> edges = new ArrayList<>();
                edges.add(vertex);
                graphData.put(edge, edges);
            }
            else {
                graphData.get(edge).add(vertex);
            }
        }
    }

    public void addVertex(int vertex) {
        // check if the vertex is already added
        if (graphData != null && !graphData.containsKey(vertex)) {
            // vertex does not exist
            graphData.put(vertex, new ArrayList<>());
        }
    }

    public List<Integer> bfsTraversal(int sourceVertex) {

        List<Integer> result = null;
        Set<Integer> resultSet = new HashSet<>();
        if (graphData.containsKey(sourceVertex)) {
            result = new ArrayList<>();
            result.add(sourceVertex);
            resultSet.add(sourceVertex);
            List<Integer> sourceEdges = graphData.get(sourceVertex);
            result.addAll(sourceEdges);
            resultSet.addAll(sourceEdges);
            for (Integer edge : sourceEdges) {
                List<Integer> childEdges = graphData.get(edge);
                if (childEdges != null) {
                    for (Integer childEdge : childEdges) {
                        if (resultSet.add(childEdge)) {
                            result.add(childEdge);
                        }
                    }
                }
            }
        }
        return result;

    }

    public void printGraph() {
        if (graphData == null || graphData.isEmpty()) {
            return;
        }
        for (Map.Entry<Integer, List<Integer>> key : graphData.entrySet()) {
            System.out.println(key.getKey() + " -> " + key.getValue());
        }
    }


}

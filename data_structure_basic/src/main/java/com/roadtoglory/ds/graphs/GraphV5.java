/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.graphs;
/*
*
*

This class GraphV5 is created and managed by subhe
Created on 16-02-2026 at 08:00 for the project Weighted Undirected Graph Implementation - Udemy

*
*
*/

import java.util.*;

public class GraphV5 {

    private List<List<Integer>> graphAdjList;
    private Map<String, Integer> edgeWeights;

    private Integer getWeight(Integer vertex, Integer edge) {
//        String key = String.valueOf(new StringBuilder(vertex).append("-").append(edge));
        String key = vertex + "-" + edge;
        if (edgeWeights.containsKey(key)) {
            return edgeWeights.get(key);
        }
        else {
            key = edge + "-" + vertex;
            return edgeWeights.get(key);
        }
    }

    private void setWeight(Integer vertex, Integer edge, Integer weight) {
        String key = vertex + "-" + edge;
        if (edgeWeights == null) {
            edgeWeights = new HashMap<>();
        }
        edgeWeights.put(key, weight);
    }

    public void add(Integer vertex) {
        if (this.graphAdjList == null) {
            this.graphAdjList = new ArrayList<>();
        }
        if (vertex >= this.graphAdjList.size()) {
            this.graphAdjList.add(new ArrayList<>());
        }


    }

    public void add(Integer vertex, Integer edge) {
        add(vertex);
        add(edge);
        this.graphAdjList.get(vertex).add(edge);
        this.graphAdjList.get(edge).add(vertex);
    }

    public void add(Integer vertex, Integer edge, Integer weight) {
        add(vertex, edge);
        setWeight(vertex, edge, weight);
    }

    public void printGraph() {
        for (int i = 0; i < this.graphAdjList.size(); i++) {
            System.out.println("[ " + i + " -> " + this.graphAdjList.get(i) + " ]");
        }
        // print the weight
        for (Map.Entry<String, Integer> entry : edgeWeights.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public Integer minSpanningTree() {

        Set<Integer> includedMST = new HashSet<>();
        Set<Integer> allVertex = new HashSet<>();
        for (int i = 0; i < this.graphAdjList.size(); i++) {
            if (i != 0) {
                allVertex.add(i);
            }
        }
        int result = 0;
        for (int i = 0; i < this.graphAdjList.size(); i++) {

            includedMST.add(i);
//            if (includedMST.add(i)) {
            int lowestWeight = 0;
            int lowestVertex = -1;
            int selectedMst = i;
            // find the lowest weighted edge connected to this tree includedMST
            for (Integer mstV : includedMST) {

                for (Integer vertex : allVertex) {
                    List<Integer> connectedV = this.graphAdjList.get(vertex);
                    if (!mstV.equals(vertex) && connectedV.contains(mstV)) {
                        int weight = getWeight(mstV, vertex);
                        if (lowestWeight == 0) {
                            lowestWeight = weight;
                            lowestVertex = vertex;
                            selectedMst = mstV;
                        }
                        else if (lowestWeight > weight) {
                            lowestWeight = weight;
                            lowestVertex = vertex;
                            selectedMst = mstV;

                        }

                    }
                }
            }

            if (lowestVertex > -1 && lowestWeight > 0) {

                includedMST.add(lowestVertex);
                result += lowestWeight;
//                    visitedV[lowestVertex] = true;
                allVertex.remove(lowestVertex);
                System.out.println(
                        "The  vertex [" + lowestVertex + "] and the corresponding weight [" + lowestWeight + "] are added to the existing vertex --> " + selectedMst + " <-- Min Spanning Tree ");
            }

//            visitedV[i] = true;
        }


//        }
        System.out.println("The min spanning tree graph is " + includedMST);
        System.out.println("The min edge weight count " + result);
        return result;
    }

    public Integer[] bellmanFordShortestPath() {
        Integer[] distanceV = new Integer[this.graphAdjList.size()];
        Arrays.fill(distanceV, Integer.MAX_VALUE);
        distanceV[0] = 0;

        for (int i = 0; i < this.graphAdjList.size() - 1; i++) {
            for (Map.Entry<String, Integer> mapEntry : edgeWeights.entrySet()) {
                String[] vertex = mapEntry.getKey().split("-");
                Integer u = Integer.valueOf(vertex[0]);
                Integer v = Integer.valueOf(vertex[1]);
                int edgeWeight = mapEntry.getValue();
                if (distanceV[v] > edgeWeight + distanceV[u]) {
                    distanceV[v] = edgeWeight + distanceV[u];
                }
            }


        }
        return distanceV;
    }

    public Integer[] dijkstraShortestDistance() {
        Integer[] distanceV = new Integer[this.graphAdjList.size()];
        Arrays.fill(distanceV, Integer.MAX_VALUE);
        distanceV[0] = 0;

        for (int i = 0; i < this.graphAdjList.size(); i++) {
            List<Integer> neiV = this.graphAdjList.get(i);

            for (Integer nEdge : neiV) {
                int edgeWeight = this.getWeight(i, nEdge);
                int currDistance = distanceV[nEdge];
                if (currDistance > edgeWeight + distanceV[i]) {
                    currDistance = edgeWeight + distanceV[i];
                    distanceV[nEdge] = currDistance;
                }

            }
        }
        return distanceV;
    }

}

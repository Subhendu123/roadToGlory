package com.roadtoglory.ds.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class Graph
{
    public HashMap<String, List<String>> adjList = null;

    Graph ()
    {
        if (adjList == null) adjList = new HashMap<>();
    }

    public boolean addVertex (String vertex)
    {
        if (adjList.get(vertex) == null)
        {
            // new one
            adjList.put(vertex, new ArrayList<>());
            return true;
        }
        return false;
    }

    public void addEdge (String existingV, String appendV)
    {
        if (adjList.get(existingV) != null && adjList.get(appendV) != null)
        {
            List<String> edgesInVertex = adjList.get(existingV);
            edgesInVertex.add(appendV);
            //  addVertex(appendV);
            List<String> edgesInAppendV = adjList.get(appendV);
            edgesInAppendV.add(existingV);

        }
    }

    public void printGraph ()
    {
        System.out.println(adjList);
    }

    public boolean removeVertex (String vertex)
    {
        if (adjList.get(vertex) != null)
        {
            adjList.remove(vertex);
            adjList.values().stream().forEach(s -> s.remove(vertex));
            return true;
        }
        throw new RuntimeException("Please check the value of the vertex passed!");
    }
}

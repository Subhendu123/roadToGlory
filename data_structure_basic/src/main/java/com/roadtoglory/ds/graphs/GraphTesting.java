package com.roadtoglory.ds.graphs;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class GraphTesting
{
    public static void main (String[] args)
    {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("E");
        graph.addVertex("C");
        graph.addVertex("B");

        graph.addEdge("A", "E");
        graph.addEdge("A", "C");
        graph.addEdge("C", "B");
        graph.addEdge("E", "B");

        graph.removeVertex("B");

        graph.printGraph();

    }
}

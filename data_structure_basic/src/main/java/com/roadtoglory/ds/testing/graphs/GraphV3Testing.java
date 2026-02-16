/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.graphs;
/*
*
*

This class GraphV2Testing is created and managed by subhe
Created on 01-02-2026 at 19:57 for the project Udemy LEarning Graph

*
*
*/

import com.roadtoglory.ds.graphs.GraphV3;

public class GraphV3Testing {

    public static void main(String[] args) {

        GraphV3 graphV3 = new GraphV3();
       /* graphV3.addVertex(0);
        graphV3.addEdge(0, 1);
        graphV3.addEdge(0, 4);
        graphV3.addEdge(1, 2);
        graphV3.addVertex(3);
        graphV3.addEdge(2, 3);
        graphV3.addEdge(4, 5);
        graphV3.addEdge(5, 6);
        graphV3.addEdge(6, 4);
        graphV3.printGraph();

        System.out.println("\n DFS Traversal Results (With Vertex Specified): " + graphV3.dfsTraversal(0));*/

        /*graphV3 = new GraphV3();
        graphV3.addVertex(0);
        graphV3.addEdge(0, 1);
        graphV3.addEdge(0, 4);
        graphV3.addEdge(1, 2);
        graphV3.addVertex(3);
        graphV3.addEdge(2, 3);
        graphV3.addEdge(5, 6);
        graphV3.printGraph();*/

//        graphV3 = new GraphV3();
//        graphV3.addVertex(0);
//        graphV3.addEdge(0, 1);
//        graphV3.addEdge(0, 2);
//        graphV3.addEdge(0, 4);
//        graphV3.addEdge(1, 3);
//        graphV3.addEdge(2, 3);
//        graphV3.addEdge(2, 4);
//        graphV3.addEdge(2, 3);
//        graphV3.addEdge(4, 5);
////        graphV3.printGraph();
//
////        System.out.println("\n Shortest Traversal: " + graphV3.shortestPath());
//        System.out.println("\n Is Cycle: " + graphV3.isCycle(true));
//        System.out.println("\n Is Cycle (OLD): " + graphV3.isCycle());


//        graphV3 = new GraphV3();
//        graphV3.addVertex(0);
//        graphV3.addEdge(0, 1);
//        graphV3.addEdge(1, 2);
//        graphV3.addEdge(1, 3);
//        graphV3.addEdge(2, 3);
//        graphV3.addEdge(2, 4);
//        graphV3.printGraph();
//        System.out.println("The graph is cyclic " + graphV3.isCycle(true));
//        System.out.println("The graph is cyclic (OLD BFS) " + graphV3.isCycle());
//        System.out.println("\n\nTopological Sorting: " + graphV3.topologicalSort());


       /* graphV3 = new GraphV3();
        graphV3.addVertex(0);
        graphV3.addEdge(0, 1);
        graphV3.addEdge(0, 2);
        graphV3.addEdge(1, 3);
        graphV3.addEdge(2, 3);
        graphV3.addEdge(3, 4);
        graphV3.addEdge(3, 5);
        graphV3.printGraph();
        System.out.println("The graph is cyclic " + graphV3.isCycle(true));
        System.out.println("The graph is cyclic (OLD BFS) " + graphV3.isCycle());
        System.out.println("\n\nTopological Sorting: " + graphV3.topologicalSort());*/


        graphV3 = new GraphV3();
        graphV3.addVertex(0);
        graphV3.addEdge(0, 2);
        graphV3.addEdge(0, 3);
        graphV3.addEdge(1, 3);
        graphV3.addEdge(1, 4);

        graphV3.printGraph();
//        System.out.println("The graph is cyclic " + graphV3.isCycle(true));
//        System.out.println("The graph is cyclic (OLD BFS) " + graphV3.isCycle());
        System.out.println("\n\nTopological Sorting: " + graphV3.topologicalSort());

        /*GraphV3 graphV3 = new GraphV3();
        graphV3.addVertex(0);
        graphV3.addEdge(0, 1);
        graphV3.addEdge(0, 2);
        graphV3.addEdge(0, 3);
        graphV3.addVertex(4);
        graphV3.addEdge(1, 4);
        graphV3.addEdge(2, 3);
        graphV3.addEdge(3, 4);
        graphV3.addVertex(5);
        graphV3.addEdge(5, 6);
        graphV3.printGraph();

        System.out.println("\n BFS Traversal Results (With Vertex Specified): " + graphV3.bfsTraversal(0));
        System.out.println("\n BFS Traversal Results: " + graphV3.bfsTraversal());
        System.out.println("\n Count Connected Graphs: " + graphV3.countConnectedGraphs());

        graphV3 = new GraphV3();
        graphV3.addVertex(0);
        graphV3.addEdge(0, 1);
        graphV3.addEdge(1, 2);
        graphV3.addEdge(3, 4);
        graphV3.addVertex(5);
        graphV3.addEdge(4, 5);

        graphV3.addVertex(6);
        graphV3.addEdge(6, 7);
        graphV3.printGraph();

        System.out.println("\n BFS Traversal Results (With Vertex Specified): " + graphV3.bfsTraversal(0));
        System.out.println("\n BFS Traversal Results: " + graphV3.bfsTraversal());
        System.out.println("\n Count Connected Graphs: " + graphV3.countConnectedGraphs());*/
    }
}

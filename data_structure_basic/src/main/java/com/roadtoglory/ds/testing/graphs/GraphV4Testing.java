/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.graphs;
/*
*
*

This class GraphV4Testing is created and managed by subhe
Created on 05-02-2026 at 06:58 for the project Udemy Test class for directed graph impl

*
*
*/

import com.roadtoglory.ds.graphs.DirectedGraph;

public class GraphV4Testing {

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(3, 1);
//        graph.printGraph();
//        System.out.println("Is Cycle (Directed Graph Impl wit DFS) " + graph.isCycle());
//        System.out.println("How Manu Cycles (Directed Graph Impl wit DFS) " + graph.countCycle());


        graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(2, 1);
        graph.insert(2, 3);
        graph.insert(1, 3);
//        graph.printGraph();
//        System.out.println("\nIs Cycle (Directed Graph Impl wit DFS) " + graph.isCycle());
//        System.out.println("How Manu Cycles (Directed Graph Impl wit DFS) " + graph.countCycle());


        graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(3, 4);
        graph.insert(4, 5);
        graph.insert(5, 3);
        graph.insert(5, 2);

//        graph.printGraph();
//        System.out.println("\nIs Cycle (Directed Graph Impl wit DFS) " + graph.isCycle());
//        System.out.println("How Manu Cycles (Directed Graph Impl wit DFS) " + graph.countCycle());

        graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(2, 1);
        graph.insert(2, 3);
        graph.insert(3, 4);
        graph.insert(4, 5);
        graph.insert(5, 3);
        graph.insert(5, 2);

//        graph.printGraph();
//        System.out.println("\nIs Cycle (Directed Graph Impl wit DFS) " + graph.isCycle());
//        System.out.println("How Manu Cycles (Directed Graph Impl wit DFS) " + graph.countCycle());


        graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(0, 2);
        graph.insert(0, 3);
        graph.insert(1, 3);
        graph.insert(1, 4);

        graph.printGraph();
//        System.out.println("The graph is cyclic " + graph.isCycle(true));
//        System.out.println("The graph is cyclic (OLD BFS) " + graph.isCycle());
        System.out.println("\n\nTopological Sorting: " + graph.topologicalSort());

    }
}

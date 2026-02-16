/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing;
/*
*
*

This class GraphV2Testing is created and managed by subhe
Created on 01-02-2026 at 19:57 for the project Udemy LEarning Graph

*
*
*/

import com.roadtoglory.ds.graphs.GraphV2;

public class GraphV2Testing {

    public static void main(String[] args) {
        GraphV2 graphV2 = new GraphV2();
        graphV2.addVertex(5);
        graphV2.addEdge(5, 4);
        graphV2.addEdge(5, 3);
        graphV2.addEdge(5, 10);

        graphV2.addEdge(4, 2);
        graphV2.addEdge(2, 10);
        graphV2.addEdge(3, 7);
        graphV2.addEdge(7, 10);
        graphV2.printGraph();

        System.out.println("\n BFS Traversal Results: " + graphV2.bfsTraversal(2));
        System.out.println("\n ");
        System.out.println("\n DFS Traversal Results: " + graphV2.dfsTraversal());

    }
}

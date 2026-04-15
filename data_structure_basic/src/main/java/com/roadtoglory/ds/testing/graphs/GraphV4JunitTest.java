/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.graphs;
/*
*
*

This class GraphV4JunitTest is created and managed by subhe
Created on 08-02-2026 at 08:49 for the project udemy graph impl

*
*
*/


import com.roadtoglory.ds.graphs.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphV4JunitTest {

    // ------------------------------------------------
    // TEST SET 1
    // Cycle: 1 -> 2 -> 3 -> 1
    // ------------------------------------------------
    @Test
    void testGraphSet1_ShouldDetectCycle() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(3, 1);

        graph.dfsTraversal();
    }

    @Test
    void testGraphSet2_ShouldDetectCycle() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(2, 4);
        graph.insert(4, 5);
        graph.insert(3, 6);
        graph.insert(6, 1);

        graph.dfsTraversal();
    }

    @Test
    void testGraph3_No_Cycle() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(2, 4);
        graph.insert(4, 5);
        graph.insert(3, 6);
        graph.insert(1, 6);

        graph.dfsTraversal();
    }

    // ------------------------------------------------
    // TEST SET 2
    // DAG (No Cycle)
    // ------------------------------------------------
    @Test
    void testGraphSet2_ShouldNotHaveCycle() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(2, 1);
        graph.insert(2, 3);
        graph.insert(1, 3);

        assertFalse(graph.isCycle(), "Graph should NOT have a cycle");
    }

    // ------------------------------------------------
    // TEST SET 3
    // Complex Cycle
    // ------------------------------------------------
    @Test
    void testGraphSet3_ShouldDetectCycle() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(3, 4);
        graph.insert(4, 5);
        graph.insert(5, 3);
        graph.insert(5, 2);

        assertTrue(graph.isCycle(), "Graph should have a cycle");
    }

    // ------------------------------------------------
    // TEST SET 4
    // Another Cycle Case
    // ------------------------------------------------
    @Test
    void testGraphSet4_ShouldDetectCycle() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(2, 1);
        graph.insert(2, 3);
        graph.insert(3, 4);
        graph.insert(4, 5);
        graph.insert(5, 3);
        graph.insert(5, 2);

        assertTrue(graph.isCycle(), "Graph should have a cycle");
    }

    // ------------------------------------------------
    // TEST SET 5
    // DAG → Topological Sort
    // ------------------------------------------------
    @Test
    void traversalAndTopo_Case001() {

        DirectedGraph graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(0, 2);
        graph.insert(1, 3);
        graph.insert(2, 4);
        graph.printGraph();
        List<List<Integer>> trave = graph.dfsTraversal();
        System.out.println("tr: \n " + trave);

        List<Integer> basicDFSTraversal = graph.dfsTraversalBasic();
        System.out.println("baseic dfs " + basicDFSTraversal);

//        assertFalse(graph.dfsTraversal(), "Graph should NOT have a cycle");

        List<Integer> topo = graph.topologicalSort();
        System.out.println("\n Topological Sorting: " + topo);

        assertFalse(topo.isEmpty(), "Topo sort should not be empty");


        // Weak validation (since topo order can vary)
        assertTrue(topo.contains(0));
        assertTrue(topo.contains(1));
        assertTrue(topo.contains(2));
        assertTrue(topo.contains(3));
        assertTrue(topo.contains(4));
    }

    @Test
    void kosaraju_Case001() {
        DirectedGraph graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 0);
        graph.insert(1, 3);
        graph.insert(3, 4);
        graph.printGraph();
        List<List<Integer>> kosaraju = graph.sccKosaraju();
        System.out.println("kosaraju: " + kosaraju);
    }

    @Test
    void dfsTraversal_Case002() {

        DirectedGraph graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(2, 0);
        graph.insert(1, 4);
        graph.insert(4, 5);


        graph.printGraph();
        List<List<Integer>> trave = graph.dfsTraversal();
        System.out.println("tr: \n " + trave);


        assertEquals(2, trave.size());
        assertEquals(List.of(new Integer[]{0, 1, 4, 5}), trave.get(1));
        assertEquals(List.of(new Integer[]{0, 1, 2, 3}), trave.get(0));

    }


    @Test
    void testTopology_Set2() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(0, 2);
        graph.insert(1, 3);
        graph.insert(2, 3);
        graph.insert(3, 4);
        graph.insert(3, 5);

        graph.printGraph();

        List<Integer> topo = graph.topologicalSort();
        System.out.println("\n Topological Sorting: " + topo);

        assertFalse(topo.isEmpty(), "Topo sort should not be empty");


        // Weak validation (since topo order can vary)
        assertTrue(topo.contains(0));
        assertTrue(topo.contains(1));
        assertTrue(topo.contains(2));
        assertTrue(topo.contains(3));
        assertTrue(topo.contains(4));
        assertTrue(topo.contains(5));
    }

    @Test
    void testTopology_Set3() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 0);
        graph.insert(2, 3);

        graph.printGraph();

        List<Integer> topo = graph.topologicalSort();
        List<Integer> topo2 = graph.topologicalSortWithDfs();
        System.out.println("\n Topological Sorting: " + topo);
        System.out.println("\n Topological Sorting 2: " + topo2);
        assertTrue(topo.isEmpty(), "Topo sort should be empty");
    }

    @Test
    void testTopology_Set4() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(1);
        graph.insert(0, 2);
        graph.insert(0, 3);
        graph.insert(1, 3);
        graph.insert(1, 4);
        graph.insert(5, 1);

        graph.printGraph();

        List<Integer> topo = graph.topologicalSortWithDfs();
        System.out.println("\n Topological Sorting: " + topo);
        assertFalse(topo.isEmpty(), "Topo sort should not be empty");
    }

    @Test
    void shortestDistance_Set001() {

        DirectedGraph graph = new DirectedGraph();

        graph.insert(0);
        graph.insert(1);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(1, 3);

        graph.printGraph();

        List<Integer> topo = graph.topologicalSortWithDfs();
        System.out.println("\n Topological Sorting: " + topo);
        assertFalse(topo.isEmpty(), "Topo sort should not be empty");

        List<Integer> verification = graph.shortestDistanceFromV(0);
        System.out.printf("Shortest Dis " + verification);
    }
}

/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.graphs;
/*
*
*

This class GraphV5JunitTesting is created and managed by subhe
Created on 16-02-2026 at 16:33 for the project undirected weighted graph implementation

*
*
*/

import com.roadtoglory.ds.graphs.GraphV5;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphV5JunitTesting {


    @Test
    @Order(1)
    public void sanity_check_001() {

        GraphV5 graph = new GraphV5();
        graph.add(0);
        graph.add(0, 1, 2);
        graph.add(2);
        graph.add(0, 2, 6);
        graph.add(1, 2, 3);
        graph.add(1, 3, 8);
        graph.add(1, 4, 5);
        graph.add(2, 4, 7);

        graph.printGraph();

    }

    @Test
    @Order(2)
    public void min_spanning_tree_tc_01() {

        GraphV5 graph = new GraphV5();
        graph.add(0);
        graph.add(0, 1, 2);
        graph.add(2);
        graph.add(1, 2, 3);
        graph.add(3);
        graph.add(0, 3, 6);
        graph.add(1, 3, 8);
        graph.add(1, 4, 5);
        graph.add(2, 4, 7);

        graph.printGraph();
        int minTotalWeight = graph.minSpanningTree();
        assertEquals(minTotalWeight, 16);

    }

    @Test
    @Order(3)
    public void shortest_path_Dijkstra_tc_01() {

        GraphV5 graph = new GraphV5();
        graph.add(0);
        graph.add(0, 1, 4);
        graph.add(2);
        graph.add(0, 2, 1);
        graph.add(1, 2, 2);

        graph.add(3);
        graph.add(2, 3, 4);
        graph.add(1, 3, 2);

        graph.add(3, 4, 1);
        graph.add(2, 4, 5);

        graph.printGraph();
        Integer[] shortestDistanceArr = graph.dijkstraShortestDistance();
        for (int i = 0; i < shortestDistanceArr.length; i++) {
            System.out.println(i + " -> " + shortestDistanceArr[i]);
        }
        assertEquals(shortestDistanceArr[0], 0);
        assertEquals(shortestDistanceArr[1], 3);
        assertEquals(shortestDistanceArr[2], 1);
        assertEquals(shortestDistanceArr[3], 5);
        assertEquals(shortestDistanceArr[4], 6);

    }

    @Test
    @Order(4)
    public void shortest_path_Dijkstra_tc_02() {

        GraphV5 graph = new GraphV5();
        graph.add(0);
        graph.add(0, 1, 2);
        graph.add(2);
        graph.add(1, 2, 3);
        graph.add(3);
        graph.add(0, 3, 6);
        graph.add(1, 3, 1);
        graph.add(1, 4, 5);
        graph.add(2, 4, 1);

        graph.printGraph();
        Integer[] shortestDistanceArr = graph.dijkstraShortestDistance();
        for (int i = 0; i < shortestDistanceArr.length; i++) {
            System.out.println(i + " -> " + shortestDistanceArr[i]);
        }

        assertEquals(shortestDistanceArr[0], 0);
        assertEquals(shortestDistanceArr[1], 2);
        assertEquals(shortestDistanceArr[2], 5);
        assertEquals(shortestDistanceArr[3], 3);
        assertEquals(shortestDistanceArr[4], 6);

    }

    @Test
    @Order(5)
    public void shortest_path_Dijkstra_tc_03() {

        GraphV5 graph = new GraphV5();
        graph.add(0);
        graph.add(0, 1, 1);
        graph.add(2);
        graph.add(0, 2, 4);
        graph.add(3);
        graph.add(2, 3, 1);
        graph.add(1, 3, 2);

        graph.printGraph();
        Integer[] shortestDistanceArr = graph.dijkstraShortestDistance();
        for (int i = 0; i < shortestDistanceArr.length; i++) {
            System.out.println(i + " -> " + shortestDistanceArr[i]);
        }

        assertEquals(shortestDistanceArr[0], 0);
        assertEquals(shortestDistanceArr[1], 1);
        assertEquals(shortestDistanceArr[2], 4);
        assertEquals(shortestDistanceArr[3], 3);

    }

    @Test
    @Order(6)
    public void shortest_path_Dijkstra_tc_04() {

        GraphV5 graph = new GraphV5();
        graph.add(0);
        graph.add(0, 1, 10);
        graph.add(2);
        graph.add(0, 2, 2);
        graph.add(1, 2, 1);

        graph.printGraph();
        Integer[] shortestDistanceArr = graph.dijkstraShortestDistance();
        for (int i = 0; i < shortestDistanceArr.length; i++) {
            System.out.println(i + " -> " + shortestDistanceArr[i]);
        }

        assertEquals(shortestDistanceArr[0], 0);
        assertEquals(shortestDistanceArr[1], 3);
        assertEquals(shortestDistanceArr[2], 2);

    }

    @Test
    @Order(7)
    public void shortest_path_Dijkstra_tc_06() {
        GraphV5 graph = new GraphV5();
        graph.add(0);
        graph.add(0, 1, 5);
        graph.add(0, 2, 2);
        graph.add(2, 1, -10);
        graph.printGraph();
        Integer[] shortestDistanceArr = graph.dijkstraShortestDistance();
        for (int i = 0; i < shortestDistanceArr.length; i++) {
            System.out.println(i + " -> " + shortestDistanceArr[i]);
        }
        assertEquals(shortestDistanceArr[0], 0);
        assertEquals(shortestDistanceArr[1], -8);
        assertEquals(shortestDistanceArr[2], -5);
    }


    // King of King Test case
    @Test
    @Order(7)
    public void shortest_path_Dijkstra_tc_05() {

        GraphV5 graph = new GraphV5();
        graph.add(0);
        graph.add(0, 1, 4);
        graph.add(2);
        graph.add(0, 2, 8);
        graph.add(1, 2, 11);
        graph.add(3);
        graph.add(4);
        graph.add(1, 3, 8);
        graph.add(2, 4, 7);
        graph.add(3, 4, 2);
        graph.add(4, 5, 6);
        graph.add(2, 5, 1);
        graph.add(6);
        graph.add(7);
        graph.add(8);
        graph.add(3, 6, 7);
        graph.add(3, 7, 4);
        graph.add(5, 7, 2);
        graph.add(6, 7, 14);
        graph.add(6, 8, 9);
        graph.add(7, 8, 10);


        graph.printGraph();
        Integer[] shortestDistanceArr = graph.dijkstraShortestDistance();
        for (int i = 0; i < shortestDistanceArr.length; i++) {
            System.out.println(i + " -> " + shortestDistanceArr[i]);
        }

        assertEquals(shortestDistanceArr[0], 0);
        assertEquals(shortestDistanceArr[1], 4);
        assertEquals(shortestDistanceArr[2], 8);
        assertEquals(shortestDistanceArr[3], 12);
        assertEquals(shortestDistanceArr[4], 14);
        assertEquals(shortestDistanceArr[5], 9);
        assertEquals(shortestDistanceArr[6], 19);
        assertEquals(shortestDistanceArr[7], 11);
        assertEquals(shortestDistanceArr[8], 21);

    }
}

/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.graphs;
/*
*
*

This class DirectedWeightedGraphV6Junit is created and managed by subhe
Created on 26-02-2026 at 07:38 for the project Udemy Learning Directed Weighted Graph Testing

*
*
*/

import com.roadtoglory.ds.graphs.DirectedWeightedGraphV6;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectedWeightedGraphV6Junit {

    @Test
    @Order(1)
    public void shortest_path_bellmanFord_tc_01() {
        DirectedWeightedGraphV6 graph = new DirectedWeightedGraphV6();
        graph.add(0);
        graph.add(0, 1, 5);
        graph.add(0, 2, 2);
        graph.add(2, 1, -10);
        graph.printGraph();
        Integer[] shortestDistanceArr = graph.bellmanFordShortestPath();
        for (int i = 0; i < shortestDistanceArr.length; i++) {
            System.out.println(i + " -> " + shortestDistanceArr[i]);
        }
        assertEquals(shortestDistanceArr[0], 0);
        assertEquals(shortestDistanceArr[1], -8);
        assertEquals(shortestDistanceArr[2], 2);
    }

    @Test
    @Order(2)
    public void shortest_path_bellmanFord_tc_02() {
        DirectedWeightedGraphV6 graph = new DirectedWeightedGraphV6();
        graph.add(0);
        graph.add(0, 1, 1);
        graph.add(0, 2, 4);
        graph.add(1, 2, -3);
        graph.add(1, 3, 2);
        graph.add(2, 3, 3);
        graph.printGraph();
        Integer[] shortestDistanceArr = graph.bellmanFordShortestPath();
        for (int i = 0; i < shortestDistanceArr.length; i++) {
            System.out.println(i + " -> " + shortestDistanceArr[i]);
        }
        assertEquals(shortestDistanceArr[0], 0);
        assertEquals(shortestDistanceArr[1], 1);
        assertEquals(shortestDistanceArr[2], -2);
        assertEquals(shortestDistanceArr[3], 1);
    }

    @Test
    @Order(3)
    @DisplayName("Simple Positive Graph for Bellman Ford")
    public void shortest_path_bellmanFord_tc_03() {

        DirectedWeightedGraphV6 graph = new DirectedWeightedGraphV6();

        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);

        graph.add(0, 1, 4);
        graph.add(0, 2, 1);
        graph.add(2, 1, 2);
        graph.add(1, 3, 1);
        graph.add(2, 3, 5);

        Integer[] dist = graph.bellmanFordShortestPath();

        assertEquals(0, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(1, dist[2]);
        assertEquals(4, dist[3]);
    }

    @Test
    @Order(4)
    @DisplayName("Negative Edge But No Negative Cycle")
    public void shortest_path_bellmanFord_tc_04() {

        DirectedWeightedGraphV6 graph = new DirectedWeightedGraphV6();

        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);

        graph.add(0, 1, 5);
        graph.add(0, 2, 2);
        graph.add(2, 1, -4);
        graph.add(1, 3, 3);
        graph.add(2, 3, 10);

        Integer[] dist = graph.bellmanFordShortestPath();

        assertEquals(0, dist[0]);
        assertEquals(-2, dist[1]);
        assertEquals(2, dist[2]);
        assertEquals(1, dist[3]);
    }

    @Test
    @Order(5)
    public void shortest_path_bellmanFord_negative_cycle() {

        DirectedWeightedGraphV6 graph = new DirectedWeightedGraphV6();

        graph.add(0);
        graph.add(1);
        graph.add(2);

        graph.add(0, 1, 1);
        graph.add(1, 2, -2);
        graph.add(2, 0, -2); // Negative cycle

        assertThrows(RuntimeException.class, () -> {
            graph.bellmanFordShortestPath();
        });
    }

    @Order(6)
    public void shortest_path_bellmanFord_disconnected() {

        DirectedWeightedGraphV6 graph = new DirectedWeightedGraphV6();

        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);

        graph.add(0, 1, 2);

        Integer[] dist = graph.bellmanFordShortestPath();

        assertEquals(0, dist[0]);
        assertEquals(2, dist[1]);
        assertEquals(Integer.MAX_VALUE, dist[2]);
        assertEquals(Integer.MAX_VALUE, dist[3]);
    }

    @Order(7)
    public void shortest_path_bellmanFord_chain() {

        DirectedWeightedGraphV6 graph = new DirectedWeightedGraphV6();

        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);
        graph.add(4);

        graph.add(0, 1, 1);
        graph.add(1, 2, 1);
        graph.add(2, 3, 1);
        graph.add(3, 4, 1);

        Integer[] dist = graph.bellmanFordShortestPath();

        assertEquals(0, dist[0]);
        assertEquals(1, dist[1]);
        assertEquals(2, dist[2]);
        assertEquals(3, dist[3]);
        assertEquals(4, dist[4]);
    }

    @Test
    @Order(8)
    public void shortest_path_bellmanFord_zero_weights() {

        DirectedWeightedGraphV6 graph = new DirectedWeightedGraphV6();

        graph.add(0);
        graph.add(1);
        graph.add(2);

        graph.add(0, 1, 0);
        graph.add(1, 2, 0);

        Integer[] dist = graph.bellmanFordShortestPath();

        assertEquals(0, dist[0]);
        assertEquals(0, dist[1]);
        assertEquals(0, dist[2]);
    }

}

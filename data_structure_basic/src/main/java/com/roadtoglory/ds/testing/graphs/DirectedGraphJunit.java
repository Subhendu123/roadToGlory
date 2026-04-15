/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.graphs;
/*
*
*

This class DirectedGraphJunit is created and managed by subhe
Created on 11-03-2026 at 19:20 for the project Udemy Directed Graph Testing

*
*
*/

import com.roadtoglory.ds.graphs.DirectedGraph;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DirectedGraphJunit {

    @Test
    public void testBasic() {
        DirectedGraph graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(1);
        graph.insert(2);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(3, 0);

        graph.printGraph();

        boolean isCycle = graph.isCycle();
        Assert.assertTrue(isCycle);
    }

    @Test
    public void testCycle002() {
        DirectedGraph graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(1);
        graph.insert(2);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(1, 3);
        graph.insert(2, 3);
        graph.insert(0, 3);

        graph.printGraph();

        boolean isCycle = graph.isCycle();
        Assert.assertFalse(isCycle);
    }

    @Test
    @DisplayName("Not a Cycle result")
    public void testCycle003() {
        DirectedGraph graph = new DirectedGraph();
        graph.insert(0);
        graph.insert(1);
        graph.insert(2);
        graph.insert(0, 3);
        graph.insert(1, 3);
        graph.insert(2, 1);
        graph.insert(2, 0);

        graph.printGraph();

        boolean isCycle = graph.isCycle();
        Assert.assertFalse(isCycle);
    }
}

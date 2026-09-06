/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.graphs;
/*
*
*

This class DirectGraphV2 is created and managed by subhe
Created on 29-06-2026 at 07:28 for the project Directed Graph

*
*
*/

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DirectGraphV2 extends DirectedGraph {
    public boolean isCycle() {
        Set<Integer> uniqueTr = new HashSet<>();
        boolean isCycle = false;

        for (int i = 0; i < this.dirAdjList.size(); i++) {
            if (uniqueTr.add(i)) {
                isCycle = isCycle(i, uniqueTr);
                if (isCycle) {
                    break;
                }
            }
        }
        return isCycle;

    }

    private boolean isCycle(int i, Set<Integer> uniqueTr) {
        List<Integer> neighbours = this.dirAdjList.get(i);
        boolean isCycle = false;
        for (Integer n : neighbours) {
            if (uniqueTr.add(n)) {
                isCycle = isCycle(n, uniqueTr);
                if (isCycle) {
                    break;
                }
            }
            else {
                return isCycle;
            }
        }
        return isCycle;
    }
}

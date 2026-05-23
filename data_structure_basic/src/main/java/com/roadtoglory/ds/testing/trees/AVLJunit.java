/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.trees;
/*
*
*

This class AVLJunit is created and managed by subhe
Created on 11-05-2026 at 21:05 for the project udemy testing

*
*
*/

import com.roadtoglory.ds.new_trees.AVLTree;
import org.junit.Test;

public class AVLJunit {

    @Test
    public void testAVL() {
        AVLTree tree = new AVLTree(21);
        tree.add(26);
        tree.add(30);
        tree.add(9);
        tree.add(4);
        tree.add(14);
        tree.add(28);

        System.out.println("Tree added");
    }
}

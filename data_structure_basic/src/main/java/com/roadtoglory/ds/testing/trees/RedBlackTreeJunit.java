/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.trees;
/*
*
*

This class RedBlackTreeJunit is created and managed by subhe
Created on 20-05-2026 at 12:38 for the project red black tree

*
*
*/

import com.roadtoglory.ds.new_trees.RedBlackTree;
import org.junit.Test;

public class RedBlackTreeJunit {

    @Test
    public void case1() {
        RedBlackTree tree = new RedBlackTree(50);
        tree.add(40);
        tree.add(60);
        tree.add(30);
        tree.add(35);
        tree.add(55);
        tree.add(45);
        tree.add(20);
        tree.add(25);
        tree.add(10);
        tree.add(70);
        tree.add(65);
        tree.add(80);
        tree.add(75);
        tree.add(85);
        tree.add(15);
        tree.add(42);

        System.out.println("Tree added");
    }
}

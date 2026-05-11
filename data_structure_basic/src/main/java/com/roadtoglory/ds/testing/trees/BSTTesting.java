/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.trees;
/*
*
*

This class BSTTesting is created and managed by subhe
Created on 08-05-2026 at 07:09 for the project Udemy Testing

*
*
*/

import com.roadtoglory.ds.new_trees.BinarySearchTree;
import org.junit.Test;

public class BSTTesting {

    @Test
    public void testInsertion() {
        BinarySearchTree tree = new BinarySearchTree(25);
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(7);
        tree.insert(15);
        tree.insert(27);
        tree.insert(50);
        System.out.println("Insertion Done with an error. Please check the output");

        System.out.println("Is the value 15 available in the tree? " + tree.search(15));
        System.out.println("Is the value 27 available in the tree? " + tree.search(27));
        System.out.println("Is the value 50 available in the tree? " + tree.search(50));
        System.out.println("Is the value 14 available in the tree? " + tree.search(14));

        System.out.println("The floor value of the 14 in the tree is " + tree.floor(14));
        System.out.println("The floor value of the 28 in the tree is " + tree.floor(28));
        System.out.println("The floor value of the 5 in the tree is " + tree.floor(5));

        tree.delete(7);

    }
}

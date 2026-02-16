/*
 * Copyright (c) 2025-2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.trees;
/*
*
*

This class AVLTreeImplTest is created and managed by subhe
Created on 03-07-2025 at 20:24 for the project Data Structure

*
*
*/

import com.roadtoglory.ds.trees.AVLTree;

public class AVLTreeImplTest {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
//        set1ForTest(tree);
        tree.insertAsAVL(10);
        tree.insertAsAVL(12);
        tree.insertAsAVL(15);
        tree.insertAsAVL(4);
        tree.insertAsAVL(8);
        tree.insertAsAVL(20);
        tree.insertAsAVL(7);
        tree.insertAsAVL(14);
        tree.insertAsAVL(5);
        tree.insertAsAVL(25);
        tree.insertAsAVL(30);
        tree.insertAsAVL(40);
        tree.insertAsAVL(1);

        System.out.println("Done.....");
    }

    private static void set1ForTest(AVLTree tree) {
        tree.insertAsAVL(10);
        tree.insertAsAVL(20);
        tree.insertAsAVL(30);
        tree.insertAsAVL(40);
        tree.insertAsAVL(25);
        tree.insertAsAVL(5);
        tree.insertAsAVL(50);
    }
}

/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.trees;
/*
*
*

This class TreeJunit is created and managed by subhe
Created on 14-04-2026 at 07:54 for the project Udemy Learning

*
*
*/

import com.roadtoglory.ds.new_trees.BinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TreeJunit {

    @Test
    public void simple_tree() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addTree(12);
        binaryTree.add(10, 12, "left");
        binaryTree.add(15, 12, "right");
        binaryTree.add(1, 10, "left");
        binaryTree.add(5, 10, "right");
        binaryTree.add(14, 15, "left");
        binaryTree.add(20, 15, "right");
    }

    @Test
    public void simple_tree_inorderTest() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addTree(10);
        binaryTree.add(20, 10, "left");
        binaryTree.add(30, 10, "right");
        binaryTree.add(40, 20, "left");
        binaryTree.add(50, 20, "right");
        binaryTree.add(60, 30, "left");
        binaryTree.add(70, 30, "right");
        binaryTree.add(80, 70, "left");
        binaryTree.add(90, 70, "right");

        List<Integer> result = binaryTree.inorderTraversal();

        Assert.assertEquals(9, result.size());
        int[] arr = {40, 20, 50, 10, 60, 30, 80, 70, 90};
        System.out.println("The inorder traversal: " + result);
        Assert.assertEquals(result.get(0).intValue(), 40);

        result = binaryTree.preorderTraversal();
        System.out.println("The preorder traversal: " + result);
        Assert.assertEquals(result.get(0).intValue(), 10);

    }
}

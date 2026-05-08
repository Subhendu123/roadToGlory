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

import java.util.ArrayList;
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
        System.out.println("The level order traversal: " + binaryTree.levelOrderTraversal());

        System.out.println("The Size of the tree is " + binaryTree.size());
        System.out.println("The Max Element of the tree is " + binaryTree.max());
        System.out.println("Children Sum property " + binaryTree.isChildrenSum());
        System.out.println("Spiral Representation " + binaryTree.spiralTraversal());


    }

    @Test
    public void lca_test() {
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

        Assert.assertSame(10, binaryTree.LCA(50, 60));
        Assert.assertSame(10, binaryTree.LCA(20, 60));
        Assert.assertSame(10, binaryTree.LCA(20, 30));
        Assert.assertSame(10, binaryTree.LCA(50, 70));
        Assert.assertSame(30, binaryTree.LCA(60, 80));
        Assert.assertSame(20, binaryTree.LCA(40, 50));
    }

    @Test
    public void simple_tree_childrensum() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addTree(20);
        binaryTree.add(20, 8, "left");
        binaryTree.add(30, 12, "right");
        binaryTree.add(3, 8, "left");
        binaryTree.add(5, 8, "right");
        System.out.println("Children Sum property " + binaryTree.isChildrenSum());
    }

    @Test
    public void tree_from_order() {
        List<Integer> preorder = new ArrayList<>();
        preorder.add(10);
        preorder.add(20);
        preorder.add(30);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(20);
        inorder.add(10);
        inorder.add(30);
        BinaryTree binaryTree = new BinaryTree().generateBT(preorder, inorder);
        System.out.println("Pre Order: " + binaryTree.preorderTraversal());
        System.out.println("In Order: " + binaryTree.inorderTraversal());

    }

    @Test
    public void tree_from_orders_med() {

        List<Integer> preorder = new ArrayList<>();
        preorder.add(10);
        preorder.add(20);
        preorder.add(30);
        preorder.add(40);
        preorder.add(50);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(20);
        inorder.add(10);
        inorder.add(40);
        inorder.add(30);
        inorder.add(50);

        BinaryTree binaryTree = new BinaryTree().generateBT(preorder, inorder);
        System.out.println("Pre Order: " + binaryTree.preorderTraversal());
        System.out.println("In Order: " + binaryTree.inorderTraversal());

    }

    @Test
    public void serializtionTest01() {

        List<Integer> preorder = new ArrayList<>();
        preorder.add(10);
        preorder.add(20);
        preorder.add(40);
        preorder.add(-1);
        preorder.add(-1);
        preorder.add(35);
        preorder.add(-1);
        preorder.add(-1);
        preorder.add(30);
        preorder.add(50);
        preorder.add(-1);
        preorder.add(-1);
        preorder.add(-1);

        List<Integer> preOrderRegen = new BinaryTree().serializationTest(preorder);

        Assert.assertEquals(preOrderRegen, preorder);
        Assert.assertEquals(preOrderRegen.size(), preorder.size());
    }

    @Test
    public void serializtionTest02() {

        List<Integer> preorder = new ArrayList<>();
        preorder.add(10);
        preorder.add(20);
        preorder.add(-1);
        preorder.add(-1);
        preorder.add(-1);

        List<Integer> preOrderRegen = new BinaryTree().serializationTest(preorder);

        System.out.println("Serialized: " + preOrderRegen);
        Assert.assertEquals(preOrderRegen, preorder);
        Assert.assertEquals(preOrderRegen.size(), preorder.size());
    }


}

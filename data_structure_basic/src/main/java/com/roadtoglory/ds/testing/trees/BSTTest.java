/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.trees;

import com.roadtoglory.ds.bst.BinarySearchTree;

import java.util.List;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class BSTTest {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree(50);
        binarySearchTree.insert(40);
        binarySearchTree.insert(30);
        binarySearchTree.insert(45);
        binarySearchTree.insert(80);
        binarySearchTree.insert(90);
        binarySearchTree.insert(65);
        //        binaryTree.insert(55);
        binarySearchTree.printTree();

        System.out.println("BFS Traversal Result:");
        binarySearchTree.bfsTraversal();
        System.out.println();
        System.out.println("DFS pre-order traversal Result:");
        binarySearchTree.preOrderDFS();
        System.out.println("\n DFS post-order traversal Result:");
        List<Integer> result = binarySearchTree.postOrderDFS();
        BinarySearchTree.printTraversedBST(result);
        System.out.println("\n DFS in-order traversal Result:");
        result = binarySearchTree.inOrderDFS();
        BinarySearchTree.printTraversedBST(result);
    }
}

package com.roadtoglory.ds.testing;

import com.roadtoglory.ds.bst.BinaryTree;

import java.util.List;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class BSTTest
{
    public static void main (String[] args)
    {
        BinaryTree binaryTree = new BinaryTree(50);
        binaryTree.insert(40);
        binaryTree.insert(30);
        binaryTree.insert(45);
        binaryTree.insert(80);
        binaryTree.insert(90);
        binaryTree.insert(65);
        //        binaryTree.insert(55);
        binaryTree.printTree();

        System.out.println("BFS Traversal Result:");
        binaryTree.bfsTraversal();
        System.out.println();
        System.out.println("DFS pre-order traversal Result:");
        binaryTree.preOrderDFS();
        System.out.println("\n DFS post-order traversal Result:");
        List<Integer> result = binaryTree.postOrderDFS();
        BinaryTree.printTraversedBST(result);
        System.out.println("\n DFS in-order traversal Result:");
        result = binaryTree.inOrderDFS();
        BinaryTree.printTraversedBST(result);
    }
}

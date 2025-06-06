package com.roadtoglory.ds.trees;

public class BinaryTreeInvocation {

    public static void main(String[] args) {
        BinaryTreeImpl binaryTree = new BinaryTreeImpl(10);
        binaryTree.add(25, "left");
        binaryTree.add(54, "right"); // actually right
        binaryTree.add(12, "left");
        binaryTree.add(21, "left");
        binaryTree.add(32, "right");
        binaryTree.add(42, "right");
        binaryTree.add(33, "left");
        binaryTree.add(55, "right");
        binaryTree.add(65, "right");
        System.out.println("The In Order Traversal using the Recursion....");
        binaryTree.orderedTraversal("preorder");
        System.out.println("\nThe In Order Traversal using Iterative method...");
        binaryTree.iterTraversal("preorder");
        System.out.println();
        System.out.println("Printing the tree with level order..");
        binaryTree.print();
        System.out.println();
    }
}

package com.roadtoglory.ds.testing;

import com.roadtoglory.ds.trees.BinarySearchTreeImpl;

public class BSTInvocation {

    private static final boolean recursionReqd = true;
    static BinarySearchTreeImpl binarySearchTree = null;

    public static void main(String[] args) {

        add(50);

        add(30);
        add(70);
        add(20);
        add(40);
        add(60);
        add(80);
        add(65);
        add(55);
        add(10);
        add(25);
        add(75);
        add(90);
        add(35);
        add(45);
        System.out.println("printing karo inorder");
        binarySearchTree.traverse("inorder");

    }

    private static void add(int val) {
        if (binarySearchTree == null) {
            binarySearchTree = new BinarySearchTreeImpl(val);
            return;
        }

        binarySearchTree.insert(val, recursionReqd);
    }
}

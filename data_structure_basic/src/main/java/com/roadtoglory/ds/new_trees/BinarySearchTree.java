/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.new_trees;
/*
*
*

This class BinarySearchTree is created and managed by subhe
Created on 08-05-2026 at 06:39 for the project Udemy Learning Videos

*
*
*/

public class BinarySearchTree {

    private Node root;

    protected BinarySearchTree() {

    }

    public BinarySearchTree(int rootValue) {
        if (this.root == null) {
            this.root = new Node(rootValue);
        }
    }

    public void insert(int value) {
        new BSTUtils().insert(value, this.root);
    }

    public boolean search(int value) {
        return new BSTUtils().searchAndDelete(value, this.root, false);
    }

    public boolean delete(int value) {
        return new BSTUtils().searchAndDelete(value, this.root, true);
    }

    public int floor(int input) {
        return new BSTUtils().floor(input, this.root);
    }


    protected static class Node {
        protected int data;
        protected Node left;
        protected Node right;

        Node(int value) {
            this.data = value;
        }

    }
}

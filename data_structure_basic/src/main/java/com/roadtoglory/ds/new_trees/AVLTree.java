/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.new_trees;
/*
*
*

This class AVLTree is created and managed by subhe
Created on 11-05-2026 at 19:25 for the project Udemy But Personal

*
*
*/

import java.util.Stack;

public class AVLTree {

    private Node root;
    private Node ancestor;
    private Stack<Node> roots;
    private Node parentNode;


    public AVLTree(int data) {
        this.root = new Node(data);
    }

    public void add(int value) {
        this.root = add(value, this.root);
    }

    private Node add(int value, Node root) {
        if (root == null) {
            return new Node(value);
        }

        if (value < root.data) {
            root.left = add(value, root.left);
        }
        else if (value > root.data) {
            root.right = add(value, root.right);
        }
        else {
            throw new IllegalArgumentException(
                    "The value " + value + " is already present in the BST. Please avoid duplicate insertion");
        }

        calcAndStoreBF(root);

        // LL Rotation (Left +2 -> Left +1 Node)
        if (root.balanceFactor < -1 && value > root.right.data) {
            Node tempRoot = root.right;
            tempRoot.left = root;
            root.right = null;
            return tempRoot;
        }
        // RR Rotation (Left -2 -> Left -1 Node)
        else if (root.balanceFactor > 1 && value < root.left.data) {
            Node tempRoot = root.left;
            tempRoot.right = root;
            root.left = null;
            return tempRoot;
        }
        // LR Rotation (Left +2 -> Left -1 Node)
        else if (root.balanceFactor > 1 && value > root.left.data) {

            Node t1 = root.left;
            Node t2 = t1.right;


            // Left rotation - construct t1
            Node tempRoot = t2;
            t1.right = t2.left;
            if (t2.right != null) {
                t1.right = t2.right;
            }

            // Construction of left modified sub tree complete. Now assigning.
            tempRoot.left = t1;

            //right rotation and construction of the root ( sub tree)
            tempRoot.right = root;
            root.left = null;

            return tempRoot;
        }
        else if (root.balanceFactor < -1 && value < root.right.data) {
            Node t1 = root.right;
            Node t2 = t1.left;
            Node tempRoot = t2;
            tempRoot.left = root;
            tempRoot.right = t1;
            t1.left = null;
            root.right = null;
            return tempRoot;
        }

        return root;
    }

    private void calcAndStoreBF(Node root) {
        root.balanceFactor = Math.subtractExact(height(root.left), height(root.right));
    }

    private int height(Node node) {

        if (node == null) {
            return 0;
        }
        return Math.max(height(node.right), height(node.left)) + 1;
    }

    public static class Node {
        Node left;
        Node right;
        int balanceFactor;
        int data;

        Node(int value) {
            this.data = value;
        }
    }
}

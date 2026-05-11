/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.new_trees;
/*
*
*

This class BSTUtils is created and managed by subhe
Created on 08-05-2026 at 07:03 for the project Udemy BST 

*
*
*/

public class BSTUtils {
    private BinarySearchTree.Node parentNode;
    private int floorVal;

    void insert(int value, BinarySearchTree.Node root) {

        if (value < root.data) {
            if (root.left == null) {
                root.left = new BinarySearchTree.Node(value);
            }
            else {
                insert(value, root.left);
            }
        }
        else if (value > root.data) {
            if (root.right == null) {
                root.right = new BinarySearchTree.Node(value);
            }
            else {
                insert(value, root.right);
            }
        }
        else {
            throw new IllegalArgumentException(
                    "The value " + value + " is already present in the BST. Please avoid duplicate insertion");
        }
    }

    boolean searchAndDelete(int value, BinarySearchTree.Node root, boolean delete) {
        if (root == null) {
            return false;
        }

        if (value == root.data) {
            if (delete) {
                if (parentNode != null && parentNode.left.data == root.data) {
                    System.out.println("Deleting left reference of the " + parentNode.data);
                    parentNode.left = null;
                }
                else if (parentNode != null && parentNode.right.data == root.data) {
                    System.out.println("Deleting the right reference of the " + parentNode.data);
                    parentNode.right = null;
                }
            }
            return true;
        }
        else if (value < root.data) {
            parentNode = root;
            return searchAndDelete(value, root.left, delete);
        }
        else {
            parentNode = root;
            return searchAndDelete(value, root.right, delete);
        }
    }

    int floor(int input, BinarySearchTree.Node root) {
        if (root == null) {
            if (floorVal == 0) {
                return -1;
            }
            return floorVal;
        }

        if (root.data == input) {
            return root.data;
        }
        else if (input < root.data) {
            return floor(input, root.left);
        }
        else {
            if (root.data > floorVal) {
                floorVal = root.data;
            }
            return floor(input, root.right);
        }
    }
}

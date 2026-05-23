/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.new_trees;
/*
*
*

This class RedBlackTree is created and managed by subhe
Created on 19-05-2026 at 07:28 for the project Personal Red Black Tree Study

*
*
*/

public class RedBlackTree {
    private static boolean amIChanged = false;
    private Node root;

    public RedBlackTree(int data) {
        this.root = new Node(data);
    }

    private static Node getUncle(Node node, Node grandParent) {
        Node uncle = null;
        if (grandParent.left != null && grandParent.left.data == node.data) {
            uncle = grandParent.right;
        }
        else if (grandParent.right != null && grandParent.right.data == node.data) {
            uncle = grandParent.left;
//            isParentRightChild = true;
        }
        return uncle;
    }

    public void add(int data) {
        add(this.root, null, data);
    }

    private Node add(Node node, Node grandParent, int data) {
        Node uncle = null;
        boolean isRightChild = false;

        if (grandParent == null) {
            grandParent = node;
        }
        Node newlyAddedNode = null;
        if (node == null) {
            Node newNode = new Node(data);
            newNode.isRed = true;
            return newNode;
        }
        else if (data < node.data) {
            node.left = add(node.left, node, data);
            if (amIChanged) {
                node = node.left;
                amIChanged = false;
            }
            newlyAddedNode = node.left;
            uncle = getUncle(node, grandParent);
        }
        else if (data > node.data) {
            node.right = add(node.right, node, data);
            if (amIChanged) {
                node = node.right;
                amIChanged = false;
            }
            newlyAddedNode = node.right;
            uncle = getUncle(node, grandParent);
            isRightChild = true;
        }
        else {
            throw new IllegalArgumentException("Nodes are to be distinct");
        }
        // check red-red
        if (node.isRed && newlyAddedNode.isRed) {
            // Needs attention
            if (uncle != null && uncle.isRed) {
                uncle.isRed = false;
                node.isRed = false;
                if (this.root.data != grandParent.data) {
                    grandParent.isRed = true;
                }
                //return;
            }
            else {
                boolean isParentRightChild = isCurrNodeRightChild(node, grandParent);

                // Rotation is needed
                // LR Rotation
                if (!isParentRightChild && isRightChild) {
                    newlyAddedNode.left = node;
                    node.right = newlyAddedNode.right;
                    newlyAddedNode.isRed = false;

                    newlyAddedNode.right = new Node(grandParent.data);
                    if (grandParent.right != null) {
                        newlyAddedNode.right.right = grandParent.right;
                    }
                    newlyAddedNode.right.isRed = true;

                    // most important
//                    grandParent = newlyAddedNode;
                    amIChanged = true;
                    return newlyAddedNode;
                }

                // RL Rotation
                else if (isParentRightChild && !isRightChild) {
                    newlyAddedNode.left = new Node(grandParent.data);
                    newlyAddedNode.left.isRed = true;
                    newlyAddedNode.isRed = false;
                    newlyAddedNode.right = node;
                    node.left = null;
                    if (grandParent.left != null) {
                        newlyAddedNode.left.left = grandParent.left;
                    }
                    amIChanged = true;
                    return newlyAddedNode;
                }
                // RR Rotation - both are left child
                else if (!isParentRightChild && !isRightChild) {
                    Node r8Node = node.right;
                    node.right = new Node(grandParent.data);
                    node.isRed = false;
                    node.right.isRed = true;
                    node.right.right = grandParent.right;
                    node.right.left = r8Node;
                    amIChanged = true;
                    if (grandParent.data == this.root.data) {
                        this.root = node;
                    }
                }
                // LL Rotation - Both are Right Child
                else if (isParentRightChild && isRightChild) {
                    Node leftNode = node.left;
                    node.left = new Node(grandParent.data);
                    node.left.isRed = true;
                    node.isRed = false;
                    node.left.right = leftNode;
                    node.left.left = grandParent.left;
                    amIChanged = true;
                    if (grandParent.data == this.root.data) {
                        this.root = node;
                    }
                }


            }

        }
        return node;


    }

    private boolean isCurrNodeRightChild(Node node, Node grandParent) {
        return grandParent != null && grandParent.right != null && grandParent.right.data == node.data;
    }


    public class Node {
        int data;
        Node right;
        Node left;
        boolean isRed;

        Node(int data) {
            this.data = data;
        }
    }
}

/*
 * Copyright (c) 2025.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.trees;
/*
*
*

This class AVLTree is created and managed by subhe
Created on 02-07-2025 at 20:35 for the project Data Structure

*
*
*/

public class AVLTree {

    private Node root;


    public void insertAsAVL(int nodeVal) {

        if (this.root == null) {
            this.root = new Node(nodeVal);
            return;
        }

        Node tr = this.root;
        Node prev = tr;
        boolean isAdded = false;

        while (tr != null) {
            if (nodeVal < tr.data) {
                if (tr.left == null) {
                    tr.left = new Node(nodeVal);
                    isAdded = true;
                    break;
                }
                prev = tr;
                tr = tr.left;
            }
            else if (nodeVal > tr.data) {
                if (tr.right == null) {
                    isAdded = true;
                    tr.right = new Node(nodeVal);
                    break;
                }
                prev = tr;
                tr = tr.right;

            }
            else {
                throw new IllegalArgumentException(
                        "A node with the same value (" + nodeVal + ") already exists in the tree.");
            }
        }

        if (isAdded) {
            // increase the height by 1 as a new element is added
            tr.height = calculateNodeHeight(tr);
            if (tr != prev) {
                prev.height = calculateNodeHeight(prev);
            }

            if (calculateBF(prev) < -1 && calculateBF(prev.right) < 0) {
                // execute RR Rotation
                if (this.root == prev) {
                    this.root = tr;
                }
                tr.left = prev;
                prev.right = null;

                prev.height = calculateNodeHeight(prev);
                tr.height = calculateNodeHeight(tr);

            }
            else if (calculateBF(prev) > 1 && calculateBF(prev.left) > 0) {
                // execute LL Rotation
                if (this.root == prev) {
                    this.root = tr;
                }
                tr.right = prev;
                prev.left = null;

                prev.height = calculateNodeHeight(prev);
                tr.height = calculateNodeHeight(tr);

            }
            else if (calculateBF(prev) > 1 && calculateBF(prev.left) < 0) {
                // execute RL Rotation
                Node newLeafNode = tr;
                Node newlyAdded = tr.right;

                if (this.root == prev) {
                    this.root = newlyAdded;
                }
                newlyAdded.left = newLeafNode;
                newLeafNode.right = null;

                newlyAdded.right = prev;
                prev.left = null;

                prev.height = calculateNodeHeight(prev);
                newLeafNode.height = calculateNodeHeight(newLeafNode);
                newlyAdded.height = calculateNodeHeight(newlyAdded);
            }
            else if (calculateBF(prev) < -1 && calculateBF(prev.right) > 0) {
                // execute LR Rotation
                Node newLeafNode = tr;
                Node newlyAdded = tr.left;

                if (this.root == prev) {
                    this.root = newlyAdded;
                }
                newlyAdded.right = newLeafNode;
                newLeafNode.left = null;

                newlyAdded.left = prev;
                prev.right = null;

                prev.height = calculateNodeHeight(prev);
                newLeafNode.height = calculateNodeHeight(newLeafNode);
                newlyAdded.height = calculateNodeHeight(newlyAdded);
            }
            else {
                System.out.println("In the else part");
//                this.root.height = calculateNodeHeight(this.root);
            }
        }


    }

    private int calculateBF(Node tr) {
        int leftH = tr.left != null ? tr.left.height + 1 : 0;
        int rightH = tr.right != null ? tr.right.height + 1 : 0;
        int balanceFactor = leftH - rightH;
        return balanceFactor;
    }

    private int calculateNodeHeight(Node tr) {
        int leftH = tr.left != null ? tr.left.height + 1 : 0;
        int rightH = tr.right != null ? tr.right.height + 1 : 0;
        return leftH > rightH ? leftH : rightH;

    }

    class Node implements Comparable {
        int data;
        Node left;
        Node right;
        int height;

        Node(int data) {
            this.data = data;
        }

        /**
         * @param o the object to be compared.
         * @return
         */
        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
}

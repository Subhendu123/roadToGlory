/*
 * Copyright (c) 2025.
 * This is created and managed by Subhendu
 */

package com.roadtoglory.ds.trees;
/*
*
*

This class BinarySearchTreeImpl is created and managed by Subhendu
Created on 08-06-2025 at 21:07 for the project data structure 2025

*
*
*/

public class BinarySearchTreeImpl {

    protected Node root;
    StringBuilder output;

    protected BinarySearchTreeImpl() {
        System.out.println("Default Constructor for the Binary Search Tree");
    }

    public BinarySearchTreeImpl(int val) {
        if (this.root == null) {
            this.root = new Node(val);
        }
        else {
            throw new IllegalCallerException("The Tree is already created.");
        }
    }

    public void insert(int val, boolean withRecursion) {

        if (withRecursion) {
            insert(val, this.root);
            return;
        }
        Node tr = this.root;

        while (tr != null) {
            if (val < tr.data) {
                if (tr.left == null) {
                    tr.left = new Node(val);
                    break;
                }
                tr = tr.left;
            }
            else if (val > tr.data) {
                if (tr.right == null) {
                    tr.right = new Node(val);
                    break;
                }
                tr = tr.right;
            }
            else {
                throw new IllegalArgumentException(
                        "A node with the same value (" + val + ") already exists in the tree.");
            }
        }
    }

    private void insert(int val, Node root) {

        if (val < root.data) {
            if (root.left == null) {
                root.left = new Node(val);
            }
            else {
                insert(val, root.left);
            }
        }
        else if (val > root.data) {
            if (root.right == null) {
                root.right = new Node(val);
            }
            else {
                insert(val, root.right);
            }
        }
        else {
            throw new IllegalArgumentException("A node with the same value (" + val + ") already exists in the tree.");
        }
    }

    public void traverse(String order) {
        if (order.equalsIgnoreCase("inorder")) {
            String output = inorderTraversal(this.root, true);
            System.out.println("The inorder traversal value is " + output);
        }
    }

    private String inorderTraversal(Node root, boolean triggeredByMainTh) {
        if (triggeredByMainTh) {
            output = new StringBuilder();
        }
        if (root != null) {
            inorderTraversal(root.left, false);
            if (output == null) {
                output = new StringBuilder();
                output.append(root.data);
                output.append(" -> ");
            }
            else {
                output.append(" -> ");
                output.append(root.data);
            }
            inorderTraversal(root.right, false);
        }
        return output != null ? output.toString() : null;

    }

    public void delete(int val) {
        Node tr = this.root;
        while (tr != null) {
            if (val == tr.data) {

            }
            else if (val > tr.data) {
                Node nextRight = tr.right;
                if (nextRight != null) {
                    if (nextRight.data == val) {
                        // element found
                        if (nextRight.right == null && nextRight.left == null) {
                            tr.right = null;
                            System.out.println("The node with the value " + val + " has been removed from the tree.");
//                            return true;
                        }
                        else if (nextRight.left == null) {
                            tr.right = nextRight.right;

                        }
                        else if (nextRight.right == null) {
                            tr.right = nextRight.left;

                        }
                        else {
                            // all the children are present
                            System.out.println("all the children are present");
                            Node newLeft = nextRight.left;
                            if (newLeft.left != null && newLeft.right != null) {
                                // new node's children are present
                                tr.right = newLeft;
                                Node right = newLeft.right;

                            }
                            else if (newLeft.left != null) {

                            }
                            else if (newLeft.right != null) {

                            }
                        }

                    }
                }

                else {
                    tr = tr.right;
                }
            }
            else {
                throw new IllegalArgumentException("No node is present with a value " + val + " in the binary tree.");
            }
        }
    }


    class Node implements Comparable {
        int data;
        Node left;
        Node right;

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


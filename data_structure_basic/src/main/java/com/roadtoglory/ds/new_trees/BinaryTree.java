/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.new_trees;
/*
*
*

This class BinaryTree is created and managed by subhe
Created on 14-04-2026 at 07:30 for the project Udemy Tree Study

*
*
*/

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryTree {
    private Node root;

    private void initializeBinaryTree(int data) {
        if (root == null) {
            root = new Node(data);
        }
        else {
            throw new NoSuchElementException("The Root is initialized already");
        }
    }

    public List<Integer> preorderTraversal() {
        List<Integer> result = new ArrayList<>();
        preorderTraversal(result, this.root);
        return result;
    }

    private void inorderTraversal(List<Integer> result, Node node) {
        if (node != null) {
            inorderTraversal(result, node.left);
            result.add(node.data);
            inorderTraversal(result, node.right);
        }
    }

    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(result, this.root);
        return result;
    }

    private void preorderTraversal(List<Integer> result, Node node) {
        if (node != null) {
            result.add(node.data);
            preorderTraversal(result, node.left);
            preorderTraversal(result, node.right);
        }
    }

    public void addTree(int data) {
        if (this.root == null) {
            initializeBinaryTree(data);
        }
    }

    public void add(int data, int rootNode, String nodeType) {
        Node tr = this.root;
        if (tr != null) {
            if (tr.data == rootNode) {
                assignNode(tr, data, nodeType);
                return;
            }
            boolean isAdded = searchAndAdd(tr.left, rootNode, data, nodeType);
            if (!isAdded) {
                searchAndAdd(tr.right, rootNode, data, nodeType);
            }
        }
    }

    private int height(Node node) {

        if (node == null) {
            return 0;
        }
        return Math.max(height(node.right), height(node.left)) + 1;
    }

    public void printNodes(int k) {
        printNodes(k, this.root);
    }

    public void printNodes(int k, Node node) {
        if (node == null) {
            return;
        }
        if (k == 0) {
            System.out.println(node.data + " ");
        }
        else {
            printNodes(k - 1, node.left);
            printNodes(k - 1, node.right);

        }
    }

    private boolean searchAndAdd(Node node, int rootNodeData, int data, String nodeType) {
        if (node != null) {
            if (node.data == rootNodeData) {
                assignNode(node, data, nodeType);
                return true;
            }
            searchAndAdd(node.left, rootNodeData, data, nodeType);
            searchAndAdd(node.right, rootNodeData, data, nodeType);
        }

        return false;
    }

    private void assignNode(Node node, int data, String nodeType) {
        if (nodeType.equalsIgnoreCase("left")) {
            System.out.println("Adding to the left");
            node.left = new Node(data);
        }
        else {
            System.out.println("Adding to the right");
            node.right = new Node(data);
        }
    }

    private class Node {
        int data;
        Node right;
        Node left;

        public Node(int data) {
            this.data = data;
        }
    }
}

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

import java.util.*;

import static com.roadtoglory.ds.new_trees.BinaryTreeUtils.searchAndAdd;

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
                BinaryTreeUtils.assignNode(tr, data, nodeType);
                return;
            }
            boolean isAdded = searchAndAdd(tr.left, rootNode, data, nodeType);
            if (!isAdded) {
                searchAndAdd(tr.right, rootNode, data, nodeType);
            }
        }
    }

    public List<Integer> serializationTest(List<Integer> preOrder) {
        Node node = deserialize(preOrder);
        List<Integer> preOrderAL = new ArrayList<>();
        serialize(node, preOrderAL);
        return preOrderAL;
    }

    private void serialize(Node node, List<Integer> preOrderAL) {
        if (node == null) {
            preOrderAL.add(-1);
            return;
        }
        preOrderAL.add(node.data);
        serialize(node.left, preOrderAL);
        serialize(node.right, preOrderAL);
    }

    private Node deserialize(List<Integer> preOrderArray) {
        Stack<Node> nStack = new Stack<>();
        Node traversal = null;
        if (root == null) {
            addTree(preOrderArray.get(0));
            nStack.push(root);
            traversal = root;

        }
        for (int i = 1; i < preOrderArray.size(); i++) {
            if (preOrderArray.get(i) != -1) {
                if (traversal.left == null) {
                    add(preOrderArray.get(i), traversal.data, "left");
                    traversal = traversal.left;
                    nStack.push(traversal);
                }
                else {
                    add(preOrderArray.get(i), traversal.data, "right");
                    traversal = traversal.right;
                    nStack.push(traversal);
                }
            }
            else {
                if (preOrderArray.get(i - 1) == -1 && !nStack.isEmpty()) {
                    nStack.pop();
                    traversal = nStack.pop();
                }
            }
        }
        return root;
    }

    public BinaryTree generateBT(List<Integer> preorder, List<Integer> inorder) {
        BinaryTree binaryTree = new BinaryTreeUtils().genBTWithTraversal(null, preorder, inorder, false);
        return binaryTree;
    }

    public List<Integer> spiralTraversal() {
        return new BinaryTreeUtils().spiralFormPrint(this.root);
    }

    private int height(Node node) {

        if (node == null) {
            return 0;
        }
        return Math.max(height(node.right), height(node.left)) + 1;
    }

    public List<Integer> levelOrderTraversal() {
        Map<Integer, List<Integer>> levelOrderMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int level = 1;
        new BinaryTreeUtils().levelOrderTraversal(this.root, levelOrderMap, level);
        for (Map.Entry<Integer, List<Integer>> entry : levelOrderMap.entrySet()) {
            result.addAll(entry.getValue());
        }
        return result;
    }

    public Integer LCA(Integer node1, Integer node2) {
        return new BinaryTreeUtils().LCA(this.root, node1, node2);
    }

    public void printNodes(int k) {
        BinaryTreeUtils.printNodes(k, this.root);
    }

    public int size() {
        return new BinaryTreeUtils().size(this.root, 0);
    }

    public int max() {
        return new BinaryTreeUtils().max(this.root, 0);
    }

    public boolean isChildrenSum() {
        return new BinaryTreeUtils().isChildrenSum(this.root);
    }

    protected class Node {
        int data;
        Node right;
        Node left;

        public Node(int data) {
            this.data = data;
        }
    }
}

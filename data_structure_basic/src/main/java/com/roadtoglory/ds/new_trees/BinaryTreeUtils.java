/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.new_trees;
/*
*
*

This class BinaryTreeUtils is created and managed by subhe
Created on 15-04-2026 at 18:31 for the project Udemy Tree DS Learning

*
*
*/

import com.roadtoglory.ds.linkedlists.DoublyLinkedListImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeUtils {

    private int lastRoot = -1;
    private Map<Integer, List<Integer>> nodeTracker = new HashMap<>();
    private Map<Integer, Boolean> nodeSearchedMap = new HashMap<>();

    protected static boolean searchAndAdd(BinaryTree.Node node, int rootNodeData, int data, String nodeType) {
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

    protected static void assignNode(BinaryTree.Node node, int data, String nodeType) {
        if (nodeType.equalsIgnoreCase("left")) {
            System.out.println("Adding to the left");
            node.left = new BinaryTree().new Node(data);
        }
        else {
            System.out.println("Adding to the right");
            node.right = new BinaryTree().new Node(data);
        }
    }

    protected static void printNodes(int k, BinaryTree.Node node) {
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


    protected void levelOrderTraversal(BinaryTree.Node root, Map<Integer, List<Integer>> levelOrderMap, int level) {

        if (levelOrderMap.containsKey(level)) {
            levelOrderMap.get(level).add(root.data);
        }
        else {
            List<Integer> nodes = new ArrayList<>();
            nodes.add(root.data);
            levelOrderMap.put(level, nodes);
        }
        if (root.left != null) {
            levelOrderTraversal(root.left, levelOrderMap, level + 1);
        }
        if (root.right != null) {
            levelOrderTraversal(root.right, levelOrderMap, level + 1);
        }

    }

    protected int size(BinaryTree.Node node, int size) {

        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return size + 1;
        }

        return 1 + size(node.left, size) + size(node.right, size);
    }

    protected int max(BinaryTree.Node node, int max) {
        if (node == null) {
            return max;
        }
        if (node.data > max) {
            max = node.data;
        }

        return Math.max(max(node.left, max), max(node.right, max));
    }

    protected boolean isChildrenSum(BinaryTree.Node node) {
        if (node == null) {
            return true;
        }
        if (node.right == node.left && node.left == null) {
            return true;
        }
        int sum = node.left != null ? node.left.data : 0;
        sum = sum + (node.right != null ? node.right.data : 0);
        System.out.println("Sum: " + sum);
        if (node.data != sum) {
            return false;
        }

        return isChildrenSum(node.left) && isChildrenSum(node.right);
    }

    protected int calcBalanced(BinaryTree.Node root) {
        if (root == null) {
            return 0;
        }
        int heightLeft = calcBalanced(root.left);
        if (heightLeft < 0) return -1;

        int heightRight = calcBalanced(root.right);
        if (heightRight < 0) return -1;

        int diff = Math.abs(heightRight - heightLeft);
        if (diff > 1) {
            return -1;
        }
        return diff;

    }

    protected DoublyLinkedListImpl convertToDLL(BinaryTree.Node node) {
        List<Integer> inorderTr = new BinaryTree().inorderTraversal();
        DoublyLinkedListImpl doublyLinkedList = new DoublyLinkedListImpl(inorderTr.get(0));
        for (int i = 1; i < inorderTr.size(); i++) {
            doublyLinkedList.append(inorderTr.get(i));
        }
        return doublyLinkedList;
    }

    protected BinaryTree genBTWithTraversal(BinaryTree binaryTree, List<Integer> preOrder, List<Integer> inOrder,
                                            boolean isLeftSubtree) {

        int currentRootNode = preOrder.remove(0);

        if (binaryTree == null) {
            binaryTree = new BinaryTree();
            binaryTree.addTree(currentRootNode);
        }
        else {
            binaryTree.add(currentRootNode, lastRoot, isLeftSubtree ? "left" : "right");
        }


        int index = inOrder.indexOf(currentRootNode);
        List<Integer> leftSubtree = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            leftSubtree.add(inOrder.get(i));
        }
        List<Integer> rightSubtree = new ArrayList<>();
        for (int i = index + 1; i < inOrder.size(); i++) {
            rightSubtree.add(inOrder.get(i));
        }

        if (!leftSubtree.isEmpty()) {
            lastRoot = currentRootNode;
            genBTWithTraversal(binaryTree, preOrder, leftSubtree, true);
        }
        if (!rightSubtree.isEmpty()) {
            lastRoot = currentRootNode;
            genBTWithTraversal(binaryTree, preOrder, rightSubtree, false);
        }
        return binaryTree;

    }

    protected List<Integer> spiralFormPrint(BinaryTree.Node root) {
        Map<Integer, List<Integer>> levelOrderMap = new HashMap<>();
        int level = 0;
        levelOrderTraversal(root, levelOrderMap, level);
        List<Integer> result = new ArrayList<>();
        boolean isLeft = true;
        for (Map.Entry entry : levelOrderMap.entrySet()) {
            List<Integer> nodes = (List<Integer>) entry.getValue();
            if (isLeft) {
                result.addAll(nodes);
            }
            else {
                for (int i = nodes.size() - 1; i > -1; i--) {
                    result.add(nodes.get(i));
                }
            }
            isLeft = !isLeft;
        }
        return result;
    }

    protected Integer LCA(BinaryTree.Node root, Integer node1, Integer node2) {

        Integer resultLeft = LCA(root.left, root, node1, node2);

        boolean node1Found = false;
        boolean node2Found = false;
        if (resultLeft == 1) {
            if (nodeSearchedMap.size() == 2) {
                Integer lca = getLCAfromTrArray(node1, node2);
                if (lca != null) return lca;
            }
            else {
                node1Found = nodeSearchedMap.containsKey(node1);
                node2Found = nodeSearchedMap.containsKey(node2);
                if (node1Found) {
                    nodeTracker.put(node2, new ArrayList<>());
                }
                if (node2Found) {
                    nodeTracker.put(node1, new ArrayList<>());
                }
            }
        }
        else {
            nodeTracker.clear();
        }

        Integer resultRight = LCA(root.right, root, node1, node2);

        if (resultRight == 1) {
            if (resultLeft == 1) {
                List<Integer> nodesTr1 = nodeTracker.get(node1);
                List<Integer> nodesTr2 = nodeTracker.get(node2);
                int len1 = nodesTr1.size();
                int len2 = nodesTr2.size();
                int lca = 0;
                if (len1 > len2) {
                    lca = nodesTr2.get(0);
                    lca = nodesTr1.contains(lca) ? lca : -1;
                }
                else {
                    lca = nodesTr1.get(0);
                    lca = nodesTr2.contains(lca) ? lca : -1;
                }
                return lca;
            }
            else {
                Integer lca = getLCAfromTrArray(node1, node2);
                if (lca != null) return lca;
            }

        }
        return -1;
    }

    private Integer getLCAfromTrArray(Integer node1, Integer node2) {
        if (nodeSearchedMap.size() == 2) {
            // both are found
            //calculate the common parent via nodeTracker
            List<Integer> nodesTr1 = nodeTracker.get(node1);
            List<Integer> nodesTr2 = nodeTracker.get(node2);
            int len1 = nodesTr1.size();
            int len2 = nodesTr2.size();
            int lca = 0;
            if (len1 > len2) {
                lca = nodesTr2.get(len2 - 1);
                lca = nodesTr1.contains(lca) ? lca : -1;
            }
            else {
                lca = nodesTr1.get(len1 - 1);
                lca = nodesTr2.contains(lca) ? lca : -1;
            }
            return lca;
        }
        return null;
    }

    private Integer LCA(BinaryTree.Node root, BinaryTree.Node parentRoot, Integer node1, Integer node2) {
        if (root == null) {
            return 0;
        }
        if (!nodeSearchedMap.containsKey(node1)) {
            populateNodeTracker(parentRoot, node1);
        }
        if (!nodeSearchedMap.containsKey(node2)) {
            populateNodeTracker(parentRoot, node2);
        }
        if (root.data == node1) {
            nodeSearchedMap.put(node1, true);
            return 1;
        }
        else if (root.data == node2) {
            nodeSearchedMap.put(node2, true);
            return 1;
        }
        else {
            Integer resultLeft = LCA(root.left, root, node1, node2);
            Integer resultRight = LCA(root.right, root, node1, node2);
            if (resultLeft == 1 || resultRight == 1) {
                return 1;
            }
        }
        return 0;
    }


    private void populateNodeTracker(BinaryTree.Node parentRoot, Integer n) {
        if (!nodeTracker.containsKey(n)) {
            List<Integer> list = new ArrayList<>();
            list.add(parentRoot.data);
            nodeTracker.put(n, list);
        }
        else {
            nodeTracker.get(n).add(parentRoot.data);
        }
    }
}

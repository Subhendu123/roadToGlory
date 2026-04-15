/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.greedy_algo;
/*
*
*

This class HuffmanAlgorith is created and managed by subhe
Created on 10-03-2026 at 06:53 for the project Udemy Greedy Algo

*
*
*/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanAlgorithm {
    private Node root;

    public List<String> huffmanCoding(Integer[] values, Character[] character) {
        PriorityQueue<Node> sortedNodesQueue = new PriorityQueue<>((a, b) -> a.value > b.value ? 1 : -1);
        for (int i = 0; i < values.length; i++) {
            sortedNodesQueue.add(new Node(character[i], values[i]));
        }
        System.out.println(sortedNodesQueue);

        while (sortedNodesQueue.size() > 1) {
            Node left = sortedNodesQueue.poll();
            Node right = sortedNodesQueue.poll();
            this.root = new Node('$', left.value + right.value);
            this.root.left = left;
            this.root.right = right;
            sortedNodesQueue.add(this.root);

            System.out.println(
                    "Root : " + this.root.value + " left : [" + left.character + " | " + left.value + "] right : [" + right.character + " | " + right.value + "]");
        }
        printNodes(this.root);

        List<String> codes = printCodes(this.root, null, new String());

        System.out.println(codes);

        return codes;

    }

    private List<String> printCodes(Node root, List<String> currentCodes, String currentCode) {
        if (currentCodes == null) {
            currentCodes = new ArrayList<>();
        }
        if (root == null) {
            return currentCodes;
        }
        if (!root.character.equals('$')) {

            currentCodes.add(root.character + " -> " + currentCode);
        }
        printCodes(root.left, currentCodes, currentCode + "0");
        printCodes(root.right, currentCodes, currentCode + "1");
        return currentCodes;
    }

    private void printNodes(Node root) {
        while (root != null) {
            System.out.println("[" + root.character + " | " + root.value + "]");
            printNodes(root.left);
            printNodes(root.right);
            root = root.left;
        }
    }

    public class Node {
        public Node left, right;
        public Integer value;
        public Character character;

        public Node(Character character, Integer value) {
            this.character = character;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.character + ":" + this.value;
        }
    }

}

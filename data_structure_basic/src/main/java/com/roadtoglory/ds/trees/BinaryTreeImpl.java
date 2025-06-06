package com.roadtoglory.ds.trees;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Stack;

public class BinaryTreeImpl {

    private static final String LEFT = "left";
    private Node root;

    public BinaryTreeImpl(int data) {
        this.root = new Node(data);
    }

    public boolean add(int data, String precedence) {
        if (this.root == null) {
            new BinaryTreeImpl(data);
            return true;
        }
        Node traverser = this.root;
        boolean isAdded = false;
        while (traverser != null) {

            if (traverser.left == null && traverser.right == null) {
                traverser.left = new Node(data);
                isAdded = true;
                break;
            } else if (traverser.left != null && traverser.right == null) {
                traverser.right = new Node(data);
                isAdded = true;
                break;
            } else {
                if (precedence.equals(BinaryTreeImpl.LEFT))
                    traverser = traverser.left;
                else
                    traverser = traverser.right;
            }
        }
        return isAdded;
    }

    public void orderedTraversal(String order) {
        orderedTraversal(this.root, order);
    }

    private void orderedTraversal(Node root, String order) {

        if (root != null && order.equalsIgnoreCase("inorder")) {
            orderedTraversal(root.left, "inorder");
            System.out.print(root.data + " -> ");
            orderedTraversal(root.right, "inorder");
        } else if (root != null && order.equalsIgnoreCase("preorder")) {
            System.out.print(root.data + " - ");
            orderedTraversal(root.left, "preorder");
            orderedTraversal(root.right, "preorder");
        } else if (root != null && order.equalsIgnoreCase("postorder")) {
            orderedTraversal(root.left, "postorder");
            orderedTraversal(root.right, "postorder");
            System.out.print(root.data + " - ");
        }
    }

    public void iterTraversal(String order) {
        Node t = this.root;
        Stack<Node> nodeStack = new Stack<>();
        int i = 1;
        while (i == 1) {
            if (order.equalsIgnoreCase("inorder")) {
                // In order
                if (t != null) {
                    nodeStack.add(t);
                    t = t.left;
                } else {
                    Node poppedT = nodeStack.pop();
                    System.out.print(poppedT.data + " -> ");
                    t = poppedT.right;
                }
            }
            if (order.equalsIgnoreCase("preorder")) {
                // Pre order
                if (t != null) {
                    System.out.print(t.data + " -> ");
                    nodeStack.add(t);
                    t = t.left;
                } else {
                    Node poppedT = nodeStack.pop();
                    t = poppedT.right;
                }
            }


            if (t == null && nodeStack.isEmpty()) {
                break;
            }
        }
    }

    public void print() {
        printLevelOrder(this.root);

    }

    private void printLevelOrder(Node root) {
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        if (root != null) {
            nodeQueue.add(root);
            System.out.print(root.data);
        } else
            throw new NoSuchElementException("The tree is empty.");

        while (!nodeQueue.isEmpty()) {
            Node next = nodeQueue.remove();
            if (next.left != null) {
                nodeQueue.add(next.left);
                System.out.print(" - " + next.left.data);
            }
            if (next.right != null) {
                nodeQueue.add(next.right);
                System.out.print(" - " + next.right.data);
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

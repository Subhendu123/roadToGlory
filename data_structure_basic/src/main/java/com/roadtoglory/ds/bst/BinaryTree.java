package com.roadtoglory.ds.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class BinaryTree
{
    public Node root;

    public BinaryTree (int value)
    {
        if (this.root == null)
        {
            this.root = new Node(value);
        }
        else
        {
            System.out.println("Please insert data");
        }
    }

    public void insert (int value)
    {
        insert(value, this.root);
    }

    private void insert (int value, Node root)
    {
        if (value > root.value)
        {
            if (root.right == null)
            {
                root.right = new Node(value);
            }
            else
            {
                insert(value, root.right);
            }
        }
        else
        {
            // End of the tree is reached - this is the leaf node
            if (root.left == null)
            {
                root.left = new Node(value);
            }
            else
            {
                insert(value, root.left);
            }
        }
    }

    public void bfsTraversal ()
    {
        bfsTraversal(this.root);
    }

    private void bfsTraversal (Node root)
    {
        List<Integer> results = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node itr = root;
        queue.add(itr);
        while (!queue.isEmpty())
        {
            itr = queue.remove();
            results.add(itr.value);
            if (itr.left != null)
            {
                queue.add(itr.left);
            }
            if (itr.right != null)
            {
                queue.add(itr.right);
            }
            //            Older approach
            //            if (itr.left == null && itr.right == null && queue.isEmpty()) break;
        }
        System.out.println("The BFS Traversed tree is ");
        for (Integer out : results)
        {System.out.print(out + " ");}

    }

    public void preOrderDFS ()
    {
        Node currNode = this.root;
        Queue<Node> queue = new LinkedList<>();
        List<Integer> results = new ArrayList<>();
        queue.add(currNode);
        while (!queue.isEmpty())
        {
            currNode = queue.remove();
            results.add(currNode.value);
            if (currNode.left != null)
            {
                queue.add(currNode.left);
            }

            else if (currNode.right != null)
            {
                queue.add(currNode.right);
            }
        }
    }

    public void printTree ()
    {
        printTree(this.root);

    }

    private void printTree (Node root)
    {
        System.out.println(root.value);
        boolean leftNodePrinted = false;
        if (root.left != null)
        {
            leftNodePrinted = true;
            System.out.println("/");
            printTree(root.left);
        }
        if (root.right != null)
        {
            if (leftNodePrinted)
            {
                System.out.print("\t" + "\\");
            }
            else
            {
                System.out.println("\t" + "\\");
            }
            printTree(root.right);
        }

    }


}

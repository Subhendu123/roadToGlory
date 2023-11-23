package com.roadtoglory.ds.bst;

import java.util.*;


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

    private static void addChildNodesToStack (Node currNode, Stack<Node> stack)
    {
        if (currNode.right != null)
        {
            stack.add(currNode.right);
        }
        if (currNode.left != null)
        {
            stack.add(currNode.left);
        }
    }

    public static void printTraversedBST (List<Integer> results)
    {
        for (Integer out : results)
        {System.out.print(out + " ");}
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
        printTraversedBST(results);

    }

    public void preOrderDFS ()
    {
        Node currNode = this.root;
        Stack<Node> stack = new Stack<>();
        List<Integer> results = new ArrayList<>();
        stack.add(currNode);

        while (!stack.isEmpty())
        {
            currNode = stack.pop();
            results.add(currNode.value);
            addChildNodesToStack(currNode, stack);
        }
        printTraversedBST(results);
    }

    public List<Integer> postOrderDFS ()
    {
        Node currNode = this.root;
        Stack<Node> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        stack.add(currNode);
        while (!stack.isEmpty())
        {
            currNode = stack.lastElement();
            boolean isChildPresent = false;

            if (currNode.right != null)
            {
                if (result.contains(currNode.right.value))
                {
                    isChildPresent = true;
                }
                else
                {
                    stack.add(currNode.right);
                }
            }
            if (currNode.left != null)
            {
                if (result.contains(currNode.left.value))
                {
                    isChildPresent = true;
                }
                else {stack.add(currNode.left);}
            }
            if ((currNode.right == null && currNode.left == null) || isChildPresent)
            {
                // time to print
                currNode = stack.pop();
                result.add(currNode.value);
            }
        }
        return result;
    }

    public List<Integer> inOrderDFS ()
    {
        Node currNode = this.root;
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.add(currNode);
        List<Integer> result = new ArrayList<>();
        while (!nodeStack.isEmpty())
        {
            currNode = nodeStack.lastElement();
            boolean isChildPresent = false;
            if (currNode.left != null)
            {
                if (result.contains(currNode.left.value))
                {
                    isChildPresent = true;
                }
                else
                {
                    nodeStack.add(currNode.left);
                }
            }
            if (currNode.left == null || isChildPresent)
            {
                // root node to be printed as left either is written or does not exist
                currNode = nodeStack.pop();
                result.add(currNode.value);
                if (currNode.right != null)
                {
                    if (!result.contains(currNode.right.value))
                    {
                        nodeStack.add(currNode.right);
                    }
                }
            }


        }
        return result;
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

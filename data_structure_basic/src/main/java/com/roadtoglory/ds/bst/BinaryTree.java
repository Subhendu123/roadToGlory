package com.roadtoglory.ds.bst;

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

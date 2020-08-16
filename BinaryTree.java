package com.company;

import java.util.*;

public class BinaryTree {
    Node root;

    public boolean isBalanced(Node root, Height height) {
        if (root == null) {
            height.height = 0;
            return true;
        }

        Height lheight = new Height(), rheight = new Height();
        boolean left = isBalanced(root.left, lheight);
        boolean right = isBalanced(root.right, rheight);
        int lefth = lheight.height, righth = rheight.height;

        height.height = (lefth > righth ? lefth : righth) + 1;

        if ((lefth - righth >= 2) || (righth - lefth >= 2))
            return false;
        else
            return left && right;

    }

    public boolean isBST()  {
        return isBSTUtil(root, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    public boolean isBSTUtil(Node node, int min, int max)
    {
        if (node == null)
            return true;

        if (node.data < min || node.data > max)
            return false;

        return (isBSTUtil(node.left, min, node.data-1) &&
                isBSTUtil(node.right, node.data+1, max));
    }

    public static Node insert(Node node, int num)
    {
        Random rand = new Random();
        int way = rand.nextInt(2);

        if(node == null)
            node = new Node(num);

        if(way == 0)
        {
            if(node.left != null){
                insert(node.left, num);
            }else{
                node.left = new Node(num);
            }
        } else if (way == 1)
        {
            if(node.right != null){
                insert(node.right, num);
            } else {
                node.right = new Node(num);
            }
        }
        return node;
    }

    public static void main(String args[])
    {
        Height height = new Height();
        BinaryTree tree = new BinaryTree();
        Random rand = new Random();

        int [] arr = new int[rand.nextInt(10)];
        for(int j = 0; j < arr.length; j++)
            arr[j] = rand.nextInt(100);

        tree.root = new Node(arr[0]);
        int i = 1;
        while(i < arr.length)
        {
            insert(tree.root,arr[i]);
            i++;
        }

        if (tree.isBST()) {
            if (tree.isBalanced(tree.root, height))
                System.out.println("Tree is an AVL Tree and is a BST");
            else
                System.out.println("Tree is a BST but is not a AVL tree");
        }else
            System.out.println("Tree is not a BST or an AVL tree");

    }

}

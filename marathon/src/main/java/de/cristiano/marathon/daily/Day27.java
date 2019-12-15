package de.cristiano.marathon.daily;

/**
 * Given pre-order and in-order traversals of a binary tree, write a function to reconstruct the tree.
 */
public class Day27 {

    Tree reconstructTree(String preOrder, String inOrder) {
        Tree root = new Tree(preOrder.charAt(0));
        final int currentNodePosition = inOrder.indexOf(root.value);

        root.left = buildTree(preOrder.substring(1), inOrder.substring(0, currentNodePosition));
        root.right = buildTree(preOrder.substring(currentNodePosition + 1), inOrder.substring(currentNodePosition + 1));

        return root;
    }

    private Tree buildTree(String preOrder, String inOrder) {
        Tree node = new Tree(preOrder.charAt(0));
        final int currentNodePosition = inOrder.indexOf(node.value);

        if (inOrder.length() == 1) {
            return node;
        }

        node.left = buildTree(preOrder.substring(1), inOrder.substring(0, currentNodePosition));
        node.right = buildTree(preOrder.substring(2), inOrder.substring(currentNodePosition + 1));

        return node;
    }


    static class Tree {
        Tree left;

        Tree right;

        Character value;

        public Tree(Character value) {
            this.value = value;
        }
    }
}

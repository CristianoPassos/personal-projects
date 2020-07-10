package de.cristiano.marathon.dcp;

import de.cristiano.marathon.dcp.utils.TreeNode;

/**
 * Given pre-order and in-order traversals of a binary tree, write a function to reconstruct the tree.
 */
public class Day27 {

    TreeNode reconstructTree(String preOrder, String inOrder) {
        TreeNode root = new TreeNode(preOrder.charAt(0), null, null);
        final int currentNodePosition = inOrder.indexOf(root.value);

        root.left = buildTree(preOrder.substring(1), inOrder.substring(0, currentNodePosition));
        root.right = buildTree(preOrder.substring(currentNodePosition + 1), inOrder.substring(currentNodePosition + 1));

        return root;
    }

    private TreeNode buildTree(String preOrder, String inOrder) {
        TreeNode node = new TreeNode(preOrder.charAt(0), null, null);
        final int currentNodePosition = inOrder.indexOf(node.value);

        if (inOrder.length() == 1) {
            return node;
        }

        node.left = buildTree(preOrder.substring(1), inOrder.substring(0, currentNodePosition));
        node.right = buildTree(preOrder.substring(2), inOrder.substring(currentNodePosition + 1));

        return node;
    }

}

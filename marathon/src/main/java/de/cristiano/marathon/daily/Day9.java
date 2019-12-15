package de.cristiano.marathon.daily;

/**
 * A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
 * <p>
 * Given the root to a binary tree, count the number of unival subtrees.
 */
public class Day9 {

    int countUnivalSubtrees(Node root) {
        return evaluteSubtree(root);
    }

    private int evaluteSubtree(Node node) {
        if (node.left == null && node.right == null) {
            return 1;
        }

        int univalSubtress = 0;

        if (node.isLeftNode()) {
            univalSubtress += evaluteSubtree(node.left);
        }

        if (node.isRightNode()) {
            univalSubtress += evaluteSubtree(node.right);
        }

        if (node.value.equalsIgnoreCase(node.left.value) && node.value.equalsIgnoreCase(node.right.value)) {
            univalSubtress++;
        }

        return univalSubtress;
    }


    static class Node {
        Node left;
        Node right;

        String value;

        Node(Node left, Node right, String value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        Node(String value) {
            this.value = value;
        }

        boolean isLeftNode() {
            return left != null;
        }

        boolean isRightNode() {
            return right != null;
        }
    }
}

package de.cristiano.marathon.dcp;

import static java.util.Objects.nonNull;

/**
 * Given the root to a binary search tree, find the second largest node in the tree.
 */
public class Day36 {

    int currentPosition = 0;

    Integer searchFor(Node root, int kth) {
        currentPosition = 0;

        return postOrderSearch(root, kth);
    }

    private Integer postOrderSearch(Node node, int kth) {
        if (node.right != null) {
            final Integer integer = postOrderSearch(node.right, kth);

            if (nonNull(integer)) {
                return integer;
            }
        }

        if (kth == currentPosition) {
            return node.value;
        }

        currentPosition++;

        if (node.left != null) {
            final Integer integer = postOrderSearch(node.left, kth);

            if (nonNull(integer)) {
                return integer;
            }
        }

        return null;
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return  Integer.toString(value);
        }
    }
}

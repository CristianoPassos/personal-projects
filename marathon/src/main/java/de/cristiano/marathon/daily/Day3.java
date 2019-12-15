package de.cristiano.marathon.daily;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

/**
 * Given the root to a binary tree, implement serialize(root), which serializes
 * the tree into a string, and deserialize(s), which deserializes the string
 * back into the tree.
 */
class Day3 {
    private final static String NULL = "null";

    private final AtomicInteger index = new AtomicInteger();

    /**
     * Time Complexity Space Complexity
     */
    Node deserialize(String tree) {
        String[] nodes = tree.split(",");

        index.set(0);
        return reconstruct(nodes);
    }

    private Node reconstruct(String[] nodes) {
        if (nodes[index.intValue()].equals(NULL)) {
            return null;
        }

        char value = nodes[index.intValue()].charAt(0);
        index.incrementAndGet();
        Node left = reconstruct(nodes);
        index.incrementAndGet();
        Node right = reconstruct(nodes);

        return new Node(value, left, right);
    }

    String serialize(Node tree) {
        requireNonNull(tree);

        return tree.toString();
    }

    static class Node {
        final Character value;

        final Node left;

        final Node right;

        Node(Character value, Node left, Node right) {
            this.value = requireNonNull(value);
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (this.getClass() != obj.getClass())
                return false;

            Node other = (Node) obj;

            return this.value.equals(other.value) && nodeEqual(this.left, other.left)
                    && nodeEqual(this.right, other.right);
        }

        @Override
        public String toString() {
            return format("%s,%s,%s", value, print(left), print(right));
        }

        private String print(Node node) {
            return node == null ? "null" : node.toString();
        }

        private boolean nodeEqual(Node node, Node other) {
            return node == null ? null == other : node.equals(other);
        }
    }

}

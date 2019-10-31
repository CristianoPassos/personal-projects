package de.cristiano.marathon;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

/**
 * Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
 */
class Day3 {
    private final static Integer NAME_SCAPE = "name:".length();

    private final static Integer LEFT_SCAPE = 5;

    private final static Integer RIGHT_SCAPE = 6;

    /**
     * Time Complexity
     * Space Complexity
     */
    Node deserialize(String tree) {
        int position = 0;
        int nameEnd = tree.indexOf(",");

        final String name = tree.substring(NAME_SCAPE, nameEnd);

        position = nameEnd;

        return null;
    }


    String serialize(Node tree) {
        requireNonNull(tree);

        return tree.toString();
    }

    static class Node {
        private final String name;

        private final Node left;

        private final Node right;

        Node(String name, Node left, Node right) {
            this.name = requireNonNull(name);
            this.left = left;
            this.right = right;
        }

        Node getLeft() {
            return left;
        }

        String getName() {
            return name;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public String toString() {
            return format("name:%s, left:{%s}, right:{%s}", name, left == null ? "null" : left, right == null ? "null" : right);
        }
    }
}

package de.cristiano.marathon.dcp;

import java.math.BigInteger;
import java.util.Objects;

public class Day50 {

    int process(Node root) {
        return depthFirstTraversalAndProcess(root).intValueExact();
    }

    private BigInteger depthFirstTraversalAndProcess(Node node) {
        if (node.isLeaf()) {
            return node.toInteger();
        }

        final BigInteger valueLeft = depthFirstTraversalAndProcess(node.left);
        final BigInteger valueRight = depthFirstTraversalAndProcess(node.right);

        return doOperation(valueLeft, node.toOperation(), valueRight);
    }


    private BigInteger doOperation(BigInteger one, Character operation, BigInteger two) {
        switch (operation) {
            case '+':
                return one.add(two);
            case '-':
                return one.subtract(two);
            case '*':
                return one.multiply(two);
            case '/':
                return one.divide(two);
            default:
                throw new NoSuchMethodError("Invalid operation: " + operation);
        }
    }

    static class Node {
        Node left;
        Node right;

        String value;

        public Node(Node left, Node right, String value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        boolean isLeaf() {
            return Objects.isNull(left) && Objects.isNull(right);
        }

        BigInteger toInteger() {
            if (isLeaf()) {
                return new BigInteger(value);
            }

            return null;
        }

        Character toOperation() {
            if (!isLeaf()) {
                return value.charAt(0);
            }

            return null;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}

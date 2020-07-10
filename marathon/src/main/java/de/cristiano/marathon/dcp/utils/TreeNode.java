package de.cristiano.marathon.dcp.utils;

import static java.util.Objects.isNull;

public class TreeNode {
    public Character value;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(Character value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return isNull(left) && isNull(right);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

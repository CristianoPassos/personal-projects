package de.cristiano.marathon.daily;

import de.cristiano.marathon.daily.utils.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Given the root of a binary tree, return a deepest node. For example, in the following tree, return d.
 */
public class Day80 {

    @Test
    void baseCase_shouldSucceed() {
        //Given
        final TreeNode root = new TreeNode('a', new TreeNode('b', null, new TreeNode('d', null, null)), new TreeNode('c', null, null));

        //When
        final TreeNode deepestNode = deepestNode(root);

        //Then
        assertThat(deepestNode.value, is('d'));
    }

    private TreeNode deepestNode(TreeNode root) {
        final Stack<TreeNode> stack = new Stack<>();

        stack.add(root);
        TreeNode deepestNode = null;

        while (!stack.isEmpty()) {
            int heightElements = stack.size();

            while (heightElements > 0) {
                final TreeNode node = stack.remove(0);

                if (node.left != null)
                    stack.push(node.left);

                if (node.right != null)
                    stack.push(node.right);

                if (node.isLeaf())
                    deepestNode = node;

                heightElements--;
            }
        }

        return deepestNode;
    }
}

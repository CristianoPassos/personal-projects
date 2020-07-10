package de.cristiano.marathon.dcp;

import de.cristiano.marathon.dcp.utils.TreeNode;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day27Test {

    private Day27 day27 = new Day27();


    @Test
    void reconstructTree_shouldSucceed() {
        //When
        final TreeNode node = day27.reconstructTree("abdklecfg", "kdlbeafcg");

        //Then
        assertThat(node.left.left.left.value, is('k'));
        assertThat(node.right.right.value, is('g'));
    }
}

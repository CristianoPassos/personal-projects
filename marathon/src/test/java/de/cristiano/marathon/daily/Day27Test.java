package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day27Test {

    private Day27 day27 = new Day27();


    @Test
    void reconstructTree_shouldSucceed() {
        //When
        final Day27.Tree tree = day27.reconstructTree("abdklecfg", "kdlbeafcg");

        //Then
        assertThat(tree.left.left.left.value, is('k'));
        assertThat(tree.right.right.value, is('g'));
    }
}
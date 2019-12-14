package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day3Test {

    private Day3 day3 = new Day3();

    @Test
    public void challenge_shouldSucceed() {
        //Given
        final Day3.Node tree = new Day3.Node('A', new Day3.Node('C', null, null), new Day3.Node('D', null, new Day3.Node('B', null, null)));

        //when
        final Day3.Node recoveredTree = day3.deserialize(day3.serialize(tree));

        //Then
        assertThat(recoveredTree, is(tree));
    }
}
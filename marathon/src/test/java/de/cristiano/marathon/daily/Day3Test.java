package de.cristiano.marathon.daily;

import de.cristiano.marathon.daily.Day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    private Day3 day3 = new Day3();


    void challenge_shouldSucceed() {
        //Given
        final Day3.Node tree = new Day3.Node("root", new Day3.Node("left", new Day3.Node("left.left", null, null), null), new Day3.Node("right", null, null));

        //when
        final Day3.Node result = day3.deserialize(day3.serialize(tree));

        //Then
        assertEquals("left.left", result.getLeft().getLeft().getName());
    }
}
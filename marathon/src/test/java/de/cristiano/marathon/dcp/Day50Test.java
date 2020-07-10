package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day50Test {

    private final Day50 day50 = new Day50();

    @Test
    void process_shouldSucceed() {
        //Given
        final Day50.Node op1 = new Day50.Node(new Day50.Node(null, null, "3"), new Day50.Node(null, null, "2"), "+");
        final Day50.Node op2 = new Day50.Node(new Day50.Node(null, null, "4"), new Day50.Node(null, null, "5"), "+");
        final Day50.Node op3 = new Day50.Node(op1, op2, "*");

        //When
        final int response = day50.process(op3);

        //Then
        assertThat(response, is(45));
    }
}

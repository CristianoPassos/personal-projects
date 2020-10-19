package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day53Test {

    private final Day53 day53 = new Day53();

    @Test
    public void queue_shouldSucceed() {
        //Given
        day53.enqueue(1);
        day53.enqueue(2);
        day53.enqueue(3);
        day53.enqueue(4);
        day53.enqueue(5);

        //When
        final int firstElement = day53.dequeue();

        day53.enqueue(6);
        day53.enqueue(7);

        final int secondElement = day53.dequeue();

        //Then
        assertThat(firstElement, is(1));
        assertThat(secondElement, is(2));
    }
}

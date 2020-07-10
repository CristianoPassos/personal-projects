package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day26Test {
    private final Day26 day26 = new Day26();

    @Test
    public void removeKth_shouldSucceed() {
        //Given
        day26.addElement(1);
        day26.addElement(2);
        day26.addElement(3);
        day26.addElement(4);
        day26.addElement(5);
        day26.addElement(6);

        assertThat(day26.size, is(6));

        //When
        final int removedLastElement = day26.removeKth(3);
        final int removedK = day26.removeKth(3);

        //Then
        assertThat(removedLastElement, is(6));
        assertThat(removedK, is(3));
        assertThat(day26.size, is(4));
    }

}

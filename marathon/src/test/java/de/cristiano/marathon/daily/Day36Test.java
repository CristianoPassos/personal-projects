package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class Day36Test {

    private final Day36 day36 = new Day36();

    @Test
    public void search_shouldSucceed() {
        //Given
        Day36.Node root = new Day36.Node(8, new Day36.Node(3, new Day36.Node(1, null, null), new Day36.Node(6, null, null)),
                new Day36.Node(14, new Day36.Node(13, null, null), new Day36.Node(15, null, null)));

        //When
        final int thirdElement = day36.searchFor(root, 2);
        final int firstElement = day36.searchFor(root, 0);
        final int fourthElement = day36.searchFor(root, 4);
        final int sixthElement = day36.searchFor(root, 6);
        final Integer seventhElement = day36.searchFor(root, 7);

        //Then
        assertThat(firstElement, is(15));
        assertThat(thirdElement, is(13));
        assertThat(fourthElement, is(6));
        assertThat(sixthElement, is(1));
        assertThat(seventhElement, is(nullValue()));
    }
}
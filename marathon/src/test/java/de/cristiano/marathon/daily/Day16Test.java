package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day16Test {
    private Day16 day16 = new Day16();


    @Test
    void minRooms() {
        //Given
        Day16.TimeInterval[] timeIntervals = {
                new Day16.TimeInterval(30, 75),
                new Day16.TimeInterval(0, 50),
                new Day16.TimeInterval(60, 150)
        };

        //When
        final int minRooms = day16.minRooms(timeIntervals);

        //Then
        assertThat(minRooms, is(2));
    }
}
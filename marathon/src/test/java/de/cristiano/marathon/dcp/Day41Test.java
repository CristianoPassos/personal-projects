package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class Day41Test {

    private final Day41 day41 = new Day41();

    @Test
    void computeItinerary_shouldSucceed() {
        //Given
        final Day41.Flight[] flights1 = {
                new Day41.Flight("SFO", "HKO"),
                new Day41.Flight("YYZ", "SFO"),
                new Day41.Flight("YUL", "YYZ"),
                new Day41.Flight("HKO", "ORD")
        };
        final Day41.Flight[] flights2 = {
                new Day41.Flight("SFO", "COM"),
                new Day41.Flight("COM", "YYZ")
        };
        final Day41.Flight[] flights3 = {
                new Day41.Flight("A", "B"),
                new Day41.Flight("A", "C"),
                new Day41.Flight("B", "C"),
                new Day41.Flight("C", "A")
        };

        //When
        final String itinerary3 = day41.computeItinerary(flights3, "A");
        final String itinerary1 = day41.computeItinerary(flights1, "YUL");
        final String itinerary2 = day41.computeItinerary(flights2, "COM");

        //Then
        assertThat(itinerary1, is("YUL,YYZ,SFO,HKO,ORD"));
        assertThat(itinerary2, is(nullValue()));
        assertThat(itinerary3, is("A,B,C,A,C"));
    }
}

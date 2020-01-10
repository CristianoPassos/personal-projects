package de.cristiano.marathon.miscellaneous;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Z1 {

    @Test
    void baseCase_shouldSucceed() {
        //Given
        int[] A = {2, 3, 4, 1, 5};

        //When
        final int moments = solution(A);

        //Then
        assertThat(moments, is(2));
    }

    private int solution(int[] a) {
        final Set<Integer> bulbs = new HashSet<>();
        int nextBulb = 1;
        int moments = 0;

        for (int bulb : a) {
            if (bulb == nextBulb) {
                moments++;

                if (nextBulb != a.length) {
                    nextBulb = nextBulb(bulbs, nextBulb, a.length);
                }
            }

            bulbs.add(bulb);
        }

        return moments;
    }

    private int nextBulb(Set<Integer> bulbs, int currentBulb, int size) {
        for (int index = currentBulb + 1; index <= size; index++) {
            if (!bulbs.contains(index)) {
                return index;
            }
        }

        // return -1;
        throw new IllegalStateException("Invalid input");
    }

}
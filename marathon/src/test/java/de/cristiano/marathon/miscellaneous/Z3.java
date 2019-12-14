package de.cristiano.marathon.miscellaneous;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Z3 {
    private static int PAIRS_LIMIT = 1000000000;

    @Test
    public void baseCase_shouldSucceed() {
        //Given
        int[] A = {3, 5, 6, 3, 3, 5};

        //When
        final int paris = solution(A);

        //Then
        assertThat(paris, is(4));
    }

    private int solution(int[] a) {
        final Map<Integer, Integer> mapping = new HashMap<>();

        for (int i : a) {
            mapping.compute(i, (k, v) -> v == null ? 1 : ++v);
        }

        int identicalPairs = mapping.values().stream()
                .mapToInt(q -> {
                    int combinations = q - 1;
                    int groups = 0;

                    for (int index = 1; index <= combinations; index++) {
                        groups += index;
                    }

                    return groups;
                }).sum();

        return Math.min(identicalPairs, PAIRS_LIMIT);
    }
}

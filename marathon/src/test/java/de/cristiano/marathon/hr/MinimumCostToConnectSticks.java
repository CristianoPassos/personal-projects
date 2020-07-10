package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinimumCostToConnectSticks {

    @Test
    void baseCase_shouldSucceed() {
        //Given
        Integer[] sticks = {1, 8, 3, 3, 3, 5};

        //When
        final int cost = connectSticks(sticks);

        //Then
        assertThat(cost, is(56));
    }

    private int connectSticks(Integer[] sticks) {
        if (sticks.length == 1) {
            return sticks[0];
        }
        if (sticks.length == 2) {
            return sticks[0] + sticks[1];
        }

        final PriorityQueue<Integer> minHeap = new PriorityQueue<>(asList(sticks));

        int totalCost = 0;

        while (minHeap.size() > 1) {
            int fusionCost = minHeap.poll() + minHeap.poll();
            minHeap.add(fusionCost);
            totalCost += fusionCost;
        }

        return totalCost;
    }
}

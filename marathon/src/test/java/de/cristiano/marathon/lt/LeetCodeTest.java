package de.cristiano.marathon.lt;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

public class LeetCodeTest {

    private LeetCode solution = new LeetCode();

    @Test
    public void MovingAverageTest() {
        var movingAverage = new MovingAverage(3);

        assertThat(movingAverage.next(1)).isEqualTo(1D);
        assertThat(movingAverage.next(10)).isEqualTo(5.5D);
        assertThat(movingAverage.next(3)).isCloseTo(4.66667D, withinPercentage(99));
        assertThat(movingAverage.next(5)).isEqualTo(6D);
    }

    @Test
    public void wallsAndGatesTest() {
        int[][] array = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };

        solution.wallsAndGates(array);
    }

    @Test
    void openLockTest() {
        int turns = solution.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202");

        assertThat(turns).isEqualTo(6);
    }

    @Test
    void numSquaresTest() {
        int leastPerfectSquareNumbers = solution.numSquares(12);

        assertThat(leastPerfectSquareNumbers).isEqualTo(3);
    }

    @Test
    void findTargetSumWaysTest() {
        var expressions = solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);

        assertThat(expressions).isEqualTo(5);
    }

    @Test
    void decodeStringTest() {
        var decodedString = solution.decodeString("3[a2[c]]");

        assertThat(decodedString).isEqualTo("accaccacc");
    }

    @Test
    void topKFrequentTest() {
        var response = solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);

        assertThat(response).containsExactlyInAnyOrder(1, 2);
    }

    @Test
    void topKFrequent2Test() {
        var test = new KthLargest(3, new int[]{4, 5, 8, 2});
        test.add(3);
        test.add(5);
        test.add(10);
        test.add(9);
        test.add(4);
    }

    @Test
    void kWeakestRowsTest() {
        var response = solution.kWeakestRows(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        }, 3);

        assertThat(response).contains(2, 0, 3);
    }

    @Test
    void kthSmallestTest() {
        var response = solution.kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8);

        assertThat(response).isEqualTo(13);
    }

    @Test
    void minMeetingRoomsTest() {
        var response = solution.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}});

        assertThat(response).isEqualTo(2);
    }

    @Test
    void MedianFinderTest() {
        var medianFinder = new MedianFinder();

        medianFinder.addNum(1);
        medianFinder.addNum(2);

        assertThat(medianFinder.findMedian()).isCloseTo(1.5, Percentage.withPercentage(2));

        medianFinder.addNum(3);
        assertThat(medianFinder.findMedian()).isCloseTo(2, Percentage.withPercentage(2));

        medianFinder.addNum(4);
        medianFinder.addNum(5);
    }

    @Test
    void isIsomorphicTest() {
        var result = solution.isIsomorphic("aaaa", "bbbb");

        assertThat(result).isTrue();
    }
}

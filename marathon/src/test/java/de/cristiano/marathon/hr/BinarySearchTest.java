package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class BinarySearchTest {

    private BinarySearch binarySearch = new BinarySearch();

    @Test
    void numberPresent_shouldSucceed() {
        //Given
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

        //When
        boolean isPresent = binarySearch.isPresent(primes, 67);

        //Then
        assertThat(isPresent, is(true));
    }

    @Test
    void numberNotPresent_shouldSucceed() {
        //Given
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

        canWin("++++");

        //When
        boolean isPresent = binarySearch.isPresent(primes, 10);

        //Then
        assertThat(isPresent, is(false));
    }

    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        for (int index = 0; index < s.length() - 1; index++) {
            if (s.charAt(index) == '+' && s.charAt(index + 1) == '+') {
                String nextState = s.substring(0, index) + "--" + s.substring(index + 2);
                if (!canWin(nextState)) {
                    return true;
                }
            }
        }

        return false;
    }
}

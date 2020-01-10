package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * A number is considered perfect if its digits sum up to exactly 10.
 */
public class Day70 {

    @Test
    public void baseCase_shouldSucceed() {
        //When
        final int pf1 = perfectNumber(1);
        final int pf2 = perfectNumber(2);

        //Then
        assertThat(pf1, is(19));
        assertThat(pf2, is(28));
    }

    private int perfectNumber(int i) {
        final String number = Integer.toString(i);
        int total = 0;

        for (Character digit : number.toCharArray()) {
            total += digit - '0';
        }

        final int toPerfectNUmber = 10 - total;
        return toPerfectNUmber > 0 ? Integer.parseInt(number + toPerfectNUmber) : i;
    }

}

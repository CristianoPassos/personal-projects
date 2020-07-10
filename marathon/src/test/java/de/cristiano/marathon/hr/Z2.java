package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Z2 {

    @Test
    void baseCase_shouldSucceed() {
        //Given
        String binaryNumber = "011100";

        //When
        int steps = solution(binaryNumber);

        //Then
        assertThat(steps, is(7));
    }

    private int solution(String binaryNumber) {
        int decimalRepresentation = Integer.parseUnsignedInt(binaryNumber, 2);
        int steps = 0;


        while (decimalRepresentation != 0) {
            if (decimalRepresentation % 2 == 1) {
                decimalRepresentation -= 1;
            } else {
                decimalRepresentation = decimalRepresentation / 2;
            }

            steps++;
        }

        return steps;
    }
}

package de.cristiano.marathon.daily;

import static java.lang.Math.abs;

/**
 * Implement integer exponentiation. That is, implement the pow(x, y) function, where x and y are integers and returns x^y.
 * <p>
 * Do this faster than the naive method of repeated multiplication.
 */
public class Day61 {

    /**
     * Not faster than the iterative approach.
     */
    float pow(int base, int exponent) {
        final float pow = calculate(base, abs(exponent));

        if (exponent < 0) {
            return 1 / pow;
        }

        return pow;
    }

    private int calculate(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        final int pow = calculate(base, exponent / 2);

        return multiply(base, exponent, pow);
    }

    private int multiply(int base, int exponent, int pow) {
        if (exponent % 2 == 0) {
            return pow * pow;
        } else {
            return pow * pow * base;
        }
    }

}

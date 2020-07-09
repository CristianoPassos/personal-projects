package de.cristiano.marathon.miscellaneous;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class K1 {

    @Test
    public void baseCase_shouldSucceed() {
        //When
        final String masked = maskify("A1!2/(3)[4]^%56789)=A#!#/(#)[#]^%#6789");

        //Then
        assertThat(masked, is("A#!#/(#)[#]^%#####)=A#!#/(#)[#]^%#6789"));
    }

    public String maskify(String creditCardNumber) {
        final String toMask = creditCardNumber.trim();

        if (toMask.length() < 7) {
            return toMask;
        }

        final StringBuilder maskedCreditCardNumber = new StringBuilder();
        final int maskUpTo = toMask.length() - 4;

        maskedCreditCardNumber.append(toMask.charAt(0));

        for (int index = 1; index < maskUpTo; index++) {
            if (Character.isDigit(toMask.charAt(index))) {
                maskedCreditCardNumber.append("#");
            } else {
                maskedCreditCardNumber.append(toMask.charAt(index));
            }
        }

        maskedCreditCardNumber.append(toMask.substring(maskUpTo));

        return maskedCreditCardNumber.toString();
    }
}
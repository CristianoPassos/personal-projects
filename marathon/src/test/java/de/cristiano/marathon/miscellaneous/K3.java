package de.cristiano.marathon.miscellaneous;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

import static java.lang.Double.parseDouble;
import static java.util.Objects.isNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class K3 {

    @Test
    void baseCase_shouldSucceed() {
        //Given
        int[] A = {2, 3, 4, 1, 5};

        //When
        final Double rpn1 = evaluate("10000 123 +");
        final Double rpn2 = evaluate("5 1 2 + 4 * + 3 -");

        //Then
        assertThat(rpn1, is(10123.0));
        assertThat(rpn2, is(14.0));
    }

    private static final Map<String, DoubleBinaryOperator> OPERATIONS_MAPPING = new HashMap<>();

    static {
        OPERATIONS_MAPPING.put("+", Double::sum);
        OPERATIONS_MAPPING.put("-", (d1, d2) -> d2 - d1);
        OPERATIONS_MAPPING.put("*", (d1, d2) -> d1 * d2);
        OPERATIONS_MAPPING.put("/", (d1, d2) -> d2 / d1);
    }

    public double evaluate(String expr) {
        if (expr.trim().isEmpty()) {
            return 0;
        }

        final String[] operations = expr.split(" ");
        final Stack<Double> numbers = new Stack<>();

        for (String element : operations) {
            final DoubleBinaryOperator operation = OPERATIONS_MAPPING.get(element);

            if (isNull(operation)) {
                numbers.push(parseDouble(element));
            } else {
                numbers.push(operation.applyAsDouble(numbers.pop(), numbers.pop()));
            }
        }

        return numbers.pop();
    }
}
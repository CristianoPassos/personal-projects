package de.cristiano.flight.exercise2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static de.cristiano.flight.util.SystemOutUtil.*;
import static org.hamcrest.CoreMatchers.is;

public class Part2Test {

    private Part2 part2 = new Part2();

    @After
    public void after() {
        restoreSystemOutput();
    }

    @Before
    public void before() {
        captureSystemOutput();
    }

    @Test
    public void test_countUpAndDownSimpleCase() {
        part2.countUpAndDown(0, 5);

        String expectedValue = formatAsNewLines(Stream.of(0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0));
        Assert.assertThat(retrieveSystemOutput(), is(expectedValue));
    }

    @Test(expected = StackOverflowError.class)
    public void test_countUpAndDownUpToLimit() {
        part2.countUpAndDown(0, Integer.MAX_VALUE);
    }
}

package de.cristiano.flight.exercise2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static de.cristiano.flight.util.SystemOutUtil.*;
import static org.hamcrest.CoreMatchers.is;

public class Part1Test {

    private final Part1 part1 = new Part1();

    @After
    public void after() {
        restoreSystemOutput();
    }

    @Before
    public void before() {
        captureSystemOutput();
    }

    @Test(expected = StackOverflowError.class)
    public void test_countUpLimit() {
        part1.countUp(0, Integer.MAX_VALUE);
    }

    @Test
    public void test_countUpSimpleCase() {
        part1.countUp(0, 5);

        String expectedValue = formatAsNewLines(Stream.of(0, 1, 2, 3, 4, 5));
        Assert.assertThat(retrieveSystemOutput(), is(expectedValue));
    }

}

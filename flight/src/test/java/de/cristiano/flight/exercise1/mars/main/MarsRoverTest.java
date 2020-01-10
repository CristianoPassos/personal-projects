package de.cristiano.flight.exercise1.mars.main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static de.cristiano.flight.util.SystemOutUtil.*;
import static org.hamcrest.CoreMatchers.is;

public class MarsRoverTest {

    @After
    public void after() {
        restoreSystemOutput();
    }

    @Before
    public void before() {
        captureSystemOutput();
    }

    @Test
    public void test_SystemOutPut() {
        final String[] command = {"FF"};

        MarsRover.main(command);

        Assert.assertThat(retrieveSystemOutput(), is("[0, 2]"));
    }

    @Test
    public void test_DefaultSystemOutPut() {
        final String[] command = {};

        MarsRover.main(command);

        Assert.assertThat(retrieveSystemOutput(), is("[0, 0]"));
    }
}

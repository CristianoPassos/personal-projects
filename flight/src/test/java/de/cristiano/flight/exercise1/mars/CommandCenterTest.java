package de.cristiano.flight.exercise1.mars;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CommandCenterTest {

    private CommandCenter commandCenter;

    private Robot robot;

    @Before
    public void before() {
        commandCenter = new CommandCenter();
        robot = new Robot();
    }

    @Test
    public void test_processComplexCommand() {
        final String complexCommand = "LFFFRFFFRRFFF";

        final Position actualPosition = commandCenter.processCommand(complexCommand, robot);

        final Position expectedPosition = new Position(-3, 0);
        assertThat(actualPosition, is(expectedPosition));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_processInvalidCommand() {
        final String invalidCommand = "FC";

        commandCenter.processCommand(invalidCommand, robot);
    }

    @Test
    public void test_processNoCommand() {
        final String noCommand = "";

        final Position actualPosition = commandCenter.processCommand(noCommand, robot);

        final Position expectedPosition = new Position(0, 0);
        assertThat(actualPosition, is(expectedPosition));
    }

    @Test
    public void test_processSimpleCommand() {
        final String simpleCommand = "FF";

        final Position actualPosition = commandCenter.processCommand(simpleCommand, robot);

        final Position expectedPosition = new Position(0, 2);
        assertThat(actualPosition, is(expectedPosition));
    }
}

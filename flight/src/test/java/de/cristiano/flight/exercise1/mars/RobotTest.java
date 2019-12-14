package de.cristiano.flight.exercise1.mars;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RobotTest {

    private Robot robot;

    @Before
    public void before() {
        robot = new Robot();
    }

    @Test
    public void test_processSimpleInstructions() {
        robot.moveForward();
        robot.moveForward();

        final Position expectedPosition = new Position(0, 2);

        assertThat(robot.getPosition(), is(expectedPosition));
    }

    @Test
    public void test_turnLeft() {
        robot.turnLeft();

        assertThat(robot.getDirection(), CoreMatchers.is(Direction.WEST));
    }

    @Test
    public void test_turnRight() {
        robot.turnRight();

        assertThat(robot.getDirection(), CoreMatchers.is(Direction.EAST));
    }

    @Test
    public void test_turnSpin() {
        robot.turnRight();
        robot.turnRight();
        robot.turnRight();
        robot.turnRight();

        assertThat(robot.getDirection(), CoreMatchers.is(Direction.NORTH));
    }

    @Test
    public void test_turnSouth() {
        robot.turnRight();
        robot.turnRight();

        assertThat(robot.getDirection(), CoreMatchers.is(Direction.SOUTH));
    }
}

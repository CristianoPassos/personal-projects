package de.cristiano.flight.exercise1.mars.instruction;
import de.cristiano.flight.exercise1.mars.Robot;

import javax.annotation.Nonnull;

public class MoveForwardInstruction implements Instruction {

    @Override
    public boolean canHandle(@Nonnull final String instruction) {
        return "F".equalsIgnoreCase(instruction);
    }

    @Override
    public void apply(@Nonnull final Robot robot) {
        robot.moveForward();
    }
}

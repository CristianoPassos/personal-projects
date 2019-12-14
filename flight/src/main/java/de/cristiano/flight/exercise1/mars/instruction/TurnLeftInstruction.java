package de.cristiano.flight.exercise1.mars.instruction;
import de.cristiano.flight.exercise1.mars.Robot;

import javax.annotation.Nonnull;

public class TurnLeftInstruction implements Instruction {

    @Override
    public boolean canHandle(@Nonnull final String instruction) {
        return "L".equalsIgnoreCase(instruction);
    }

    @Override
    public void apply(@Nonnull final Robot robot) {
        robot.turnLeft();
    }
}

package de.cristiano.flight.exercise1.mars.instruction;
import de.cristiano.flight.exercise1.mars.Robot;

import javax.annotation.Nonnull;

public class TurnRightInstruction implements Instruction {

    @Override
    public boolean canHandle(@Nonnull final String instruction) {
        return "R".equalsIgnoreCase(instruction);
    }

    @Override
    public void apply(@Nonnull final Robot robot) {
        robot.turnRight();
    }
}

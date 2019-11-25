package de.cristiano.flight.exercise1.mars.instruction;
import de.cristiano.flight.exercise1.mars.Robot;

import javax.annotation.Nonnull;

public interface Instruction {

    boolean canHandle(@Nonnull final String instruction);

    void apply(@Nonnull final Robot robot);
}

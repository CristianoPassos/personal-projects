package de.cristiano.flight.exercise1.mars;
import de.cristiano.flight.exercise1.mars.instruction.Instruction;
import de.cristiano.flight.exercise1.mars.instruction.InstructionConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.Stream;

public class CommandCenter {

    public Position processCommand(@Nullable final String command,
                                   @Nonnull final Robot robot) {
        if (command == null || command.isEmpty()) {
            return new Position();
        }

        splitAndConvertToInstructions(command)
            .forEach(instruction -> instruction.apply(robot));

        return robot.getPosition();
    }

    private Stream<Instruction> splitAndConvertToInstructions(@Nonnull final String command) {
        return Stream.of(command.split(""))
            .map(InstructionConverter::convert);
    }
}

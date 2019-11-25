package de.cristiano.flight.exercise1.mars.instruction;
import javax.annotation.Nonnull;
import java.util.Collection;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class InstructionConverter {

    private final static Collection<Instruction> INSTRUCTIONS = asList(new MoveForwardInstruction(), new TurnLeftInstruction(),
        new TurnRightInstruction());

    public static Instruction convert(@Nonnull final String instructionString) {
        return INSTRUCTIONS.stream().
            filter(instruction -> instruction.canHandle(instructionString))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                format("Only L R and F instructions are allowed, please recheck your command, instruction %s is not allowed.", instructionString)));
    }
}

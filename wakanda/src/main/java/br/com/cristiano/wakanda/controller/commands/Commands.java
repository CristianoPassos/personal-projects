package br.com.cristiano.wakanda.controller.commands;

import br.com.cristiano.wakanda.controller.commands.executors.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public enum Commands {
    EXIT(97, ExitCommand.class), LIST_COMMANDS(98, ListCommand.class), MOVE(1, MoveCommand.class), PROTECT_CITY(2,
            ProtectCityCommand.class), SAVE(99, SaveCommand.class);

    private static final int[] ids;

    static {
        Commands[] values = Commands.values();
        ids = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            ids[i] = values[i].id;
        }
    }

    private Class<? extends Command> commandClass;
    private int id;

    private Commands(int id, Class<? extends Command> commandClass) {
        this.id = id;
        this.commandClass = commandClass;
    }

    private static Command getExecuter(Commands command) {
        Command newClass = null;
        try {
            newClass = command.commandClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
        return newClass;
    }

    public static Optional<Command> getExecuter(int idCommand) {
        Optional<Commands> command = recoverById(idCommand);
        if (command.isPresent()) {
            return Optional.of(getExecuter(command.get()));
        } else {
            return Optional.empty();
        }
    }

    public static int[] getIds() {
        return ids;
    }

    private static Optional<Commands> recoverById(int id) {
        for (Commands command : Commands.values()) {
            if (command.getId() == id) {
                return Optional.of(command);
            }
        }
        return Optional.empty();
    }

    public Command getExecuter() {
        return getExecuter(this);
    }

    public int getId() {
        return this.id;
    }
}
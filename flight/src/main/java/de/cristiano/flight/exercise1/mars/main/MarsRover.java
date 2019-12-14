package de.cristiano.flight.exercise1.mars.main;
import de.cristiano.flight.exercise1.mars.CommandCenter;
import de.cristiano.flight.exercise1.mars.Robot;

public class MarsRover {

    private final static CommandCenter COMMAND_CENTER = new CommandCenter();

    private final static Robot MARS_ROBOT = new Robot();

    private static String extractCommand(final String[] args) {
        if (args != null && args.length > 0) {
            return args[0];
        } else {
            return "";
        }
    }

    public static void main(String args[]) {
        final String command = extractCommand(args);

        System.out.print(COMMAND_CENTER.processCommand(command, MARS_ROBOT));
    }
}

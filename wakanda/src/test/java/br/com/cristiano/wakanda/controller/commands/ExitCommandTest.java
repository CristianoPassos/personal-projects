package br.com.cristiano.wakanda.controller.commands;

import br.com.cristiano.wakanda.controller.commands.executors.ExitCommand;
import org.junit.Test;

public class ExitCommandTest extends BaseCommandTest {
    public ExitCommandTest() {
        super(new ExitCommand());
    }

    @Test
    public void exitTest() {
        command.execute(null, view);
        assertMessageIsPresent("game.exit.author");
    }

}

package br.com.cristiano.wakanda.controller.commands;

import br.com.cristiano.wakanda.controller.commands.executors.MoveCommand;
import br.com.cristiano.wakanda.factory.GameFactory;
import br.com.cristiano.wakanda.model.Game;
import org.junit.Test;

public class MoveCommandTest extends BaseCommandTest {
    public MoveCommandTest() {
        super(new MoveCommand());
    }

    @Test
    public void moveCommandTest() {
        setUserInput("0".getBytes());
        Game game = GameFactory.createNewGame();
        this.command.execute(game, view);
        assertTextIsPresent("Birnin Azzaria - City  [0]");
        assertMessageIsPresent("game.command.move.welcome", "Birnin Azzaria - City");
    }

    @Test
    public void wrongInputMoveCommandTest() {
        setUserInput("55\n55\n0".getBytes());
        Game game = GameFactory.createNewGame();
        this.command.execute(game, view);
        assertTextIsPresent("Birnin Azzaria - City  [0]");
        assertMessageIsPresent("game.command.move.welcome", "Birnin Azzaria - City");
    }

}

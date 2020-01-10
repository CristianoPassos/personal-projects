package br.com.cristiano.wakanda.controller.commands;

import br.com.cristiano.wakanda.controller.commands.executors.ProtectCityCommand;
import br.com.cristiano.wakanda.factory.GameFactory;
import br.com.cristiano.wakanda.model.Game;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProtectCityCommandTest extends BaseCommandTest {

    public ProtectCityCommandTest() {
        super(new ProtectCityCommand());
    }

    @Test
    public void playerLevelUpTest() {
        Game game = GameFactory.createNewGame();
        while (game.getPlayer().getLevel() != 2) {
            command.execute(game, view);
        }
        assertEquals(2, game.getPlayer().getLevel());
        assertEquals(0, game.getPlayer().getExp());
        assertEquals(3, game.getPlayer().getAttack());
        assertEquals(110, game.getPlayer().getLife());
    }

    @Test
    public void playerLoseTest() {
        command.execute(GameFactory.createNewGameLastCity(), view);
        assertMessageIsPresent("game.command.protect.city.exit");
    }

    @Test
    public void playerWinsTest() {
        command.execute(GameFactory.createNewGame(), view);
        assertTextIsPresent("Congratulation you have won the battle!");
    }
}

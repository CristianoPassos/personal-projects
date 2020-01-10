package br.com.cristiano.wakanda.controller;

import br.com.cristiano.wakanda.factory.GameFactory;
import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.model.Storage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class LoadOrStartNewGameControllerTest extends BaseControllerTest<LoadOrStartNewGameController> {
    Storage storage = new Storage();

    public LoadOrStartNewGameControllerTest() {
        super(new LoadOrStartNewGameController());
    }

    private void assertNewGameHasStarted() {
        assertTextIsPresent("Staring now Cristiano de Oliveira Passos!");
    }

    @Test
    public void createNewGameTest() {
        setUserInput("0\nCristiano de Oliveira Passos".getBytes());
        controller.newOrLoadGame();
        assertNewGameHasStarted();
    }

    @Test
    public void loadGameTest() {
        persistRandonGames();
        setUserInput("1\n0".getBytes());
        controller.newOrLoadGame();
        assertMessageIsPresent("game.setup.load.success");
    }

    @Test
    public void loadGameWrongInputTest() {
        persistRandonGames();
        setUserInput("1\nhgjhghg\n0".getBytes());
        controller.newOrLoadGame();
        assertMessageIsPresent("game.setup.load.success");
    }

    @Test
    public void loadWithNoSavedGame() {
        storage.deleteGames();
        setUserInput("1\nCristiano de Oliveira Passos".getBytes());
        controller.newOrLoadGame();
        assertNewGameHasStarted();
    }

    private void persistRandonGames() {
        storage.deleteGames();
        Game game1 = GameFactory.createNewGame();
        Game game2 = GameFactory.createNewGame();
        Game game3 = GameFactory.createNewGame();
        Game game4 = GameFactory.createNewGame();
        try {
            storage.saveGame(game1);
            storage.saveGame(game2);
            storage.saveGame(game3);
            storage.saveGame(game4);

        } catch (IOException e) {
            fail();
        }
    }

}

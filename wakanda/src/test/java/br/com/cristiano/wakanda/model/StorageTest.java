package br.com.cristiano.wakanda.model;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StorageTest {

    private Storage service = new Storage();

    @Test
    public void deleteGamesTest() throws IOException {
        Game newGame = new Game("Game Storage Test");
        this.service.saveGame(newGame);
        String messasge = this.service.deleteGames();
        assertEquals("service.storage.removed.success", messasge);
    }

    @Test
    public void gameSaveTest() throws IOException {
        this.service.deleteGames();
        Game newGame = new Game("Game Storage Test");
        String message = this.service.saveGame(newGame);
        assertEquals("service.storage.save.success", message);
    }

    @Test
    public void loadGamesTest() throws IOException {
        gameSaveTest();
        List<Game> games = this.service.loadGames();
        assertEquals(1, games.size());
    }

    @Test
    public void saveSameGameTwiceTest() throws IOException {
        Game newGame = new Game("Game Storage Test");
        this.service.deleteGames();
        this.service.saveGame(newGame);
        this.service.saveGame(newGame);
        List<Game> games = this.service.loadGames();
        assertEquals(1, games.size());
    }

}

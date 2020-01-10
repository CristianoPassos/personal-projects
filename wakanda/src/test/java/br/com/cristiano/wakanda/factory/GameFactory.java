package br.com.cristiano.wakanda.factory;

import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.model.cities.BirninZana;

import java.util.UUID;

public class GameFactory {

    public static Game createNewGame() {
        Game newGame = new Game(UUID.randomUUID().toString());
        return newGame;
    }

    public static Game createNewGameLastCity() {
        Game newGame = new Game(UUID.randomUUID().toString());
        newGame.setCurrentCity(new BirninZana());
        return newGame;
    }
}

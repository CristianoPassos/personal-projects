package br.com.cristiano.wakanda.controller;

import br.com.cristiano.wakanda.WakandaProject;
import br.com.cristiano.wakanda.controller.util.MyListUtils;
import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.model.Storage;
import br.com.cristiano.wakanda.view.BasicView;

import java.util.List;
import java.util.Optional;

public class LoadOrStartNewGameController extends BaseController<BasicView> {
    private Storage storage = new Storage();

    public LoadOrStartNewGameController() {
        super(new BasicView());
    }

    private void loadGame() {
        List<Game> games = storage.loadGames();
        if (games.isEmpty()) {
            view.printMessage("service.storage.load.empty.games");
            startNewGame();
        } else {
            selectGame(games);
        }
    }

    public void newOrLoadGame() {
        view.printMessage("game.setup.new.or.load");
        int option = view.readIntUserInput(Optional.of(0), new int[]{0, 1});
        if (option == 1) {
            loadGame();
        } else {
            startNewGame();
        }
    }

    private void selectGame(List<Game> games) {
        view.printMessage("game.setup.load.select.game");
        for (int i = 0; i < games.size(); i++) {
            view.printText(games.get(i) + "  [" + i + "]");
        }
        int indexSelectedGame = view.readIntUserInput(Optional.empty(), MyListUtils.indexesToArray(games));
        WakandaProject.setGameInPlay(games.get(indexSelectedGame));
        view.printMessage("game.setup.load.success");
    }

    private void startNewGame() {
        view.printMessage("game.setup.new");
        WakandaProject.setGameInPlay(new Game(view.readStringUserInput()));
        view.clear();
        view.printMessage("game.setup.new.start", WakandaProject.getGameInPlay());
    }
}

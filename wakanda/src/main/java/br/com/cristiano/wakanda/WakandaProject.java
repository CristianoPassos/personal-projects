package br.com.cristiano.wakanda;

import br.com.cristiano.wakanda.controller.ChooseLanguageController;
import br.com.cristiano.wakanda.controller.LoadOrStartNewGameController;
import br.com.cristiano.wakanda.controller.RunCommandController;
import br.com.cristiano.wakanda.controller.StartGameController;
import br.com.cristiano.wakanda.model.Game;

public class WakandaProject {
    private static ChooseLanguageController chooseLanguageController = new ChooseLanguageController();
    private static int exit = 0;
    private static Game gameInPlay = null;
    private static LoadOrStartNewGameController loadOrStartNewGameController = new LoadOrStartNewGameController();
    private static RunCommandController runCommandController = new RunCommandController();
    private static StartGameController startGameController = new StartGameController();

    public static Game getGameInPlay() {
        return gameInPlay;
    }

    public static void setGameInPlay(Game gameInPlay) {
        WakandaProject.gameInPlay = gameInPlay;
    }

    public static void main(String[] args) {
        chooseLanguageController.chooseLanguage();
        loadOrStartNewGameController.newOrLoadGame();
        startGameController.startGame();
        while (exit != 1) {
            runCommandController.executePlayerCommand();
        }
    }

    public static void setExit(int exit) {
        WakandaProject.exit = exit;
    }
}
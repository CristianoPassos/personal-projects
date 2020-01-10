package br.com.cristiano.wakanda.controller;

import br.com.cristiano.wakanda.WakandaProject;
import br.com.cristiano.wakanda.controller.commands.Commands;
import br.com.cristiano.wakanda.model.ASCIIArt;
import br.com.cristiano.wakanda.view.BasicView;

public class StartGameController extends BaseController<BasicView> {

    public StartGameController() {
        super(new BasicView());
    }

    public void startGame() {
        view.clear();
        Commands.SAVE.getExecuter().execute(WakandaProject.getGameInPlay(), view);
        view.printText(new ASCIIArt().createArt("game.name"));
        view.printMessage("game.welcome", WakandaProject.getGameInPlay());
        view.printMessage("game.goal");
    }
}

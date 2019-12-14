package br.com.cristiano.wakanda.controller.commands.executors;

import br.com.cristiano.wakanda.WakandaProject;
import br.com.cristiano.wakanda.controller.commands.Command;
import br.com.cristiano.wakanda.model.ASCIIArt;
import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.view.BasicView;

public class ExitCommand implements Command {

	@Override
	public void execute(Game game, BasicView view) {
		view.printText(new ASCIIArt().createArt("game.exit"));
		view.printMessage(("game.exit.author"));
		WakandaProject.setExit(1);
	}

}

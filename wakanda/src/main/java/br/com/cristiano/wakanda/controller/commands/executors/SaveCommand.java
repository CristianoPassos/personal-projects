package br.com.cristiano.wakanda.controller.commands.executors;

import java.io.IOException;

import br.com.cristiano.wakanda.controller.commands.Command;
import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.model.Storage;
import br.com.cristiano.wakanda.view.BasicView;

public class SaveCommand implements Command {

	private Storage service = new Storage();

	@Override
	public void execute(Game game, BasicView view) {
		try {
			view.printMessage(this.service.saveGame(game));
		} catch (IOException e) {
			view.printMessage("service.command.save.error");
		}
	}

}

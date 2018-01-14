package br.com.cristiano.wakanda.controller.commands.executors;

import static br.com.cristiano.wakanda.view.util.MessagesUtils.getText;

import java.util.Optional;

import br.com.cristiano.wakanda.controller.commands.Command;
import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.model.cities.City;
import br.com.cristiano.wakanda.view.BasicView;

public class MoveCommand implements Command {

	@Override
	public void execute(Game game, BasicView view) {
		view.printText(getText("game.list.near.by.cities"));
		City currentPhase = game.getCurrentCity();
		view.printText(currentPhase.listNearByCities());
		view.printText(getText("game.command.move.where"));
		int nextPhaseIndex = view.readIntUserInput(Optional.empty(), currentPhase.getNearByCitiesIndexes());
		game.setCurrentCity(currentPhase.recoverNearCityByIndex(nextPhaseIndex));
		view.clear();
		view.printText(getText("game.command.move.welcome", game.getCurrentCity()));
	}

}

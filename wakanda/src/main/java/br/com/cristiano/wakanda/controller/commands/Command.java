package br.com.cristiano.wakanda.controller.commands;

import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.view.BasicView;

public interface Command {

	public void execute(Game game, BasicView view);

}
